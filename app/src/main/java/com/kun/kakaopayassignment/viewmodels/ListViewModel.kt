package com.kun.kakaopayassignment.viewmodels

import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.kun.kakaopayassignment.model.data.BookDocument
import com.kun.kakaopayassignment.model.data.Common.Companion.KAKAO_SEARCH_API_KEY
import com.kun.kakaopayassignment.model.network.ResultConverter
import com.kun.kakaopayassignment.model.repositories.SearchRepository
import kotlinx.coroutines.launch

class ListViewModel @ViewModelInject constructor(
    private val repo : SearchRepository
) : ViewModel() {

    // 페이지 및 페이지 사이즈
    private var page = 1
    private var pageSize = 50

    private var isEnd = false
    private var isCalling = false



    // 2-way
    val searchText : MutableLiveData<String> = MutableLiveData() // 검색어
    val searchProgressVisible : MutableLiveData<Boolean> = MutableLiveData() // progressbar visible

    // live Data
    private val errorData : MutableLiveData<String> = MutableLiveData()
    private val bookData : MutableLiveData<ArrayList<BookDocument>> = MutableLiveData()
    private val nowSearch : MutableLiveData<Pair<String,Int>> = MutableLiveData() // 스크롤시 검색했던 검색어 유지를 위함

    val getErrorData : LiveData<String> get() = errorData
    val getBookData : LiveData<ArrayList<BookDocument>> get() = bookData
    val getNowSearch : LiveData<Pair<String,Int>> get() = nowSearch

    fun setErrorMsg(msg:String) = errorData.postValue(msg)

    enum class SearchSort(val type:String){
        SORT_ACCURACY("accuracy"), // 정확도순
        SORT_RECENCY("recency")    // 최신순
    }
    enum class SearchTarget(val type:String){
        TARGET_TITLE("title"),     // 제목
        TARGET_ISBN("isbn"),      // isbn
        TARGET_PUBLISHER("publisher"), // 출판사
        TARGET_PERSON("person")     // 인명
    }

    fun searchBook(isSearch : Boolean = false) {
        viewModelScope.launch {
            if(searchText.value.isNullOrEmpty()) { setErrorMsg("검색어를 입력해주세요") } else {
                if(isSearch) { page = 1 }  // 버튼 클릭으로 들어왔으면 초기화 1시키기 위함.
                isCalling = true
                searchProgressVisible.postValue(true)

                repo.searchBook( KAKAO_SEARCH_API_KEY,
                    searchText.value!!,
                    SearchSort.SORT_ACCURACY.type,
                    page,
                    pageSize,
                    SearchTarget.TARGET_TITLE.type
                ).run {
                    when(this){
                        is ResultConverter.Success -> {
                            if(isSearch) {
                                nowSearch.postValue(Pair(searchText.value!!,data.meta.total_count))
                                bookData.postValue(data.bookDocuments)
                            } else {
                                bookData.value?.let {
                                    var result = it.apply { addAll(data.bookDocuments) }
                                    bookData.postValue(result)
                                } ?: kotlin.run {
                                    bookData.postValue(data.bookDocuments)
                                }
                            }

                            isEnd = data.meta.is_end
                            page++
                            isCalling = false
                            searchProgressVisible.postValue(false)
                        }
                        is ResultConverter.Error -> {
                            errorData.postValue(exception.message)
                        }
                    }
                }
            }
        }
    }

    fun addOnRecyclerViewInfinite(rv:RecyclerView){
        rv.apply {
            // 스크롤 감지 및 로딩..
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if(isEnd) return
                    if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                        // init
                        val layoutManager = recyclerView.layoutManager
                        val adapter = recyclerView.adapter
                        if (layoutManager!!.childCount > 0) {
                            // Calculations..
                            val indexOfLastItemViewVisible = layoutManager.childCount - 1
                            val lastItemViewVisible = layoutManager.getChildAt(indexOfLastItemViewVisible)
                            val adapterPosition = layoutManager.getPosition(lastItemViewVisible!!)
                            val isLastItemVisible = adapterPosition == adapter!!.itemCount - 1

                            // check
                            if (isLastItemVisible) {  // callback
                                when(isCalling){
                                    true->{ errorData.postValue("정보를 불러오는 중입니다. 잠시 후 시도해주세요.") }
                                    false->{ searchBook() }
                                }
                            }
                        }

                    }
                }
            })
        }
    }

}
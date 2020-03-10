package com.example.contributors.viewmodel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.example.contributors.ContributorRepository
import com.example.contributors.model.ContributorDetail
import kotlinx.coroutines.launch

class ContributorDetailViewModel (
    private val myApplication: Application,
    private val userID : String
) : AndroidViewModel(myApplication){

    private val repository = ContributorRepository.instance
    val contributorDetailLiveData: MutableLiveData<ContributorDetail> = MutableLiveData()

    var contributorDetail = ObservableField<ContributorDetail>()
    
    init {
        loadContributorDetail()
    }

    private fun loadContributorDetail() {
        viewModelScope.launch {
            try {
                val contributorDetail = repository.getContributorDetails(userID)
                if (contributorDetail.isSuccessful) {
                    contributorDetailLiveData.postValue(contributorDetail.body())
                }
            } catch (e: Exception) {
                Log.e("loadProject:Failed", e.stackTrace.toString())
            }
        }
    }

    fun setProject(contributorDetail: ContributorDetail) {
        this.contributorDetail.set(contributorDetail)
    }

    class Factory(private val application: Application,private val userID: String) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ContributorDetailViewModel(application,userID) as T
        }
    }
}

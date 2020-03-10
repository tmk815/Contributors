package com.example.contributors.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.contributors.ContributorRepository
import com.example.contributors.model.Contributor
import kotlinx.coroutines.launch

class ContributorListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ContributorRepository.instance

    var contributorListLiveData: MutableLiveData<List<Contributor>> = MutableLiveData()

    init {
        loadContributorList()
    }

    private fun loadContributorList() {
        viewModelScope.launch {
            try {
                val request = repository.getContributorList()
                if (request.isSuccessful) {
                    contributorListLiveData.postValue(request.body())
                }
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }
}

package com.example.testapp

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn

class RemoteViewModel(
    private val repository: ImageRepository = ImageRepository.getInstance()
) : ViewModel() {

    fun fetchImagesLiveData(): LiveData<PagingData<ImageModel>> {
        return repository.letImagesLiveData()
            .cachedIn(viewModelScope)
    }

}
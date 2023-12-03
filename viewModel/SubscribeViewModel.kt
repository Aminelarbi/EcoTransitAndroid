package com.example.ecotansit.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecotansit.models.Billet
import com.example.ecotansit.models.Subscribe

class SubscribeViewModel: ViewModel() {
    private val subscribe: MutableLiveData<List<Subscribe>> = MutableLiveData()
    private val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getSubscribe(): LiveData<List<Subscribe>> = subscribe
    fun getErrorMessage(): LiveData<String> = errorMessage
}
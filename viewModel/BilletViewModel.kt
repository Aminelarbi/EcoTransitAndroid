package com.example.ecotansit.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecotansit.models.Billet

class BilletViewModel: ViewModel() {
    private val billet: MutableLiveData<List<Billet>> = MutableLiveData()
    private val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getBillet(): LiveData<List<Billet>> = billet
    fun getErrorMessage(): LiveData<String> = errorMessage
}
package com.example.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel: ViewModel() {

    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

    init {
        _shoes.value = mutableListOf()
        Timber.plant(Timber.DebugTree())
        Timber.i("view model init is called")
    }

    /** Method for button press **/
    fun addShoe(shoe: Shoe){
        if(_shoes.value.isNullOrEmpty()){
            _shoes.value = mutableListOf()
            _shoes.value?.add(shoe)
        } else{
            _shoes.value?.add(shoe)
        }
        _shoes.value = _shoes.value
        Timber.i("Num of records after adding: ${_shoes.value!!.size}")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("view model cleared")
    }
}
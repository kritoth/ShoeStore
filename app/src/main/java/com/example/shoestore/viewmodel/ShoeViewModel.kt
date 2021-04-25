package com.example.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel: ViewModel() {

    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe>
        get() = _shoe

    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

    init {
        _shoes.value = mutableListOf()
        Timber.plant(Timber.DebugTree())
    }

    /** Method for button press **/
    fun addShoe(shoe: Shoe){
        _shoes.value?.add(shoe)
        _shoes.value = _shoes.value
        Timber.i("$shoe added?= $_shoes.value.value")
    }
}
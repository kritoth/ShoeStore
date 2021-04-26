package com.example.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestore.R
import com.example.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel: ViewModel() {

    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

    // Event which triggers the adding/canceling
    private val _eventFinish = MutableLiveData<Boolean>()
    val eventFinish: LiveData<Boolean>
        get() = _eventFinish

    init {
        _shoes.value = mutableListOf()
        Timber.plant(Timber.DebugTree())
        Timber.i("view model init is called")
    }

    /** Method for Save button press: saves the {@param Shoe} and triggers finish event **/
    fun addShoe(shoe: Shoe?){
        if(shoe != null) _shoes.value?.add(shoe)
        _shoes.value = _shoes.value
        _eventFinish.value = true
        Timber.i("Num of records after adding: ${_shoes.value!!.size}")
    }

    /** Method for Cancel button press: not saving anything only triggers finish event **/
    fun onCancel(){
        _eventFinish.value = true
    }

//    private fun addDefaultValues(shoe: Shoe): Shoe {
//        if(shoe.name.isEmpty()) shoe.name = context.getString(R.string.error_no_name) else shoe.name
//        if(shoe.company.isEmpty()) shoe.company = context.getString(R.string.error_no_company) else shoe.company
//        if(shoe.description.isEmpty()) shoe.description = context.getString(R.string.error_no_description) else shoe.description
//        return shoe
//    }

    /** Methods for completed events **/
    fun onFinished() {
        _eventFinish.value = false
    }


    override fun onCleared() {
        super.onCleared()
        Timber.i("view model cleared")
    }
}
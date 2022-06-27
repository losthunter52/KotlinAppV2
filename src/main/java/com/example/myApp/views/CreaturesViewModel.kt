package com.example.myApp.views

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myApp.data.repository.CreatureRepository
import kotlinx.coroutines.launch
import java.io.IOException

class CreaturesViewModel(private val repository: CreatureRepository) : ViewModel() {

    init {
        if(repository.creatures.value.isNullOrEmpty()){
            refreshDataFromRepository()
        }
    }

    val creatures = repository.creatures

    private val _eventNetworkError = MutableLiveData("")

    private fun refreshDataFromRepository(){
        viewModelScope.launch {
            try {
                repository.refreshCreature()
                _eventNetworkError.value = ""
            }catch (networkError: IOException){
                Log.d("Error", "${networkError.message}")
                _eventNetworkError.value = networkError.message
            }
        }


    }

}

class CreatureVMFactory(private val repository: CreatureRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CreaturesViewModel::class.java))
            return CreaturesViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
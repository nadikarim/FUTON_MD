package com.capstone.futon.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.futon.data.MainRepository
import com.capstone.futon.data.model.Plant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {
    val plant: LiveData<List<Plant>> = repository.plant
    val animal: LiveData<List<Plant>> = repository.animal

    fun listPlant() {
        viewModelScope.launch {
            repository.plantList()
        }
    }

    fun listAnimal() {
        viewModelScope.launch {
            repository.animalList()
        }
    }
}
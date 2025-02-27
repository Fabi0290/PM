package com.example.trab.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.trab.db.COMMRepository
import com.example.trab.entities.Comentarios
import kotlinx.coroutines.launch


class COMMViewModel(private val repository: COMMRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.

    // ERRORS HERE: NEED TO IMPLEMENT LIVEDATA IN BUILD GRADLE
    //val all: LiveData<List<Comentarios>> = repository.all.asLiveData()
    fun getComment(empresaName: String): LiveData<List<Comentarios>> {
        return repository.getComment(empresaName)
    }
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(comentarios: Comentarios) = viewModelScope.launch {
        repository.insert(comentarios)
    }


    fun deleteAll()=viewModelScope.launch {
        repository.deleteAll()
    }



}

class COMMViewModelFactory(private val repository: COMMRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(COMMViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return COMMViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
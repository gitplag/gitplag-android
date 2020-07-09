package io.gitplag.android.ui.repositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import io.gitplag.android.data.repository.RepositoryRepository
import io.gitplag.android.util.data.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class RepositoryListViewModel @Inject constructor(
    private val repositoryRepository: RepositoryRepository
) : ViewModel() {

    fun getRepositories() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repositoryRepository.getAllRepositories()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

}
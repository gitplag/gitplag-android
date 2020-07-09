package io.gitplag.android.ui.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import io.gitplag.android.data.repository.AnalysisRepository
import io.gitplag.android.data.repository.RepositoryRepository
import io.gitplag.android.util.data.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class RepositoryViewModel @Inject constructor(
    private val analysisRepository: AnalysisRepository,
    private val repositoryRepository: RepositoryRepository
) : ViewModel() {

    fun getAllAnalyzesOfRepository(repositoryId: Long) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = analysisRepository.getAllAnalyzesOfRepository(repositoryId)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

    fun getRepository(repositoryId: Long) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repositoryRepository.getRepository(repositoryId)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

}
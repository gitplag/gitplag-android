package io.gitplag.android.ui.analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import io.gitplag.android.data.repository.AnalysisRepository
import io.gitplag.android.util.data.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class AnalysisViewModel @Inject constructor(
    private val analysisRepository: AnalysisRepository
) : ViewModel() {

    fun getAnalysis(analysisId: Long) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = analysisRepository.getAnalysis(analysisId)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

}
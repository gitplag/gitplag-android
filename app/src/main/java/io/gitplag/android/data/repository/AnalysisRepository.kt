package io.gitplag.android.data.repository

import io.gitplag.android.data.client.GitplagClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnalysisRepository @Inject constructor(
    private val gitplagClient: GitplagClient
) {

    fun getAnalysis(analysisId: Long) = gitplagClient.getAnalysis(analysisId)

    fun getAllAnalyzesOfRepository(repositoryId: Long) = gitplagClient.getRepositoryAnalyzes(repositoryId)

}
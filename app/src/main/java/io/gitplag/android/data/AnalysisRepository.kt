package io.gitplag.android.data

import io.gitplag.android.client.GitplagClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnalysisRepository @Inject constructor(
    private val gitplagClient: GitplagClient
) {

    fun getAnalysis(analysisId: Long) = gitplagClient.getAnalysis(analysisId)

    fun getAllAnalyzes(repositoryId: Long) = gitplagClient.getRepositoryAnalyzes(repositoryId)

}
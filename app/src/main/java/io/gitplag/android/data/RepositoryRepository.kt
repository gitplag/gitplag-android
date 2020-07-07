package io.gitplag.android.data

import io.gitplag.android.client.GitplagClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryRepository @Inject constructor(
    private val gitplagClient: GitplagClient
) {

    fun getRepository(repositoryId: Long) = gitplagClient.getRepository(repositoryId)

    fun getAllRepositories() = gitplagClient.getRepositories()

}
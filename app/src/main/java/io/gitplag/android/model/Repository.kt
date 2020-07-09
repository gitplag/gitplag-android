package io.gitplag.android.model

data class Repository(
    override val id: Long,
    val filePatterns: List<String>,
    val name: String,
    val analyzer: String,
    val gitService: String,
    val language: String,
    val analysisMode: String,
    val autoCloningEnabled: Boolean
) : Identifiable
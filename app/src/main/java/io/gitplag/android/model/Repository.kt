package io.gitplag.android.model

class Repository(
    val id: Long,
    val filePatterns: Array<String>,
    val name: String,
    val analyzer: String,
    val gitService: String,
    val language: String,
    val analysisMode: String,
    val autoCloningEnabled: Boolean
)
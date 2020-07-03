package io.gitplag.gitplag.android

class Repository(
    val id: Int,
    val filePatterns: Array<String>,
    val name: String,
    val analyzer: String,
    val gitService: String,
    val language: String,
    val analysisMode: String,
    val autoCloningEnabled: Boolean
)
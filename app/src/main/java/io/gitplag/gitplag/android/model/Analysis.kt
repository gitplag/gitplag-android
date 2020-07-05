package io.gitplag.gitplag.android.model

class Analysis(
    val id: Long,
    val repo: Long,
    val repoName: String,
    val analyzer: String,
    val branch: String,
    val date: String,
    val resultLink: String,
    val analysisPairs: List<AnalysisPair>
)
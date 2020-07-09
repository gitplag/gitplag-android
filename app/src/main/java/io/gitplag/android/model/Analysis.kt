package io.gitplag.android.model

data class Analysis(
    override val id: Long,
    val repo: Long,
    val repoName: String,
    val analyzer: String,
    val branch: String,
    val date: String,
    val resultLink: String,
    val analysisPairs: List<AnalysisPair>
) : Identifiable
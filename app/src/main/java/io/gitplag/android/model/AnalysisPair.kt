package io.gitplag.android.model

data class AnalysisPair(
    override val id: Long,
    val student1: String,
    val student2: String,
    val percentage: Int,
    val minPercentage: Int,
    val maxPercentage: Int,
    val createdAt1: String,
    val createdAt2: String
) : Identifiable
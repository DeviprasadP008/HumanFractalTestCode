package com.humanfractal.testapplication.data.responses

data class Drug(
    val brandNm: String,
    val dosage: Dosage,
    val genericNm: String,
    val qty: Int
)
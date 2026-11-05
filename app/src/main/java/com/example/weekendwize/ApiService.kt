package com.example.weekendwize

import retrofit2.http.GET

// Model for the Advice Slip REST API (English)
data class AdviceSlip(
    val slip: AdviceContent
)

data class AdviceContent(
    val id: Int,
    val advice: String
)

interface ApiService {
    // Fetches a random advice slip in English
    @GET("advice")
    suspend fun getRandomAdvice(): AdviceSlip
}

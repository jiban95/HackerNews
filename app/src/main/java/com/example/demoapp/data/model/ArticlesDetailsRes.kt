package com.example.demoapp.data.model

data class ArticlesDetailsRes(
    val `by`: String,
    val descendants: Int,
    val id: Int,
    val score: Int,
    val time: Int,
    val title: String,
    val type: String,
    val url: String
)
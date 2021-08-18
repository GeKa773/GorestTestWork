package com.gekaradchenko.goresttestwork.network

data class NetModel(
    val code: Int,
    val `data`: List<Data>,
    val meta: Meta
)
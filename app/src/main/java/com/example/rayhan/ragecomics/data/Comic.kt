package com.example.rayhan.ragecomics.data

import java.io.Serializable

data class Comic(val imageResId: Int,
                 val name: String,
                 val description: String,
                 val url: String,
                 var text: String = "") : Serializable
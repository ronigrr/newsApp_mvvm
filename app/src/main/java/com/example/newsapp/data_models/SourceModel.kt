package com.example.newsapp.data_models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SourceModel(
    @Expose
    @SerializedName("id")
    val id: String? = null,
    @Expose
    @SerializedName("name")
    val name: String? = null
)
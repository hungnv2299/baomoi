package com.hung.baomoi.video.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Videos")
data class Video(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
    val source: String?,
    val publishedAt: String?
) : Serializable
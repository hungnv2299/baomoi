package com.hung.baomoi.news.data.local

import androidx.room.TypeConverter
import com.hung.baomoi.news.data.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name:String) : Source {
        return Source(name, name)
    }
}
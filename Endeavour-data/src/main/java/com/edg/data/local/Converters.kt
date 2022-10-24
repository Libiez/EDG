package com.edg.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.edg.data.util.JsonParser
import com.edg.domain.models.products.Price
import com.google.gson.reflect.TypeToken


@ProvidedTypeConverter
class Converters(private val jsonParser: JsonParser) {

    @TypeConverter
    fun  fromMeaningsJson(json:String): List<Price> {
        return jsonParser.fromJson<ArrayList<Price>>(json,
            object : TypeToken<ArrayList<Price>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningsJson(meanings: List<Price>): String {
        return  jsonParser.toJson(meanings,
            object : TypeToken<ArrayList<Price>>(){}.type
        ) ?: "[]"
    }
}
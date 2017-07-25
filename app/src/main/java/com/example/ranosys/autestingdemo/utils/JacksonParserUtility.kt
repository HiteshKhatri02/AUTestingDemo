package com.example.ranosys.autestingdemo.utils

import android.util.SparseArray
import android.util.SparseIntArray
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature


/**
 * @author Hitesh Khatri
 */
object JacksonParserUtility {

    //Method which convert array into string
    fun convertIntSparseArrayToString(list:SparseIntArray):String{
        val mapper = ObjectMapper()
        mapper.enable(SerializationFeature.INDENT_OUTPUT)
        return mapper.writeValueAsString(list)
    }

    fun convertCarStringToIntSparseArray(string: String):SparseIntArray{
        val mapper = ObjectMapper()
        return mapper.readValue(string,SparseIntArray::class.java)
    }
}
package com.example.ranosys.autestingdemo.utils.Schedulers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature


/**
 * @author Hitesh Khatri
 */
object JacksonParserUtility {

    //Method which convert array into string
    fun convertCarIdsInJSONString(list:ArrayList<Int>):String{
        val mapper = ObjectMapper()
        mapper.enable(SerializationFeature.INDENT_OUTPUT)
        return mapper.writeValueAsString(list)
    }

    fun convertCarIdsStringToArray(string: String):ArrayList<Int>{
        val mapper = ObjectMapper()
        return mapper.readValue(string,ArrayList<Int>().javaClass)
    }
}
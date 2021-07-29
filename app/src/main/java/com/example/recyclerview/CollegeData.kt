package com.example.recyclerview

import org.json.JSONArray

data class CollegeData(

    val domains: JSONArray,
    val web_pages: JSONArray,
    val name: String,
    val alpha_two_code: String,
    val stateProvince: String,
    val country: String
)
//@Serializable
//data class Json4Kotlin_Base (
//
//    @SerializedName("domains") val domains : List<String>,
//    @SerializedName("web_pages") val web_pages : List<String>,
//    @SerializedName("name") val name : String,
//    @SerializedName("alpha_two_code") val alpha_two_code : String,
//    @SerializedName("state-province") val stateProvince : String,
//    @SerializedName("country") val country : String
//)
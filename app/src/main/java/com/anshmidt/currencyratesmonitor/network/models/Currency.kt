package com.anshmidt.currencyratesmonitor.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Currency (
        @SerializedName("ID")
        @Expose
        val id: String,
    
        @SerializedName("NumCode")
        @Expose
        val numCode: String,
    
        @SerializedName("CharCode")
        @Expose
        val charCode: String,
    
        @SerializedName("Nominal")
        @Expose
        val nominal: Int,
    
        @SerializedName("Name")
        @Expose
        val name: String,
    
        @SerializedName("Value")
        @Expose
        val value: Float,
    
        @SerializedName("Previous")
        @Expose
        val previous: Float
)

//{
//
//    @SerializedName("ID")
//    @Expose
//    val id: String? = null
//
//    @SerializedName("NumCode")
//    @Expose
//    val numCode: String? = null
//
//    @SerializedName("CharCode")
//    @Expose
//    val charCode: String? = null
//
//    @SerializedName("Nominal")
//    @Expose
//    val nominal: Int? = null
//
//    @SerializedName("Name")
//    @Expose
//    val name: String? = null
//
//    @SerializedName("Value")
//    @Expose
//    val value: Float = 0.toFloat()
//
//    @SerializedName("Previous")
//    @Expose
//    val previous: Float = 0.toFloat()
//}

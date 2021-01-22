package com.madisadyk.mentorship_android_kotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User(
        @field:Expose @field:SerializedName("username")
        var username: String, @field:Expose @field:SerializedName("email")
        var email: String, @field:Expose @field:SerializedName("phone_number")
        var phoneNumber: String, @field:Expose @field:SerializedName("password")
        var password: String, @field:Expose @field:SerializedName("first_name")
        var firstName: String, @field:Expose @field:SerializedName("last_name")
        var lastName: String, @field:Expose @field:SerializedName("country")
        var country: String, @field:Expose @field:SerializedName("city")
        var city: String) {
        @SerializedName("_id")
        @Expose
        var userId: String? = null
}

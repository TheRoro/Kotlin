package com.mobile.appfutbol.model

import com.google.gson.annotations.SerializedName

class ApiResponseDetails (
    var results: Int,
    @SerializedName("teams")
    var teams: List<Team>
)
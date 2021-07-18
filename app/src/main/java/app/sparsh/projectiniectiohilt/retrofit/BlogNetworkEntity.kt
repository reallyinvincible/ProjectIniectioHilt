package app.sparsh.projectiniectiohilt.retrofit

import com.google.gson.annotations.SerializedName

data class BlogNetworkEntity(
    @SerializedName("pk")
    var id: Int = 0,
    @SerializedName("title")
    var title: String = "",
    @SerializedName("body")
    var body: String = "",
    @SerializedName("image")
    var image: String = "",
    @SerializedName("category")
    var category: String = "",
)

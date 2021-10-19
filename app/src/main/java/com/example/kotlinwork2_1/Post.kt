package com.example.kotlinwork2_1

data class Post(val id: Int,
                val author: String,
                val data: Long,
                val txt: String,
                val like: Boolean,
                val comment: Boolean,
                val share: Boolean,
                val likeTxt : Int,
                val commentTxt : Int,
                val shareTxt : Int,
                val adress : String,
                val coordinates : Pair<Double,Double>,
                val type: Types,
                val url: String? = null,
                val dateRepost: Long? = null,
                val autorRepost: String? = null,
                var hidePost: Boolean = false) {

    override fun toString(): String {
        return "$id : $hidePost"
    }
}
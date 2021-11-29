package com.example.master.repository.model

import kotlinx.serialization.*

@Serializable
data class NewsModule(
    var modules: List<News>
)

@Serializable
data class News(
    var type: String,
    var data: NewsData
)

@Serializable
data class NewsData(
    val items: List<Story>
)

@Serializable
data class Story(
    val id: Int,
    val type: StoryType,
    var headline: StoryTitle
)

@Serializable
data class StoryTitle(
    val mobile: String
)

@Serializable
data class StoryType(
    val description: String
)
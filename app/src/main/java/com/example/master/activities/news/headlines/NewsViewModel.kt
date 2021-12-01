package com.example.master.activities.news.headlines

import android.content.Context
import com.example.master.ScreenState
import com.example.master.repository.model.NewsModule
import com.example.master.repository.model.Story
import com.example.master.ui.base.BaseViewModel
import com.google.gson.Gson
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import java.io.IOException

class NewsViewModel(applicationContext: Context) : BaseViewModel<NewsViewModel>() {

    private val currentState = BehaviorSubject.createDefault(ScreenState.LOADING_IN_PROGRESS)
    private val context = applicationContext

    val visibilityStatus = BehaviorSubject.createDefault(false)
    var headLines = BehaviorSubject.create<List<Story>>()

    init {
        currentState.subscribe {
            when (it) {
                ScreenState.LOADING_IN_PROGRESS -> {
                    visibilityStatus.onNext(it == ScreenState.LOADING_COMPLETE)
                }
                ScreenState.LOADING_COMPLETE -> {
                    visibilityStatus.onNext(it == ScreenState.LOADING_COMPLETE)
                }
                else -> {
                }
            }
        }.addTo(bag)

        headLines.onNext(fetchNewsData())
    }

    private fun fetchNewsData(): List<Story> {
        val storyList = mutableListOf<Story>()
        fetchJsonData().modules.iterator().forEach { news ->
            news.data.items.forEach { story ->
                run {
                    val url =
                        "https://e0.365dm.com/" + story.media[0].links.fileReference.replace(
                            "{width}",
                            "768"
                        ).replace("{height}", "432")
                    storyList.add(
                        Story(
                            id = story.id,
                            type = story.type,
                            headline = story.headline,
                            media = story.media,
                            imageUrl = url
                        )
                    )
                }
            }
        }
        val finalList = mutableListOf<Story>()
        finalList.add(storyList[0])
        finalList.addAll(1, storyList.subList(1, storyList.size).shuffled())
        return finalList
    }

    private fun fetchJsonData(): NewsModule {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("top-stories.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            currentState.onNext(ScreenState.LOADING_IN_PROGRESS)
            ioException.printStackTrace()
        }
        return Gson().fromJson(jsonString, NewsModule::class.java)
    }

    override fun onCleared() {
        super.onCleared()
        bag.dispose()
    }

    fun setLoadingComplete() {
        currentState.onNext(ScreenState.LOADING_COMPLETE)
    }
}
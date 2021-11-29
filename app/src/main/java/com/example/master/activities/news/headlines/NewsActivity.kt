package com.example.master.activities.news.headlines

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.master.R
import com.example.master.adapters.CustomNewsStoryAdapter
import com.example.master.databinding.ActivityNewsBinding
import com.example.master.repository.model.Story
import com.example.master.ui.base.BaseActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class NewsActivity : BaseActivity<ActivityNewsBinding>() {

    private val bag = CompositeDisposable()
    private val viewModel: NewsViewModel by inject {
        parametersOf(this)
    }

    private lateinit var customNewsStoryAdapter: CustomNewsStoryAdapter

    override fun provideLayoutResId(): Int {
        return R.layout.activity_news
    }

    override fun setupDataBinding() {
        super.setupDataBinding()
        dataBinding.viewModel = viewModel
    }

    override fun setupViewModel() {
        viewModel.headLines.subscribe {
            if(it.isNotEmpty())
            {
                setViewAdapter(it)
                viewModel.setLoadingComplete()
            }
        }.addTo(bag)
    }

    private fun setViewAdapter(storyList: List<Story>) {
        customNewsStoryAdapter =
            CustomNewsStoryAdapter(
                this,
                storyList
            )

        dataBinding.headlinesList.let { recyclerView ->
            val context = this
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = customNewsStoryAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }
}
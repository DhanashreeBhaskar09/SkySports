package com.example.master.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.master.R
import com.example.master.adapters.ViewItemType.ITEM_STORY_NEWS
import com.example.master.adapters.ViewItemType.ITEM_STORY_VIDEO
import com.example.master.adapters.ViewType.ITEM_HEADLINE_HERO
import com.example.master.adapters.ViewType.ITEM_HEADLINE_STORY
import com.example.master.databinding.ItemHeadlineBinding
import com.example.master.repository.model.Story

object ViewType {
    const val ITEM_HEADLINE_HERO = 0
    const val ITEM_HEADLINE_STORY = 1
}

object ViewItemType {
    const val ITEM_STORY_NEWS = "News Story"
    const val ITEM_STORY_VIDEO = "Video"
}

class CustomNewsStoryAdapter(private val context: Context, private val dataList: List<Story>) :
    RecyclerView.Adapter<CustomNewsStoryAdapter.CustomViewHolder>() {

    inner class CustomViewHolder(binding: ItemHeadlineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var itemHeadlineBinding: ItemHeadlineBinding? = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_headline, parent, false)
        return CustomViewHolder(binding as ItemHeadlineBinding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        context.resources.run {
            dataList[position].let { story ->
                when (position) {
                    0 -> {
                        val binding = holder.itemHeadlineBinding
                        binding?.run {
                            this.itemHeroStoryParent.visibility = View.VISIBLE
                            this.itemVideoHeadlineParent.visibility = View.GONE
                            /*if (story.imageUrl != null) {
                                Glide.with(context)
                                    .load(story.imageUrl)
                                    .into(this.itemStoryImageView)
                                this.itemStoryImageView.visibility = View.VISIBLE
                            } else
                                this.itemStoryImageView.visibility = View.GONE*/

                            this.itemHeroStoryTitle.text = story.headline.mobile
                            executePendingBindings()
                        }
                    }
                    else -> {
                        val binding = holder.itemHeadlineBinding
                        binding?.run {
                            this.itemHeroStoryParent.visibility = View.GONE
                            this.itemVideoHeadlineParent.visibility = View.VISIBLE
                            this.itemVideoTitle.text = story.headline.mobile
                            /*if (story.imageUrl != null) {
                                Glide.with(context)
                                    .load(story.imageUrl)
                                    .into(this.itemHeadlineImage)
                                this.itemHeadlineImage.visibility = View.VISIBLE
                            } else
                                this.itemHeadlineImage.visibility = View.GONE*/

                            executePendingBindings()
                        }
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return ITEM_HEADLINE_HERO
        return ITEM_HEADLINE_STORY
    }
}

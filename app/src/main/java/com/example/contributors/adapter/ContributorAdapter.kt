package com.example.contributors.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contributors.ContributorClickCallback
import com.example.contributors.R
import com.example.contributors.model.Contributor
import com.example.contributors.databinding.ContributorListItemBinding

class ContributorAdapter(private val projectClickCallback: ContributorClickCallback?) :
    RecyclerView.Adapter<ContributorAdapter.ContributorViewHolder>() {

    private var contributorList: List<Contributor>? = null

    fun setProjectList(contributorList: List<Contributor>) {

        if (this.contributorList == null) {
            this.contributorList = contributorList

            notifyItemRangeInserted(0, contributorList.size)

        } else {

            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return requireNotNull(this@ContributorAdapter.contributorList).size
                }

                override fun getNewListSize(): Int {
                    return contributorList.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldList = this@ContributorAdapter.contributorList
                    return oldList?.get(oldItemPosition)?.id == contributorList[newItemPosition].id
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    val contributor = contributorList[newItemPosition]
                    val old = contributorList[oldItemPosition]

                    return contributor.id == old.id && contributor.avatar_url == old.avatar_url
                }
            })
            this.contributorList = contributorList

            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): ContributorViewHolder {
        val binding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.contributor_list_item, parent,
                false
            ) as ContributorListItemBinding

        binding.callback = projectClickCallback

        return ContributorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        holder.binding.contributor = contributorList?.get(position)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return contributorList?.size ?: 0
    }

    open class ContributorViewHolder(val binding: ContributorListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}

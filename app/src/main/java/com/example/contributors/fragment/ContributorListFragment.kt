package com.example.contributors.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.contributors.ContributorClickCallback
import com.example.contributors.MainActivity
import com.example.contributors.R
import com.example.contributors.adapter.ContributorAdapter
import com.example.contributors.databinding.FragmentContributorListBinding
import com.example.contributors.model.Contributor
import com.example.contributors.viewmodel.ContributorListViewModel
import com.squareup.picasso.Picasso


class ContributorListFragment : Fragment() {
    private val viewModel by lazy { ViewModelProviders.of(this).get(ContributorListViewModel::class.java) }

    private lateinit var binding: FragmentContributorListBinding
    private lateinit var contributorAdapter: ContributorAdapter

    private val contributorClickCallback = object : ContributorClickCallback {
        override fun onClick(contributor: Contributor) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) && activity is MainActivity) {
                (activity as MainActivity).show(contributor)
            }
        }
    }

    @BindingAdapter("loadImg")
    fun setImage(view: ImageView, url: String?) {
        Picasso.get().load(url).into(view)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //dataBinding用のレイアウトリソース
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contributor_list, container, false)

        contributorAdapter = ContributorAdapter(contributorClickCallback)

        binding.apply {
            contributorList.adapter = contributorAdapter
            isLoading = true
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeViewModel(viewModel)
    }

    //observe開始
    private fun observeViewModel(viewModel: ContributorListViewModel) {
        
        viewModel.contributorListLiveData.observe(viewLifecycleOwner, Observer { contributors ->
            if (contributors != null) {
                binding.isLoading = false
                contributorAdapter.setContributorList(contributors)
            }
        })
    }
}

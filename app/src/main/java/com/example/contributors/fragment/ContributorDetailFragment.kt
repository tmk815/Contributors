package com.example.contributors.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.contributors.R
import com.example.contributors.databinding.FragmentContributorDetailsBinding
import com.example.contributors.viewmodel.ContributorDetailViewModel

private const val KEY_CONTRIBUTOR_DETAIL_ID = "ContributorDetails"

class ContributorDetailFragment : Fragment(){
    private lateinit var binding: FragmentContributorDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contributor_details, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val userID = arguments?.getString(KEY_CONTRIBUTOR_DETAIL_ID)

        val factory = ContributorDetailViewModel.Factory(
            requireActivity().application, userID ?: ""
        )

        val viewModel = ViewModelProviders.of(this, factory).get(ContributorDetailViewModel::class.java)

        binding.apply {
            contributorDetailViewModel = viewModel
            isLoading = true
        }

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ContributorDetailViewModel) {
        viewModel.contributorDetailLiveData.observe(viewLifecycleOwner, Observer { contributorDetail ->
            if (contributorDetail != null) {

                binding.isLoading = false
                viewModel.setPContributorDetail(contributorDetail)
            }
        })
    }

    companion object {
        fun forContributor(userID: String): ContributorDetailFragment {
            val fragment = ContributorDetailFragment()
            val args = Bundle()

            args.putString(KEY_CONTRIBUTOR_DETAIL_ID, userID)
            fragment.arguments = args

            return fragment
        }
    }
}

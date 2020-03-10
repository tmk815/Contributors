package com.example.contributors

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contributors.fragment.ContributorDetailFragment
import com.example.contributors.fragment.ContributorListFragment
import com.example.contributors.model.Contributor
import com.example.contributors.model.ContributorDetail

class MainActivity : AppCompatActivity() {
    companion object {
        const val CONTRIBUTOR_LIST_FRAGMENT = "CONTRIBUTOR"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = ContributorListFragment()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment, CONTRIBUTOR_LIST_FRAGMENT)
                .commit()
        }
    }

    //詳細画面への遷移
    fun show(contributor: Contributor) {
        val contributorDetailFragment = ContributorDetailFragment.forContributor(contributor.login)

        supportFragmentManager
            .beginTransaction()
            .addToBackStack("contributor")
            .replace(R.id.fragment_container, contributorDetailFragment, null)
            .commit()
    }
}

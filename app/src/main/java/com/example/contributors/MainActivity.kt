package com.example.contributors

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contributors.fragment.ContributorListFragment
import com.example.contributors.model.Contributor

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
}

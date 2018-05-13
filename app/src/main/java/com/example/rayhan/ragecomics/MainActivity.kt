package com.example.rayhan.ragecomics

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rayhan.ragecomics.data.Comic

class MainActivity : AppCompatActivity(), RageComicListFragment.OnRageComicSelected {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.root_layout, RageComicListFragment.newInstance(),
                            "rageComicList")
                    .commit()
        }
    }

    override fun onRageComicSelected(comic: Comic) {
        val detailsFragment = RageComicDetailsFragment.newInstance(comic)

        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right,
                        R.anim.exit_to_left,
                        R.anim.enter_from_left,
                        R.anim.exit_to_right)
                .replace(R.id.root_layout, detailsFragment, "rageComicDetails")
                .addToBackStack(null)
                .commit()
    }

}

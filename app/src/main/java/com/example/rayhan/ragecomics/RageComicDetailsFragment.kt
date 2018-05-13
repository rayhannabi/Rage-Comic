package com.example.rayhan.ragecomics

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rayhan.ragecomics.data.Comic
import com.example.rayhan.ragecomics.databinding.FragmentRageComicDetailsBinding

class RageComicDetailsFragment : Fragment() {

    companion object {
        private const val COMIC = "comic"

        fun newInstance(comic: Comic): RageComicDetailsFragment {
            val args = Bundle()
            args.putSerializable(COMIC, comic)

            val fragment = RageComicDetailsFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val fragmentRageComicDetailsBinding = FragmentRageComicDetailsBinding.inflate(inflater!!,
                container, false)

        val comic = arguments.getSerializable(COMIC) as Comic
        fragmentRageComicDetailsBinding.comic = comic

        comic.text = String.format(getString(R.string.description_format), comic.description,
                comic.url)

        return fragmentRageComicDetailsBinding.root
    }
}
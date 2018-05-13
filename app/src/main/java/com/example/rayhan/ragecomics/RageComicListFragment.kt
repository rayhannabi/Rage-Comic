package com.example.rayhan.ragecomics

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rayhan.ragecomics.data.Comic
import com.example.rayhan.ragecomics.databinding.RecyclerItemRageComicBinding

class RageComicListFragment : Fragment() {

    private lateinit var imageResIds: IntArray
    private lateinit var names: Array<String>
    private lateinit var descriptions: Array<String>
    private lateinit var urls: Array<String>

    private lateinit var listener: OnRageComicSelected

    companion object {
        fun newInstance(): RageComicListFragment {
            return RageComicListFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnRageComicSelected) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnRageComicSelected")
        }

        val resources = context.resources
        names = resources.getStringArray(R.array.names)
        descriptions = resources.getStringArray(R.array.descriptions)
        urls = resources.getStringArray(R.array.urls)

        val typedArray = resources.obtainTypedArray(R.array.images)
        val imageCount = names.size
        imageResIds = IntArray(imageCount)

        for (i in 0 until imageCount) {
            imageResIds[i] = typedArray.getResourceId(i, 0)
        }

        typedArray.recycle()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater!!.inflate(R.layout.fragment_rage_comic_list, container, false)
        val activity = activity

        val recyclerView = view.findViewById(R.id.recycler_view) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = RageComicAdapter(activity)

        return view
    }

    internal inner class ViewHolder constructor(itemView: View, val recyclerItemRageComicBinding: RecyclerItemRageComicBinding) : RecyclerView.ViewHolder(itemView) {

        fun setData(comic: Comic) {
            recyclerItemRageComicBinding.comic = comic
        }
    }

    internal inner class RageComicAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {

        private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            val recyclerItemRageComicBinding =
                    RecyclerItemRageComicBinding.inflate(layoutInflater, parent, false)

            return ViewHolder(recyclerItemRageComicBinding.root, recyclerItemRageComicBinding)
        }

        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            val comic = Comic(imageResIds[position], names[position], descriptions[position],
                    urls[position])
            holder!!.setData(comic)
            holder.itemView.setOnClickListener { listener.onRageComicSelected(comic) }
        }

        override fun getItemCount(): Int {
            return names.count()
        }
    }

    interface OnRageComicSelected {
        fun onRageComicSelected(comic: Comic)
    }
}
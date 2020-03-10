package ru.droidcat.civilizationfaq

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import java.util.ArrayList

class DetailedView: Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var listitems: ArrayList<Tech>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.detailed_view, container, false)

        if(arguments != null) {
            listitems = arguments!!.getParcelableArrayList<Tech>("TechList")!!
            val position = arguments!!.getInt("pos", 0)
            viewPager = view.findViewById(R.id.pager)
            viewPager.adapter = ViewPagerAdapter(listitems, context!!)
            viewPager.setPageTransformer(MarginPageTransformer((Resources.getSystem().displayMetrics.density * 16).toInt()))
            viewPager.setCurrentItem(position, false)
        }

        return view
    }
}
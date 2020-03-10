package ru.droidcat.civilizationfaq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class MainActivity : Fragment() {

    private var listitems: ArrayList<Tech> = ArrayList()
    private lateinit var recyclerView : RecyclerView
    private lateinit var listAdapter: TechListAdapter

    private val onItemClickListener: View.OnClickListener = View.OnClickListener { view ->
        val viewHolder = view.tag as RecyclerView.ViewHolder
        val position = viewHolder.adapterPosition
        openDetailedView(position, view)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.activity_main, container, false)
        val alertTextView: TextView = view.findViewById(R.id.alert)

        alertTextView.text = ""

        if(arguments != null) {
            listitems = arguments!!.getParcelableArrayList<Tech>("TechList")!!
            listAdapter = TechListAdapter(listitems, context!!)
            listAdapter.mOnItemClickListener = onItemClickListener

            if(listitems.isEmpty()) {
                alertTextView.text = getString(R.string.connection_error)
            }

            recyclerView = view.findViewById(R.id.tech_list)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = listAdapter
        }

        return view
    }

    private fun openDetailedView(pos: Int, view: View) {
        val args = Bundle()
        args.putInt("pos", pos)
        args.putParcelableArrayList("TechList", listitems)
        Navigation.findNavController(view).navigate(R.id.action_mainActivity_to_detailedView, args)
    }
}
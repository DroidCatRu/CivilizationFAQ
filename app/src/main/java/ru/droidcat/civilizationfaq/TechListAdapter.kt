package ru.droidcat.civilizationfaq

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.android.synthetic.main.tech_list_item.view.*

class TechListAdapter(private val items : ArrayList<Tech>, private val context: Context) : RecyclerView.Adapter<TechListAdapter.ViewHolder>() {

    private val baseurl = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/"
    lateinit var mOnItemClickListener: View.OnClickListener

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.tech_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title?.text = items[position].name
        holder.image?.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_cloud_off_black_24dp))
        holder.itemView.tag = holder
        holder.itemView.setOnClickListener(mOnItemClickListener)
        if(items[position].graphic != null) {
            val imgurl = baseurl + items[position].graphic
            Glide.with(context)
                .asBitmap()
                .load(imgurl)
                .into(object : SimpleTarget<Bitmap>(100, 100) {
                    override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                        holder.image?.setImageBitmap(resource)
                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        Log.i("Connection", "Can't load image")
                    }
                })
        }
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView? = view.list_item_text
        val image: ShapeableImageView? = view.shapeableImageView
    }
}
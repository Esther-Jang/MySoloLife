package kr.ac.kyonggi.mysololife.contentsList

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.ac.kyonggi.mysololife.R
import kr.ac.kyonggi.mysololife.utils.FBAuth
import kr.ac.kyonggi.mysololife.utils.FBRef

class BookmarkRVAdapter (val context : Context,
                         val items : ArrayList<ContentModel>,
                         val keyList : ArrayList<String>,
                         val bookmarkIdList : MutableList<String>)
    : RecyclerView.Adapter<BookmarkRVAdapter.Viewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkRVAdapter.Viewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item, parent, false)
        return Viewholder(v)
    }

    override fun onBindViewHolder(holder: BookmarkRVAdapter.Viewholder, position: Int) {

        holder.bindItem(items[position], keyList[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(item : ContentModel, key: String) {

            itemView.setOnClickListener{
                val intent = Intent(context, ContentListActivity::class.java)
                intent.putExtra("url", item.webUrl)
                itemView.context.startActivity(intent)
            }

            val contentTitle = itemView.findViewById<TextView>(R.id.textArea)
            contentTitle.text = item.title

            val imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)
            Glide.with(context).load(item.imageUrl).into(imageViewArea)

            val bookmarkArea = itemView.findViewById<ImageView>(R.id.bookmarkArea)
            if(bookmarkIdList.contains(key)){
                bookmarkArea.setImageResource(R.drawable.bookmark_color)
            } else {
                bookmarkArea.setImageResource(R.drawable.bookmark_white)
            }
        }
    }
}
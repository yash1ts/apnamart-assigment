package com.example.testapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.bumptech.glide.request.RequestOptions

class RemoteImageAdapter :
    PagingDataAdapter<ImageModel, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<ImageModel>() {
            override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean =
                oldItem.id == newItem.id
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ImageViewHolder)?.bind(item = getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageViewHolder.getInstance(parent)
    }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {
            fun getInstance(parent: ViewGroup): ImageViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_image, parent, false)
                return ImageViewHolder(view)
            }
        }

        private var imageItem: ImageView = view.findViewById(R.id.image)
        private var imageAuthor: TextView = view.findViewById(R.id.image_author)
        private var imageSize: TextView = view.findViewById(R.id.image_size)

        @SuppressLint("CheckResult", "SetTextI18n")
        fun bind(item: ImageModel?) {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_baseline_image_24)
            requestOptions.error(R.drawable.ic_baseline_broken_image_24)
            Glide.with(itemView).setDefaultRequestOptions(requestOptions).load(item?.download_url).into(imageItem)
            imageAuthor.text = item?.author
            imageSize.text = "${item?.width.toString()}X${item?.height.toString()}"
        }

    }


}
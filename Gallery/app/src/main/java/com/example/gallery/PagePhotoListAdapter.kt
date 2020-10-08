package com.example.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.pager_photo_view.view.*

class PagePhotoListAdapter : ListAdapter<PhotoItem, PagerPhotoViewHolder>(DIFFCALLBACK) {
    object DIFFCALLBACK : DiffUtil.ItemCallback<PhotoItem>() {
        override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem.photoId == newItem.photoId
        }
    }



    override fun onBindViewHolder(holder: PagerPhotoViewHolder, position: Int) {
        /*        holder.itemView.shimmerLayoutCell.apply {
            setShimmerColor(0x55FFFFFF)
            setShimmerAngle(0)
            startShimmerAnimation()
        }*/
        Glide.with(holder.itemView)
            .load(getItem(position).fullUrl)
            .placeholder(R.drawable.ic_photo_grey_24)
/*            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false.also {
                        holder.itemView.shimmerLayoutCell?.stopShimmerAnimation()
                    }
                }

            })*/
            .into(holder.itemView.pagePhoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerPhotoViewHolder {

            LayoutInflater.from(parent.context).inflate(R.layout.pager_photo_view, parent, false).apply {
                return PagerPhotoViewHolder(this)
            }

/*        holder.itemView.setOnClickListener {
            Bundle().apply {
                putParcelable("PHOTO",getItem(holder.adapterPosition))
                holder.itemView.findNavController().navigate(R.id.action_galleryFragment_to_pagerPtotoFragment,this)
            }
        }*/


    }
}

class PagerPhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
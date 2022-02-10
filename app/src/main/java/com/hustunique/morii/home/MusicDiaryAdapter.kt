package com.hustunique.morii.home

import android.content.Intent
import android.app.Activity
import android.annotation.SuppressLint
import android.widget.TextView
import morii.R
import com.bumptech.glide.Glide
import com.hustunique.morii.util.MyApplication
import com.hustunique.morii.content.ContentActivity
import android.app.ActivityOptions
import com.hustunique.morii.home.MusicDiaryAdapter.MyViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.content.DialogInterface
import com.hustunique.morii.util.DatabaseUtil
import com.google.android.material.imageview.ShapeableImageView
import android.util.Log
import android.util.Pair
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import morii.databinding.CardviewItemBinding


class MusicDiaryAdapter
    (private val activity: Activity, private val list: MutableList<MusicDiaryItem>) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: CardviewItemBinding =
            CardviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val musicDiaryItem = list[position]
        val imagePath = musicDiaryItem.imagePath
        if (null != imagePath) {
            Glide.with(holder.itemView).load(imagePath)
                .into(holder.itemBinding.PhotoTitle)
            Log.d(TAG, "load image success")
        } else {
            Glide.with(holder.itemView)
                .load(
                    MyApplication.musicTabList[musicDiaryItem.musicTabId]
                        .imageResId
                )
                .into(holder.itemBinding.PhotoTitle)
            Log.d(
                TAG,
                "onBindViewHolder: " + MyApplication.musicTabList[musicDiaryItem.musicTabId]
                    .imageResId
            )
        }
        holder.itemBinding.TextTitle.text = musicDiaryItem.title
        holder.itemBinding.TextDate.text = "# " + musicDiaryItem.date
        Log.d("RECYCLER", position.toString())
        holder.itemView.setOnClickListener {
            val intent = Intent(activity, ContentActivity::class.java)
            intent.putExtra("diary", musicDiaryItem)
            val options = ActivityOptions
                .makeSceneTransitionAnimation(
                    activity,
                    Pair.create(holder.itemBinding.PhotoTitle, "photo")
                )
            activity.startActivity(intent, options.toBundle())
        }
        holder.itemView.setOnLongClickListener { v: View? ->
            val builder = MaterialAlertDialogBuilder(activity)
            builder.setTitle("确定删除这个音乐日记吗？")
                .setMessage("这个操作不可被撤销。")
                .setNegativeButton("取消") { dialog: DialogInterface?, which: Int -> }
                .setPositiveButton("确认") { dialog: DialogInterface?, which: Int ->
                    DatabaseUtil.deleteDiary(musicDiaryItem.itemID)
                    Log.d(TAG, "deletePosition: " + holder.layoutPosition)
                    notifyItemRemoved(holder.layoutPosition)
                    list.removeAt(holder.layoutPosition)
                }
                .show()
            true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    //内部类，绑定控件
    inner class MyViewHolder(val itemBinding: CardviewItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    companion object {
        private const val TAG = "MusicDiaryAdapter"
    }
}

package com.humanfractal.testapplication.ui.base

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.humanfractal.testapplication.R
import com.humanfractal.testapplication.data.responses.ScheduleX
import com.humanfractal.testapplication.data.responses.Task

class BaseUIAdapter(private var scheduleList: List<Task>, private val context: Context) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_TASKCOMPLETED = 0
        const val TYPE_TASKMEDICINE = 1
        const val TYPE_TASKVOD = 2
    }

    private inner class ViewHolderTaskCompleted(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var tv_title:TextView
        lateinit var tv_subtitle: TextView
        lateinit var iv_tasktype: ImageView
        lateinit var iv_taskstatus: ImageView
        init {
            tv_title=itemView.findViewById(R.id.tv_title)
            tv_subtitle=itemView.findViewById(R.id.tv_subtitle)
            iv_tasktype=itemView.findViewById(R.id.iv_tasktype)
            iv_taskstatus=itemView.findViewById(R.id.iv_taskstatus)
        }
        fun bind(position: Int) {
            val recyclerViewModel = scheduleList[position]
            tv_title.text = ""
            tv_subtitle.text = ""
        }
    }

    private inner class ViewHolderTypeMedicine(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var tv_title:TextView
        lateinit var tv_subtitle: TextView
        lateinit var tv_foodcontext: TextView
        lateinit var tv_actiontext: TextView
        lateinit var iv_tasktype: ImageView
        lateinit var iv_foodcontext: ImageView
        lateinit var ll_themebackground: LinearLayout
        init {
            tv_title=itemView.findViewById(R.id.tv_title)
            tv_subtitle=itemView.findViewById(R.id.tv_subtitle)
            tv_foodcontext=itemView.findViewById(R.id.tv_foodcontext)
            tv_actiontext=itemView.findViewById(R.id.tv_actiontext)
            iv_tasktype=itemView.findViewById(R.id.iv_tasktype)
            iv_foodcontext=itemView.findViewById(R.id.iv_foodcontext)
            ll_themebackground=itemView.findViewById(R.id.ll_themebackground)
        }
        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            val recyclerViewModel = scheduleList[position]
            ll_themebackground.setBackgroundColor(Color.parseColor("#F5F5F5"))
            iv_tasktype.setImageResource(R.drawable.img_typemedicine)
            iv_foodcontext.setImageResource(R.drawable.img_actionicon)
            tv_title.text = recyclerViewModel.drug.brandNm
            tv_subtitle.text = recyclerViewModel.drug.dosage.dose.toString() + " " + recyclerViewModel.drug.dosage.unit
            val foodContext: String = recyclerViewModel.scheduleList[0].foodContext
            tv_foodcontext.text = foodContext.toString()
            tv_actiontext.text = "TAKE"
            tv_actiontext.setOnClickListener {
                recyclerViewModel?.let {
                    println("Clicked Medicine is : " + it.drug.brandNm)
                    println("Clicked Medicine dosage is : " + it.drug.dosage.dose.toString() + " " + it.drug.dosage.unit)
                }
            }
        }
    }

    private inner class ViewHolderTypeVod(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var tv_title:TextView
        lateinit var tv_subtitle: TextView
        lateinit var tv_actiontext: TextView
        lateinit var iv_tasktype: ImageView
        lateinit var iv_videothumbnail: ImageView
        lateinit var ll_themebackground: LinearLayout
        init {
            tv_title=itemView.findViewById(R.id.tv_title)
            tv_subtitle=itemView.findViewById(R.id.tv_subtitle)
            tv_actiontext=itemView.findViewById(R.id.tv_actiontext)
            iv_tasktype=itemView.findViewById(R.id.iv_tasktype)
            iv_videothumbnail=itemView.findViewById(R.id.iv_videothumbnail)
            ll_themebackground=itemView.findViewById(R.id.ll_themebackground)
        }
        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            val recyclerViewModel = scheduleList[position]
            ll_themebackground.setBackgroundColor(Color.parseColor("#F5F5F5"))
            iv_tasktype.setImageResource(R.drawable.img_typevod)
            tv_title.text = recyclerViewModel.video.title
            tv_subtitle.text = recyclerViewModel.duration.toString() + " Mins"
            Glide.with(context)
                .load(recyclerViewModel.video.thumbnail)
                .placeholder(R.drawable.img_humanfractallogo)
                .error(R.drawable.img_humanfractallogo)
                .into(iv_videothumbnail)
            tv_actiontext.text = "WATCH"
            tv_actiontext.setOnClickListener {
                recyclerViewModel?.let {
                    println("Clicked Video title is : " + it.video.title)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_TASKCOMPLETED) {
            return ViewHolderTaskCompleted(
                LayoutInflater.from(context).inflate(R.layout.layout_child_taskcompleted, parent, false)
            )
        }
        if(viewType == TYPE_TASKMEDICINE){
            return ViewHolderTypeMedicine(
                LayoutInflater.from(context).inflate(R.layout.layout_child_medicine, parent, false)
            )
        }
        if(viewType == TYPE_TASKVOD){
            return ViewHolderTypeVod(
                LayoutInflater.from(context).inflate(R.layout.layout_child_vod, parent, false)
            )
        }
        return ViewHolderTaskCompleted(
            LayoutInflater.from(context).inflate(R.layout.layout_child_taskcompleted, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(scheduleList[position].type.equals("MEDICINE")){
            (holder as ViewHolderTypeMedicine).bind(position)
        }else if(scheduleList[position].type.equals("VOD")){
            (holder as ViewHolderTypeVod).bind(position)
        }else{
            (holder as ViewHolderTaskCompleted).bind(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(scheduleList[position].type.equals("MEDICINE")){
            return 1
        }else if(scheduleList[position].type.equals("VOD")){
            return 2
        }else{
            return 0
        }
    }

}




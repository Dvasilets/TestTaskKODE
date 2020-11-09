package com.vasiletsd.testtaskkode.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vasiletsd.testtaskkode.R
import java.text.SimpleDateFormat
import java.util.*

class HomeworkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var homeworkName: TextView? = null
    private var hwDescription: TextView? = null
    private var deadline : TextView? = null


    init {
        homeworkName = itemView.findViewById(R.id.homework_name)
        hwDescription = itemView.findViewById(R.id.homework_description_tv)
        deadline = itemView.findViewById(R.id.homework_deadline)
    }

    fun bind(item: Homework) {
        homeworkName?.text = item.name
        hwDescription?.text = item.description
        val deadlineString =  item.deadline.toString() + " days left"
        deadline?.text = deadlineString
    }

}
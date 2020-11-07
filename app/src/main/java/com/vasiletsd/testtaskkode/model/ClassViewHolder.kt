package com.vasiletsd.testtaskkode.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vasiletsd.testtaskkode.R
import java.text.SimpleDateFormat
import java.util.*

class ClassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var className: TextView? = null
    private var teachersLastName: TextView? = null
    private var teachersSex : TextView? = null
    private var description : TextView? = null
    private var timeStart : TextView? = null
    private var timeEnd : TextView? = null
    private var skypeImage : ImageView? = null
    private var skypeText : TextView? = null


    init {
        className = itemView.findViewById(R.id.class_name)
      //  teachersLastName = itemView.findViewById(R.id.)
      //  teachersSex = itemView.findViewById(R.id.)
      //  description = itemView.findViewById(R.id.)
        timeStart = itemView.findViewById(R.id.class_timeStart)
        timeEnd = itemView.findViewById(R.id.class_timeEnd)
        skypeImage = itemView.findViewById(R.id.image_button)
        skypeText = itemView.findViewById(R.id.open_in_text)


    }

    fun bind(item: Class) {
        className?.text = item.name
        val dateStartString = SimpleDateFormat("HH:mm").format(item.timeStart)
        val dateEndString = SimpleDateFormat("HH:mm").format(item.timeEnd)
        timeStart?.text = dateStartString
        timeEnd?.text = dateEndString
    }

}
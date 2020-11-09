package com.vasiletsd.testtaskkode.model

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.vasiletsd.testtaskkode.R
import com.vasiletsd.testtaskkode.util.listen
import com.vasiletsd.testtaskkode.util.orFalse
import kotlinx.android.synthetic.main.item_class.view.*
import kotlinx.android.synthetic.main.item_homework.view.*


class HomeworkAdapter(private val context: Context, private val homeworks: ArrayList<Homework>): RecyclerView.Adapter<HomeworkViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeworkViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.item_homework, parent, false)

        return HomeworkViewHolder(view).listen { pos, type ->
            val item = homeworks[pos]
            Toast.makeText(context, "This is " + item.name + " homework", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return homeworks.size ?: 0
    }

    override fun onBindViewHolder(holder: HomeworkViewHolder, position: Int) {
        if(homeworks[position].deadline != null) {
            if (homeworks[position].deadline!! < 4){
                holder.itemView.homework_deadline.setTextColor(Color.parseColor("#b61827"))
                holder.itemView.homework_deadline.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_access_time_24_red, 0, 0, 0)
            }
            else{
                holder.itemView.homework_deadline.setTextColor(Color.parseColor("#484e59"))
                holder.itemView.homework_deadline.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_access_time_24_dark, 0, 0, 0)
            }
        }
        homeworks.get(position).let {
            holder.bind(it)
        }
    }


}
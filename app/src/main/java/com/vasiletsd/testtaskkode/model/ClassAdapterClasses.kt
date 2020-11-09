package com.vasiletsd.testtaskkode.model

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView
import com.vasiletsd.testtaskkode.R
import com.vasiletsd.testtaskkode.util.listen
import com.vasiletsd.testtaskkode.util.orFalse
import kotlinx.android.synthetic.main.item_class.view.*
import java.util.*
import kotlin.collections.ArrayList

class ClassAdapterClasses(private val context: Context, private val allClasses: ArrayList<Class>): RecyclerView.Adapter<ClassViewHolder>(), Filterable {

    var classes : ArrayList<Class> = allClasses

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.item_class, parent, false)

        return ClassViewHolder(view).listen { pos, type ->
            val item = classes[pos]
            if(item.canBeOpenedInSkype.orFalse()){
                if(appInstalledOrNot("com.skype.raider")){
                    val launchIntent: Intent? = context.packageManager.getLaunchIntentForPackage("com.skype.raider")
                    if (launchIntent != null) {
                        context.startActivity(launchIntent)
                    }
                    Toast.makeText(context, "Smth went wrong", Toast.LENGTH_SHORT).show()
                }
                else
                    Toast.makeText(context, "Install Skype first!", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(context, "This is " + item.name + " class", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return classes.size ?: 0
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        holder.itemView.class_timeStart.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0 ,0)
        holder.itemView.class_timeStart.visibility = View.GONE
        holder.itemView.dash.visibility = View.GONE
        holder.itemView.class_timeEnd.visibility = View.GONE
        holder.itemView.crutch_tv.visibility = View.GONE



        val vclass = classes[position]


        if (vclass.canBeOpenedInSkype.orFalse()) {
            holder.itemView.image_button.visibility = View.VISIBLE
            holder.itemView.open_in_text.visibility = View.VISIBLE

        } else{
            holder.itemView.image_button.visibility = View.GONE
            holder.itemView.open_in_text.visibility = View.GONE
        }

        if (vclass.isNecessary.orFalse()){

            holder.itemView.class_card.setBackgroundResource(R.drawable.item_class_background)
            holder.itemView.class_icon.setImageResource(R.drawable.ic_baseline_menu_book_24_white)
            holder.itemView.class_icon.setBackgroundResource(R.drawable.class_icon_background_dark)
            holder.itemView.teacher_sex_tv.setTextColor(Color.parseColor("#484e59"))
            holder.itemView.teacher_lastname_tv.setTextColor(Color.parseColor("#484e59"))

        }
        else {

            holder.itemView.class_card.setBackgroundResource(R.drawable.countdown_background)
            holder.itemView.class_icon.setImageResource(R.drawable.ic_baseline_menu_book_24_dark)
            holder.itemView.class_icon.setBackgroundResource(R.drawable.class_icon_background_light)
            holder.itemView.teacher_sex_tv.setTextColor(Color.parseColor("#d0ffed"))
            holder.itemView.teacher_lastname_tv.setTextColor(Color.parseColor("#d0ffed"))

        }

        classes.get(position).let {
            holder.bind(it)
        }
    }

    override fun getFilter(): Filter = classesFilter

    private val classesFilter = object: Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val queryString = constraint?.toString()?.toLowerCase()
            val filterResults = Filter.FilterResults()
            filterResults.values = if (queryString==null || queryString.isEmpty())
                allClasses
            else
                allClasses.filter {vclass ->
                    vclass.description?.toLowerCase(Locale.ROOT)?.contains(queryString).orFalse() ||
                            vclass.teachersLastName?.toLowerCase(Locale.ROOT)?.contains(queryString).orFalse() ||
                            vclass.timeStart?.toString()?.toLowerCase(Locale.ROOT)?.contains(queryString).orFalse() ||
                            vclass.timeEnd?.toString()?.toLowerCase(Locale.ROOT)?.contains(queryString).orFalse() ||
                            vclass.name?.toLowerCase(Locale.ROOT)?.contains(queryString).orFalse()
                }
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            classes = results?.values as ArrayList<Class>
            notifyDataSetChanged()
        }

    }


    private fun appInstalledOrNot(uri: String): Boolean {
        val pm: PackageManager = context.packageManager
        return try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

}
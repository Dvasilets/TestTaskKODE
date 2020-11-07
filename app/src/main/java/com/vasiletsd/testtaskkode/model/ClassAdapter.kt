package com.vasiletsd.testtaskkode.model

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.vasiletsd.testtaskkode.R
import com.vasiletsd.testtaskkode.util.listen
import com.vasiletsd.testtaskkode.util.orFalse
import kotlinx.android.synthetic.main.item_class.view.*


class ClassAdapter(private val context: Context, private val allClasses: ArrayList<Class>): RecyclerView.Adapter<ClassViewHolder>() {

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

        val vclass = classes[position]

        if (vclass.canBeOpenedInSkype.orFalse()) {
            holder.itemView.image_button.visibility = View.VISIBLE
            holder.itemView.open_in_text.visibility = View.VISIBLE

        } else{
            holder.itemView.image_button.visibility = View.GONE
            holder.itemView.open_in_text.visibility = View.GONE
        }


        classes.get(position).let {
            holder.bind(it)
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
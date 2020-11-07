package com.vasiletsd.testtaskkode.ui.home

import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vasiletsd.testtaskkode.R
import com.vasiletsd.testtaskkode.model.Class
import com.vasiletsd.testtaskkode.model.ClassAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var classesAdapter : ClassAdapter
    private lateinit var classRecyclerView : RecyclerView

    private var classes = ArrayList<Class>()


    private val handler = Handler()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)




        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_home)


        classRecyclerView = root.findViewById(R.id.classes_rv)

        if (context != null) {
            classRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            classesAdapter = ClassAdapter(requireContext(), classes)
        }
        classRecyclerView.adapter = classesAdapter

        handler.post(object : Runnable {
            override fun run() {
                // Keep the postDelayed before the updateTime(), so when the event ends, the handler will stop too.
                handler.postDelayed(this, 1000)
                updateTime()
            }
        })


        classes.add(Class(
                "name", "teacherLastName", false, "description", true, Date(), Date(), false
        ))

        classes.add(Class(
                "name", "teacherLastName", false, "description", false, Date(), Date(), true
        ))

        return root
    }



    fun updateTime() {
        val currentDate = Calendar.getInstance()

        val eventDate = Calendar.getInstance()
        eventDate[Calendar.YEAR] = 2021
        eventDate[Calendar.MONTH] = 0 // 0-11 so 1 less
        eventDate[Calendar.DAY_OF_MONTH] = 1
        eventDate[Calendar.HOUR] = 0
        eventDate[Calendar.MINUTE] = 0
        eventDate.timeZone = TimeZone.getDefault()

        val diff = eventDate.timeInMillis - currentDate.timeInMillis

        val days = diff / (24 * 60 * 60 * 1000) %10
        val tenD = diff / (24 * 60 * 60 * 1000) /10
        val hours = diff / (1000 * 60 * 60) % 24 %10
        val tenH = diff / (1000 * 60 * 60) % 24 /10
        val minutes = diff / (1000 * 60) % 60 %10
        val tenM = diff / (1000 * 60) % 60 /10



        val string1: String = "$minutes"
        val string2: String = "$tenM"
        val string3: String = "$hours"
        val string4: String = "$tenH"
        val string5: String = "$days"
        val string6: String = "$tenD"

        countdown_minutes.text = string1
        countdown_ten_minutes.text = string2
        countdown_hours.text = string3
        countdown_ten_hours.text = string4
        countdown_days.text = string5
        countdown_ten_days.text = string6

    }
}
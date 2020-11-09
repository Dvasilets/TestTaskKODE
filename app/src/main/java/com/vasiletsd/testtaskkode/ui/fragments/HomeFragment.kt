package com.vasiletsd.testtaskkode.ui.fragments

import android.os.*
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vasiletsd.testtaskkode.R
import com.vasiletsd.testtaskkode.model.Class
import com.vasiletsd.testtaskkode.model.ClassAdapterHome
import com.vasiletsd.testtaskkode.model.Homework
import com.vasiletsd.testtaskkode.model.HomeworkAdapter
import com.vasiletsd.testtaskkode.util.fillClasses
import com.vasiletsd.testtaskkode.util.fillHomeworks
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class HomeFragment : Fragment() {



    private lateinit var classesAdapterHome : ClassAdapterHome
    private lateinit var homeworkAdapter : HomeworkAdapter
    private lateinit var classRecyclerView : RecyclerView
    private lateinit var homeworkRecyclerView : RecyclerView

    private lateinit var mintv : TextView
    private lateinit var tenmintv : TextView
    private lateinit var hourstv : TextView
    private lateinit var tenhourstv : TextView
    private lateinit var daystv : TextView
    private lateinit var tendaystv : TextView
    private lateinit var amountOfClassesTV: TextView


    private var classes = ArrayList<Class>()
    private var homeworks = ArrayList<Homework>()


    private val handler = Handler()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        mintv = root.findViewById(R.id.countdown_minutes)
        tenmintv = root.findViewById(R.id.countdown_ten_minutes)
        hourstv = root.findViewById(R.id.countdown_hours)
        tenhourstv = root.findViewById(R.id.countdown_ten_hours)
        daystv = root.findViewById(R.id.countdown_days)
        tendaystv = root.findViewById(R.id.countdown_ten_days)
        amountOfClassesTV = root.findViewById(R.id.amount_of_classes_tv)

        val toolbarTitle : String ="Hi, " + "<b>" + getString(R.string.username) + "</b>" + "!"
        (activity as AppCompatActivity).supportActionBar?.title = HtmlCompat.fromHtml(toolbarTitle,HtmlCompat.FROM_HTML_MODE_LEGACY)


        classRecyclerView = root.findViewById(R.id.classes_rv)

        if (context != null) {
            classRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            classesAdapterHome = ClassAdapterHome(requireContext(), classes)
        }
        classRecyclerView.adapter = classesAdapterHome

        homeworkRecyclerView = root.findViewById(R.id.homework_rv)

        if (context != null) {
            homeworkRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            homeworkAdapter = HomeworkAdapter(requireContext(), homeworks)
        }
        homeworkRecyclerView.adapter = homeworkAdapter

        handler.post(object : Runnable {
            override fun run() {
                // Keep the postDelayed before the updateTime(), so when the event ends, the handler will stop too.
                handler.postDelayed(this, 1000)
                updateTime()
            }
        })


        fillClasses(classes)
        fillHomeworks(homeworks)

        val amountOfCLasses : String = classes.size.toString() + " classes today"
        amountOfClassesTV.text = amountOfCLasses

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

        mintv.text = string1
        tenmintv.text = string2
        hourstv.text = string3
        tenhourstv.text = string4
        daystv.text = string5
        tendaystv.text = string6

    }
}
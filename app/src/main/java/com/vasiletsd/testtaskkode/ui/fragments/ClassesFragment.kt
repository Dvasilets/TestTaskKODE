package com.vasiletsd.testtaskkode.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vasiletsd.testtaskkode.R
import com.vasiletsd.testtaskkode.model.Class
import com.vasiletsd.testtaskkode.model.ClassAdapterClasses
import com.vasiletsd.testtaskkode.util.fillClasses
import java.util.*


class ClassesFragment : Fragment() {



    private lateinit var classesAdapterClasses : ClassAdapterClasses
    private lateinit var classRecyclerView : RecyclerView
    private lateinit var searchView: SearchView

    private var classes = ArrayList<Class>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_classes, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_classes)
        classRecyclerView = root.findViewById(R.id.classes_rv2)
        if (context != null) {
            classRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            classesAdapterClasses = ClassAdapterClasses(requireContext(), classes)
        }
        classRecyclerView.adapter = classesAdapterClasses

        fillClasses(classes)

        searchView = root.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                classesAdapterClasses.filter.filter(newText)
                return false
            }

        })


        return root
    }

}
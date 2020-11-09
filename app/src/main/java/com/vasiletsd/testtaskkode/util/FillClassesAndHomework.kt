package com.vasiletsd.testtaskkode.util

import com.vasiletsd.testtaskkode.model.Class
import com.vasiletsd.testtaskkode.model.Homework

import java.util.*
import kotlin.collections.ArrayList

fun fillClasses(classes: ArrayList<Class>){
    val dateStart1   = Date(1605076200000) // 2020 11 11 8:30
    val dateEnd1   = Date(1605081600000) // 2020 11 11 10:00
    classes.add(Class(
            "Geometry", "Watson", "Mrs", "Gotta learn about angles today. Be prepared!", true, dateStart1, dateEnd1, true
    ))

    val dateStart2   = Date(1605082200000) // 2020 11 11 10:10
    val dateEnd2   = Date(1605087600000) // 2020 11 11 11:40
    classes.add(Class(
            "P. E.", "Vasilets", "Mr", "Basketball competition", false, dateStart2, dateEnd2, false
    ))
    val dateStart3   = Date(1605088800000) // 2020 11 8 10:10
    val dateEnd3   = Date(1605094200000) // 2020 11 8 11:40
    classes.add(Class(
            "Math", "Ivanov", "Mr", null, true, dateStart3, dateEnd3, false
    ))
    val dateStart4   = Date(1605094800000) // 2020 11 8 10:10
    val dateEnd4   = Date(1605100200000) // 2020 11 8 11:40
    classes.add(Class(
            "Art", "Sidorova", "Mrs", "Da Vinci's Mona Lisa review", false, dateStart4, dateEnd4, true
    ))
}
fun fillHomeworks(homeworks: ArrayList<Homework>){
    val deadline1 =  (Date(1605421800000).time - Date().time)/(24 * 60 * 60 * 1000) // 2020 11 15 8:30

    homeworks.add(Homework("History", "Learn about WW2", deadline1))

    val deadline2 =  (Date(1605162600000).time - Date().time)/(24 * 60 * 60 * 1000) // 2020 11 15 8:30

    homeworks.add(Homework("Algorithms", "Survive", deadline2))
}
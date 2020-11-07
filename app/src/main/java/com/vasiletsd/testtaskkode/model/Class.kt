package com.vasiletsd.testtaskkode.model

import android.os.health.TimerStat
import java.util.*

data class Class(
        val name: String?,
        val teachersLastName: String?,
        val teachersSex : Boolean?,
        val description : String?,
        val isNecessary : Boolean?,
        val timeStart : Date?,
        val timeEnd : Date?,
        val canBeOpenedInSkype : Boolean?,

) {
}
package com.vasiletsd.testtaskkode.model




import java.util.*


data class Class(
        val name: String?,
        val teachersLastName: String?,
        val teachersSex : String?,
        val description : String?,
        val isNecessary : Boolean?,
        val timeStart : Date?,
        val timeEnd : Date?,
        val canBeOpenedInSkype : Boolean?,

        ) {
}
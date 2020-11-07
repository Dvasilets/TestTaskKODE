package com.vasiletsd.testtaskkode.util

inline fun Boolean?.orFalse() = this ?: false

inline fun Boolean?.orTrue() = this ?: true
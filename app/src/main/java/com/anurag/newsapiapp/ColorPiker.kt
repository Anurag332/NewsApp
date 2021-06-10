package com.anurag.newsapiapp

object ColorPiker {
    val colors =
        arrayOf("#FF018786","#00BFA5","#FFBB86FC")
    private var colorsIndex = 1
    fun getColor():String{
        return colors[colorsIndex++ % colors.size]
    }
}
package com.jessicathornsby.kotlinslices

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri


import androidx.core.graphics.drawable.IconCompat
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.SliceAction


class MySliceProvider : SliceProvider() {


    override fun onCreateSliceProvider(): Boolean {


        return true
    }


    override fun onBindSlice(sliceUri: Uri): Slice? {
        val path = sliceUri.path
        when (path) {
            "/launchMainActivity" -> return createSlice(sliceUri)
        }
        return null
    }


    fun createSlice(sliceUri: Uri): Slice {
        val activityAction = createActivityAction()
        IconCompat.createWithResource(context!!, R.drawable.ic_home).toIcon()


        val activityAction2 = createSecondActivityAction()
        IconCompat.createWithResource(context!!, R.drawable.ic_call).toIcon()


//Construct the parent builder//

        val listBuilder = ListBuilder(context!!, sliceUri)

//Construct the builder for the row//

        val myRow = ListBuilder.RowBuilder(listBuilder)
                .setTitle("Launch MainActivity.")
                .setSubtitle("This is a subtitle")

//Add the actions that we'll be using as end items//

        myRow.addEndItem(activityAction)
        myRow.addEndItem(activityAction2)


        //Add the row to the parent builder//
        listBuilder.addRow(myRow)


//Build the slice//

        return listBuilder.build()
    }



    fun createActivityAction(): SliceAction {
        val intent = Intent(context, MainActivity::class.java)
        return SliceAction(PendingIntent.getActivity(context, 0, intent, 0),
                IconCompat.createWithResource(context!!, R.drawable.ic_home).toIcon(),
                "Launch MainActivity")
    }


    fun createSecondActivityAction(): SliceAction {
        val intent = Intent(context, SecondActivity::class.java)
        return SliceAction(PendingIntent.getActivity(context, 0, intent, 0),
                IconCompat.createWithResource(context!!, R.drawable.ic_call).toIcon(),
                "Launch SecondActivity")
    }


}

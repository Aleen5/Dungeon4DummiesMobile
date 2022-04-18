package com.example.dungeon4dummiesmobile

import android.app.Application
import android.content.Context

class Dungeon4App: Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: Dungeon4App? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        val context: Context = applicationContext()
    }
}
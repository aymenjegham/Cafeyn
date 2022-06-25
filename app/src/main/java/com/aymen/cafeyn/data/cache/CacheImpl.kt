package com.aymen.cafeyn.data.cache

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.google.gson.Gson
import javax.inject.Inject


class CacheImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val gson: Gson
) : Cache {



}
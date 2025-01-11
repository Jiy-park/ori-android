package com.dd2d.data.source.local.data_store_manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data-store-manager")

class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    suspend fun <T> getValue(key: Preferences.Key<T>, default: T) = context.dataStore.data
        .map { pref ->
            pref[key]?: default
        }
        .catch {
            emit(default)
        }
        .first()

    suspend fun <T> setValue(key:  Preferences.Key<T>, value: T) = context.dataStore
        .edit { pref ->
            pref[key] = value
        }

    suspend fun clear() = context.dataStore.edit { pref ->
        pref.clear()
    }
}
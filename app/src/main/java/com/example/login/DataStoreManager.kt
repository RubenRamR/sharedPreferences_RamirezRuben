package com.example.login

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "TiendaPrefs")

class DataStoreManager(private val context: Context) {

    companion object {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val CARRITO_IDS = stringSetPreferencesKey("carrito_ids")
    }

    suspend fun guardarSesion(isLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = isLoggedIn
        }
    }

    val isUserLoggedIn: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[IS_LOGGED_IN] ?: false
    }

    suspend fun guardarCarrito(carrito: Set<String>) {
        context.dataStore.edit { preferences ->
            preferences[CARRITO_IDS] = carrito
        }
    }

    val carritoIds: Flow<Set<String>> = context.dataStore.data.map { preferences ->
        preferences[CARRITO_IDS] ?: emptySet()
    }
}
package com.example.login

import android.content.Context

class AdministradorCarrito(private val context: Context) {
    private val prefs = context.getSharedPreferences("MiTiendaPrefs", Context.MODE_PRIVATE)

    fun agregarAlCarrito(productoId: Int) {
        val idsActuales = obtenerIdsDelCarrito().toMutableList()
        idsActuales.add(productoId)
        guardarIds(idsActuales)
    }

    fun obtenerIdsDelCarrito(): List<Int> {
        val stringIds = prefs.getString("carrito_ids", "") ?: ""
        if (stringIds.isEmpty()) return emptyList()
        return stringIds.split(",").mapNotNull { it.toIntOrNull() }
    }

    private fun guardarIds(ids: List<Int>) {
        val stringIds = ids.joinToString(",")
        prefs.edit().putString("carrito_ids", stringIds).apply()
    }
}
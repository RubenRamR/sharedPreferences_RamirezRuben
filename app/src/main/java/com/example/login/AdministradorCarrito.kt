package com.example.login

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class AdministradorCarrito(
    private val dataStoreManager: DataStoreManager,
    private val scope: CoroutineScope
) {
    val idsDelCarrito: Flow<List<Int>> = dataStoreManager.carritoIds.map { strings ->
        strings.mapNotNull { it.toIntOrNull() }
    }

    fun agregarAlCarrito(productoId: Int) {
        scope.launch {
            val actuales = dataStoreManager.carritoIds.first().toMutableSet()
            actuales.add(productoId.toString())
            dataStoreManager.guardarCarrito(actuales)
        }
    }
}
package com.example.login.screens

import androidx.compose.runtime.*
import com.example.login.AdministradorCarrito
import com.example.login.Producto
import com.example.login.TiendaSonorense

enum class PantallaActual { CATALOGO, DETALLE, CARRITO }

@Composable
fun TiendaApp(administradorCarrito: AdministradorCarrito, onLogout: () -> Unit) {
    val tienda = remember { TiendaSonorense() }
    var estadoPantalla by remember { mutableStateOf(PantallaActual.CATALOGO) }
    var productoSeleccionado by remember { mutableStateOf<Producto?>(null) }

    val idsEnCarrito by administradorCarrito.idsDelCarrito.collectAsState(initial = emptyList())

    when (estadoPantalla) {
        PantallaActual.CATALOGO -> PantallaCatalogo(
            tienda = tienda,
            irACarrito = { estadoPantalla = PantallaActual.CARRITO },
            irADetalle = { producto ->
                productoSeleccionado = producto
                estadoPantalla = PantallaActual.DETALLE
            },
            onAgregarAlCarrito = { id -> administradorCarrito.agregarAlCarrito(id) },
            onLogout = onLogout
        )

        PantallaActual.DETALLE -> PantallaDetalle(
            producto = productoSeleccionado,
            volver = { estadoPantalla = PantallaActual.CATALOGO },
            onAgregarAlCarrito = { id -> administradorCarrito.agregarAlCarrito(id) }
        )

        PantallaActual.CARRITO -> {
            val productosEnCarrito = idsEnCarrito.mapNotNull { tienda.obtenerProductoPorId(it) }

            PantallaCarrito(
                productosEnCarrito = productosEnCarrito,
                volver = { estadoPantalla = PantallaActual.CATALOGO }
            )
        }
    }
}
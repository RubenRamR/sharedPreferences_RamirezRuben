package com.example.login.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.login.Producto

@Composable
fun PantallaCarrito(
    productosEnCarrito: List<Producto>,
    volver: () -> Unit
) {
    val total = productosEnCarrito.sumOf { it.precio }
    val cantidad = productosEnCarrito.size

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text("Tu Orden", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(productosEnCarrito) { producto ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(producto.nombre)
                    Text("$${producto.precio}")
                }
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        Text("Productos añadidos: $cantidad", style = MaterialTheme.typography.titleMedium)
        Text("TOTAL: $$total", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = volver, modifier = Modifier.fillMaxWidth()) {
            Text("Regresar a la tienda")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPantallaCarrito() {
    val listaPrueba = listOf(
        Producto(1, "Dogo de Obregón", 65.0, android.R.drawable.btn_star_big_on, ""),
        Producto(4, "Coyotas", 20.0, android.R.drawable.btn_star_big_on, "")
    )
    PantallaCarrito(
        productosEnCarrito = listaPrueba,
        volver = {}
    )
}
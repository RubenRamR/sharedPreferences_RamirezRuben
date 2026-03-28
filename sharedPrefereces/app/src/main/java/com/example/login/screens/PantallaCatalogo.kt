package com.example.login.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.login.Producto
import com.example.login.TiendaSonorense

@Composable
fun PantallaCatalogo(
    tienda: TiendaSonorense,
    irACarrito: () -> Unit,
    irADetalle: (Producto) -> Unit,
    onAgregarAlCarrito: (Int) -> Unit,
    onLogout: () -> Unit
) {
    var textoBusqueda by remember { mutableStateOf("") }
    var productosMostrados by remember { mutableStateOf(tienda.inventario) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Menú", style = MaterialTheme.typography.titleLarge)
            TextButton(onClick = onLogout) {
                Text("Cerrar Sesión", color = MaterialTheme.colorScheme.error)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = textoBusqueda,
                onValueChange = {
                    textoBusqueda = it
                    productosMostrados = tienda.filtrarProductos(it)
                },
                label = { Text("Buscar comida...") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = irACarrito) { Text("Carrito") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(productosMostrados) { producto ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .clickable { irADetalle(producto) }) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = producto.imagen),
                            contentDescription = producto.nombre,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = producto.nombre,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(text = "$${producto.precio}")
                        }
                        Button(onClick = { onAgregarAlCarrito(producto.id) }) {
                            Text("+ Carrito")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPantallaCatalogo() {
    PantallaCatalogo(
        tienda = TiendaSonorense(),
        irACarrito = {},
        irADetalle = {},
        onAgregarAlCarrito = {},
        onLogout = {}
    )
}
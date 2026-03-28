package com.example.login.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.login.Producto

@Composable
fun PantallaDetalle(
    producto: Producto?,
    volver: () -> Unit,
    onAgregarAlCarrito: (Int) -> Unit
) {
    if (producto == null) return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = producto.imagen),
            contentDescription = producto.nombre,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = producto.nombre, style = MaterialTheme.typography.headlineMedium)
        Text(text = "$${producto.precio}", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = producto.descripcion)

        Spacer(modifier = Modifier.weight(1f))

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(onClick = volver) { Text("Regresar") }
            Button(onClick = {
                onAgregarAlCarrito(producto.id)
                volver()
            }) { Text("Agregar al carrito") }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPantallaDetalle() {
    val productoPrueba = Producto(
        1,
        "Dogo de Obregón",
        65.0,
        android.R.drawable.btn_star_big_on,
        "Dogo tradicional muy bueno."
    )
    PantallaDetalle(
        producto = productoPrueba,
        volver = {},
        onAgregarAlCarrito = {}
    )
}
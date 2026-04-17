package com.example.login

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val imagen: Int,
    val descripcion: String
)

class TiendaSonorense {
    val inventario = listOf(
        Producto(1, "Dogo", 65.0, R.drawable.dogo, "Dogo tradicional con pan artesanal, salchicha, tocino, cebolla asada, tomate, mayonesa y mostaza."),
        Producto(2, "Taco de Carne Asada", 45.0, R.drawable.taco, "Tortilla de harina sobaquera, carne al carbón, repollo y salsa de chiltepín."),
        Producto(3, "Cahuamanta", 130.0, R.drawable.cahuamanta, "Plato de mantarraya y camarón, acompañado de bichi."),
        Producto(4, "Coyotas", 20.0, R.drawable.coyotas, "Coyota tradicional horneada a la leña, rellena de piloncillo."),
        Producto(5, "Cocido", 160.0, R.drawable.cocido, "Caldo tradicional de res con garbanzo y elote.")
    )

    fun filtrarProductos(busqueda: String): List<Producto> {
        if (busqueda.isBlank()) return inventario
        return inventario.filter { it.nombre.contains(busqueda, ignoreCase = true) }
    }

    fun obtenerProductoPorId(id: Int): Producto? {
        return inventario.find { it.id == id }
    }
}
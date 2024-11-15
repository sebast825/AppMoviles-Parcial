package com.example.appmoviles_parcial.presentacion

sealed class ClimaEstado {
    data object Vacio: ClimaEstado()
    data object Cargando: ClimaEstado()
    //data class puede recibir parametro, data object mp
    data class Exitoso (
        val ciudad: String = "",
        val temperatura: Double = 0.0,
        val descripcion: String= "",
        val st :Double = 0.0,
    ) : ClimaEstado()    data class Error(val mensaje: String): ClimaEstado()
}


//es parte del Modelo
data class Clima(
  val temperatura: Double,
  val sensacionTermica: Double,
  val estado: String,
  val humedad: Double,
  val velocidadDelViento: Double,
  val latitud: Double,
  val longitud: Double
)
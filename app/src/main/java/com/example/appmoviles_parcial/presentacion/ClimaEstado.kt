package com.example.appmoviles_parcial.presentacion

sealed class ClimaEstado {
    data object Vacio: ClimaEstado()
    data object Cargando: ClimaEstado()
    //data class puede recibir parametro, data object mp
    data class  Exitoso(val clima: Clima): ClimaEstado()
    data class Error(val mensaje: Clima): ClimaEstado()
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
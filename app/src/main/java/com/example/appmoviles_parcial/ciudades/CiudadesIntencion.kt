package com.example.appmoviles_parcial.ciudades

import com.example.appmoviles_parcial.repositorio.modelos.Ciudad

sealed class CiudadesIntencion {
    data class Buscar( val nombre:String ) : CiudadesIntencion()
    data class Seleccionar(val nombreCiudad: String) : CiudadesIntencion()
    data object CambiarPagina : CiudadesIntencion()
}
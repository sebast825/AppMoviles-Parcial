package com.example.appmoviles_parcial.ciudades

import com.example.appmoviles_parcial.repositorio.modelos.Ciudad

sealed class CiudadesEstado {
    data object Vacio: CiudadesEstado()
    data object Cargando: CiudadesEstado()
    data class Resultado( val nameCiudades : List<String> ) : CiudadesEstado()
    data class Error(val mensaje: String): CiudadesEstado()
}
package com.example.appmoviles_parcial.repositorio

import com.example.appmoviles_parcial.repositorio.modelos.Ciudad
import com.example.appmoviles_parcial.repositorio.modelos.Clima
import com.example.appmoviles_parcial.repositorio.modelos.ListForecast

interface Repositorio {
    suspend fun traerPokemon(): String
    suspend fun buscarCiudad(ciudad: String): Ciudad
   suspend fun traerClima(lat: Double, lon: Double) : Clima
   // suspend fun traerPronostico(nombre: String) : List<ListForecast>
}
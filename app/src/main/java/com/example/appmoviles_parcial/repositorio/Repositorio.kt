package com.example.appmoviles_parcial.repositorio

interface Repositorio {
    suspend fun traerPokemon(): String
}
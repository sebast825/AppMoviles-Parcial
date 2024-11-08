package com.example.appmoviles_parcial.repositorio

import android.util.Log

class RepositorioApi : Repositorio {



    override  fun traerPokemon(): String {
        Log.d("elem","traer Pokemon")
        return "llegamo al poke"
    }

}
package com.example.appmoviles_parcial.presentacion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ClimaViewModel {

    var estado by mutableStateOf<ClimaEstado>(ClimaEstado.Vacio)

    //es la unica funcion publica que ejecuta el viewModel, el resto son privadas
    fun ejecutar(intencion: ClimaIntencion){

    }
}
package com.example.appmoviles_parcial.presentacion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ClimaViewModel {

    var estado by mutableStateOf<ClimaEstado>(ClimaEstado.Vacio)

    //es la unica funcion publica que ejecuta el viewModel, el resto son privadas
    fun ejecutar(intencion: ClimaIntencion){
        when (intencion){
            ClimaIntencion.actualizar -> actualizar()
        }
    }

    private fun actualizar(){
        estado = ClimaEstado.Cargando
        //trae datos desde un repo

        estado = ClimaEstado.Error("No funca x ahora")
    }
}
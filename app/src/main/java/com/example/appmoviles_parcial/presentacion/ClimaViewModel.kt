package com.example.appmoviles_parcial.presentacion

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.time.delay
import androidx.lifecycle.ViewModel
import com.example.appmoviles_parcial.repositorio.Repositorio

class ClimaViewModel (
    val repositorio : Repositorio

): ViewModel() {

    var estado by mutableStateOf<ClimaEstado>(ClimaEstado.Vacio)

    //es la unica funcion publica que ejecuta el viewModel, el resto son privadas
    fun ejecutar(intencion: ClimaIntencion){
        when (intencion){
            ClimaIntencion.actualizar -> actualizar()
        }
    }

    private fun actualizar(){

            Log.d("Clima View model", "actualizar")
            var repo = repositorio.traerPokemon()
            estado = ClimaEstado.Cargando
            //estado = ClimaEstado.Error("No funca x ahora")

    }
}
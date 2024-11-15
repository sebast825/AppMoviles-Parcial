package com.example.appmoviles_parcial.presentacion

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.time.delay
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmoviles_parcial.repositorio.Repositorio
import kotlinx.coroutines.launch

class ClimaViewModel (
    val repositorio : Repositorio,
    val lat : Double,
    val lon : Double,
): ViewModel() {

    var estado by mutableStateOf<ClimaEstado>(ClimaEstado.Vacio)

    //es la unica funcion publica que ejecuta el viewModel, el resto son privadas
    fun ejecutar(intencion: ClimaIntencion){
        when (intencion){
            ClimaIntencion.actualizarClima -> getClima()
        }
    }

    private fun actualizar(){

            Log.d("Clima View model", "actualizar")
        viewModelScope.launch {
            var repo = repositorio.traerPokemon()
            Log.d("actualizar", repo)
        }
            estado = ClimaEstado.Cargando
            //estado = ClimaEstado.Error("No funca x ahora")

    }

    fun getClima() {
        estado = ClimaEstado.Cargando
        viewModelScope.launch {
            try {
                val clima = repositorio.traerClima(lat = lat, lon = lon)
                estado = ClimaEstado.Exitoso(
                    ciudad = clima.name,
                    temperatura = clima.main.temp,
                    descripcion = clima.weather.first().description,
                    st = clima.main.feels_like,

                )
            } catch (exception: Exception) {
                estado = ClimaEstado.Error(exception.localizedMessage ?: "error desconocido")
            }

        }
    }
}
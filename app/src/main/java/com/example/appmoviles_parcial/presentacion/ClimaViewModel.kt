package com.example.appmoviles_parcial.presentacion

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.time.delay
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmoviles_parcial.ciudades.CiudadesEstado
import com.example.appmoviles_parcial.repositorio.Repositorio
import com.example.appmoviles_parcial.repositorio.modelos.Ciudad
import kotlinx.coroutines.launch

class ClimaViewModel (
    val repositorio : Repositorio,
    val lat : Double,
    val lon : Double,
): ViewModel() {

    var estado by mutableStateOf<ClimaEstado>(ClimaEstado.Vacio)
    var ciudad : Ciudad? = null

    //es la unica funcion publica que ejecuta el viewModel, el resto son privadas
    fun ejecutar(intencion: ClimaIntencion){
        when (intencion){
            ClimaIntencion.actualizarClima -> actualizar()
            is ClimaIntencion.buscarCiudad -> getInformacionCiudad(intencion.ciudad)
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
    fun getInformacionCiudad(nameCiudad : String){

        viewModelScope.launch {
            val ciud = buscar(nameCiudad)
            Log.d("nombre ciudad", ciud.toString() )

            Log.d("nombre ciudad", ciudad?.name ?: "azsd")
            getClima()
        }

    }
    fun getClima() {
        estado = ClimaEstado.Cargando
        viewModelScope.launch {
            try {
                var latitud = ciudad?.lat ?: 0.0
                var longitud = ciudad?.lon ?: 0.0
                Log.d("lat", latitud.toString())

                Log.d("lon", longitud.toString())
                val clima = repositorio.traerClima(lat = latitud, lon = longitud)
                Log.d("Clima", clima.name)

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
    private suspend fun buscar(nombre: String){

        estado = ClimaEstado.Cargando
            try {
                ciudad = repositorio.buscarCiudad(nombre)
                Log.d("buscar", ciudad!!.name ?:"asd")

                if (ciudad == null) {
                    estado = ClimaEstado.Vacio
                } else {
                    estado = ClimaEstado.Exitoso()
                }
            } catch (exeption: Exception){
                Log.e("error", exeption.message ?: "ni idea")
                estado = ClimaEstado.Error(exeption.message ?: "error desconocido")
            }

    }
}
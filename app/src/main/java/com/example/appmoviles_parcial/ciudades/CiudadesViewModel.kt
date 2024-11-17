package com.example.appmoviles_parcial.ciudades

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appmoviles_parcial.presentacion.ClimaEstado
import com.example.appmoviles_parcial.presentacion.ClimaIntencion
import com.example.appmoviles_parcial.repositorio.Repositorio
import com.example.appmoviles_parcial.repositorio.modelos.Ciudad
import kotlinx.coroutines.launch
import kotlin.math.log

class CiudadesViewModel(

    val repositorio: Repositorio,
    val navController: NavController? = null
        ) : ViewModel(){

    var ciudades : List<Ciudad> = emptyList()
    var estado by mutableStateOf<CiudadesEstado>(CiudadesEstado.Vacio)


    //es la unica funcion publica que ejecuta el viewModel, el resto son privadas
    fun ejecutar(intencion: CiudadesIntencion){
        when (intencion){
            CiudadesIntencion.CambiarPagina -> cambiarPagina()
            is CiudadesIntencion.Buscar -> buscar(intencion.nombre)
            is CiudadesIntencion.Seleccionar -> TODO()
        }
    }

    private fun cambiarPagina(){
        estado = CiudadesEstado.Cargando
        Log.d("cambiar apg","te camnbiamos la pagina turro");
        buscar("San Vicente")
        //Cordoba - London - Buenos Aires - La Plata - San Vicente
viewModelScope.launch {
    if (!ciudades.isEmpty()) {
    Log.d("ciudad",ciudades[0].name)}
}




        navController?.navigate("Clima/La Plata")
    }

    private fun buscar( nombre: String){

        estado = CiudadesEstado.Cargando
        viewModelScope.launch {
            try {
                ciudades = repositorio.buscarCiudad(nombre)


                if (ciudades.isEmpty()) {
                    estado = CiudadesEstado.Vacio
                } else {
                    estado = CiudadesEstado.Resultado(ciudades)
                }
            } catch (exeption: Exception){
                estado = CiudadesEstado.Error(exeption.message ?: "error desconocido")
            }
        }
    }


}
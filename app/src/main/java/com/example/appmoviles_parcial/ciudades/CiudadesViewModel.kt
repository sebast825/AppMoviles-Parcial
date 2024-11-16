package com.example.appmoviles_parcial.ciudades

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appmoviles_parcial.presentacion.ClimaEstado
import com.example.appmoviles_parcial.presentacion.ClimaIntencion
import com.example.appmoviles_parcial.repositorio.Repositorio
import com.example.appmoviles_parcial.repositorio.modelos.Ciudad

class CiudadesViewModel(

    val repositorio: Repositorio,
    val navController: NavController? = null
        ) : ViewModel(){

    var estado by mutableStateOf<CiudadesEstado>(CiudadesEstado.Vacio)


    //es la unica funcion publica que ejecuta el viewModel, el resto son privadas
    fun ejecutar(intencion: CiudadesIntencion){
        when (intencion){
            CiudadesIntencion.CambiarPagina -> cambiarPagina()
            is CiudadesIntencion.Buscar -> TODO()
            is CiudadesIntencion.Seleccionar -> TODO()
        }
    }

    private fun cambiarPagina(){
        Log.d("cambiar apg","te camnbiamos la pagina turro");
        navController?.navigate("Clima")
    }


}
package com.example.appmoviles_parcial.ciudades

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.appmoviles_parcial.repositorio.modelos.Ciudad


@Composable
fun CiudadesView (
    modifier: Modifier = Modifier,
    estado: CiudadesEstado,
    //Unit es como void en c#
    ejecutar: (CiudadesIntencion) -> Unit
    ) {
       Column {
           when(estado){
               CiudadesEstado.Cargando -> CargandoView()
               is CiudadesEstado.Error -> TODO()
               is CiudadesEstado.Resultado -> ResultadoView()
               CiudadesEstado.Vacio -> VacioView()
           }

           Text(text = "Estamos en Ciudades View")

           val namesCiudades = mutableListOf("Cordoba", "London", "Buenos Aires", "La Plata", "San Vicente")

           namesCiudades.forEach{
                   ciudad ->

               Button(onClick = { ejecutar(CiudadesIntencion.Seleccionar(ciudad))}) {
                   Text(text = ciudad)
               }
           }
       }

        }

@Composable
fun VacioView(){
    Text(
        text = "No hay anda que mostrar CiudadVie")

}

@Composable
fun CargandoView(){
    Text(
        text = "CARGANDO CiudadVie")

}


@Composable
fun ResultadoView(){
//ciudadesList : List<Ciudad>
    val namesCiudades = mutableListOf("Cordoba", "London", "Buenos Aires", "La Plata", "San Vicente")

    namesCiudades.forEach{
        ciudad ->
        Text(
            text = ciudad)

    }


}
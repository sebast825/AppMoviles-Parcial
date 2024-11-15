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
               CiudadesEstado.Cargando -> TODO()
               is CiudadesEstado.Error -> TODO()
               is CiudadesEstado.Resultado -> TODO()
               CiudadesEstado.Vacio -> VacioView()
           }

           Text(text = "Estamos en Ciudades View")
           Button(onClick = { ejecutar(CiudadesIntencion.CambiarPagina)}) {
               Text(text = "Cambiasr de pagina")
           }
       }

        }

@Composable
fun VacioView(){
    Text(
        text = "No hay anda que mostrar")

}
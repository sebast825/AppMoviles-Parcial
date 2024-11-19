package com.example.appmoviles_parcial.ciudades

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
               is CiudadesEstado.Resultado -> ResultadoView(
                   ciudades = estado.nameCiudades,
                   ejecutar = ejecutar
               )
               CiudadesEstado.Vacio -> VacioView()
           }





       }

        }

@Composable
fun VacioView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "No hay nada que mostrar",

        )
    }
}

@Composable
fun CargandoView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Cargando...",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}


@Composable
fun ResultadoView(ciudades: List<String>, ejecutar: (CiudadesIntencion) -> Unit) {
    var inputCiudad by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Encuentra una ciudad",
                style = MaterialTheme.typography.headlineLarge
            )
            TextField(
                value = inputCiudad,
                onValueChange = { inputCiudad = it },
                label = { Text("Ingresar Ciudad") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { ejecutar(CiudadesIntencion.Seleccionar(inputCiudad)) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )

            ) {
                Text(
                    text = "Buscar",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        Column (modifier = Modifier
            .padding(16.dp)){
            Text(
                text = "Elegir Otra Ciudad",
                style = MaterialTheme.typography.headlineSmall
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)

            ) {
                ciudades.forEach { ciudad ->
                    Button(
                        onClick = { ejecutar(CiudadesIntencion.Seleccionar(ciudad)) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = ciudad,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }

    }
}

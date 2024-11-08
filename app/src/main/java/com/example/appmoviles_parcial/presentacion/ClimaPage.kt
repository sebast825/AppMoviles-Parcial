package com.example.appmoviles_parcial.presentacion


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ClimaPage(

    modifier: Modifier = Modifier
) {

    val viewModel : ClimaViewModel = ViewModel()
    
    ClimaView(
        modifier = Modifier,
        estado = viewModel.estado

    ){
        viewModel.ejecutar((it))
    }

}
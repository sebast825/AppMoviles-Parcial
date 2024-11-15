package com.example.appmoviles_parcial.ciudades

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.appmoviles_parcial.presentacion.ClimaViewModel
import com.example.appmoviles_parcial.repositorio.Repositorio
import com.example.appmoviles_parcial.repositorio.RepositorioApi


@Composable
fun CiudadesPage (

    navHostController:  NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModel : CiudadesViewModel = viewModel(
        factory = CiudadesViewModelFactory(
            repositorio = RepositorioApi()

        )
    )
    CiudadesView(
        modifier = modifier,
        estado = viewModel.estado
    ){
        viewModel.ejecutar((it))
    }
}

class CiudadesViewModelFactory(
    private val repositorio: Repositorio

) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CiudadesViewModel::class.java)) {
            return CiudadesViewModel(repositorio) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
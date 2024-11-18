package com.example.appmoviles_parcial

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appmoviles_parcial.ciudades.CiudadesPage
import com.example.appmoviles_parcial.presentacion.ClimaPage
import com.example.appmoviles_parcial.ui.theme.AppMovilesParcialTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppMovilesParcialTheme {
                IrCiudadPage()

                }



            }
        }
    }

@Composable
fun IrCiudadPage(){
    val navController = rememberNavController()

NavHost(navController = navController , startDestination = "Ciudad",
    modifier = Modifier.padding(top = 22.dp)
) {
    composable(route = "Ciudad"){
        CiudadesPage(navHostController = navController)
    }
    composable(
        route = "Clima/{ciudad}",
                arguments = listOf(navArgument("ciudad"){
            type = NavType.StringType

        })) {
        val ciudad = it.arguments?.getString("ciudad") ?: ""

            ClimaPage(modifier = Modifier, ciudad = ciudad,navHostController = navController)


    }
}

}


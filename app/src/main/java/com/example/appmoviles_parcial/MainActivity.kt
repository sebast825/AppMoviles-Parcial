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
import androidx.navigation.compose.rememberNavController
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
             /*   Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ClimaPage(modifier = Modifier.padding(innerPadding))

                }*/

                    IrCiudadPage()

            }
        }
    }
}
@Composable
fun IrCiudadPage(){
    val navController = rememberNavController()

    CiudadesPage(navHostController = navController)

}


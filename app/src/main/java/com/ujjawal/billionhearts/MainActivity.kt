package com.ujjawal.billionhearts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ujjawal.billionhearts.details.DetailsScreen
import com.ujjawal.billionhearts.home.MainScreen
import com.ujjawal.billionhearts.model.getShoes
import com.ujjawal.billionhearts.ui.theme.BillionHeartsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BillionHeartsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.White,
                ) { innerPadding ->
                    val navController = rememberNavController()
                    SharedTransitionLayout {
                        NavHost(
                            navController = navController,
                            startDestination = NavRoutes.MainScreen
                        ) {
                            composable<NavRoutes.MainScreen> {
                                MainScreen(
                                    modifier = Modifier,
                                    topPadding = { innerPadding.calculateTopPadding() },
                                    onClick = {
                                        navController.navigate(
                                            NavRoutes.DetailsScreen(it)
                                        )
                                    },
                                    animatedContentScope = this,
                                    sharedTransitionScope = this@SharedTransitionLayout
                                )
                            }
                            composable<NavRoutes.DetailsScreen> { backStackEntry ->
                                val data = backStackEntry.toRoute<NavRoutes.DetailsScreen>()
                                DetailsScreen(
                                    modifier = Modifier,
                                    shoe = getShoes()[data.index],
                                    topPadding = { innerPadding.calculateTopPadding() },
                                    onBackPress = {
                                        navController.popBackStack()
                                    },
                                    animatedContentScope = this,
                                    sharedTransitionScope = this@SharedTransitionLayout
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BillionHeartsTheme {
        Greeting("Android")
    }
}
package com.ktacademy.foodie

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ktacademy.foodie.data.repository.FoodRepository
import com.ktacademy.foodie.ui.presentation.home.HomeScreen
import com.ktacademy.foodie.ui.presentation.home.HomeViewModel
import com.ktacademy.foodie.ui.presentation.search.SearchScreen
import com.ktacademy.foodie.ui.presentation.search.SearchViewModel
import com.ktacademy.foodie.ui.theme.FoodieTheme

class MainActivity : ComponentActivity() {
    @ExperimentalMaterial3Api
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // Data Sources
            val foodRepository = FoodRepository()

            // UI ViewModels
            val homeViewModel = HomeViewModel(repository = foodRepository)
            val searchViewModel = SearchViewModel(repository = foodRepository)


            // navigation
            val navController = rememberNavController()

            FoodieTheme {
                // A surface container using the 'background' color from the theme

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                                .padding(20.dp)
                        ) {
                           Text(
                               text = "Home",
                               modifier = Modifier.weight(1f).clickable { navController.navigate(AppScreens.home) },
                               style = MaterialTheme.typography.bodyMedium,
                               fontSize = 40.sp)
                            Text(
                                text = "Search",
                                modifier = Modifier.weight(1f).clickable { navController.navigate(AppScreens.search) },
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 40.sp
                            )
                        }
                    }
                ) {  paddingValues ->
                    NavHost(navController = navController, startDestination = AppScreens.home){
                        composable(AppScreens.home){ HomeScreen(homeViewModel = homeViewModel) }
                        composable(AppScreens.search){ SearchScreen(viewModel = searchViewModel) }
                    }

                }
            }
        }
    }
}

object AppScreens {
    const val home = "home"
    const val search = "search"
}

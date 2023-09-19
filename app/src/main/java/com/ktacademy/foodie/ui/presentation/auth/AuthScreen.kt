import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ktacademy.foodie.data.network.AppResource
import com.ktacademy.foodie.ui.presentation.auth.AuthActions
import com.ktacademy.foodie.ui.presentation.auth.AuthUIState
import com.ktacademy.foodie.ui.presentation.auth.AuthViewModel
import com.ktacademy.foodie.ui.theme.FoodieTheme

@Composable
@ExperimentalMaterial3Api
fun AuthScreen(
    modifier: Modifier = Modifier
) {

    val authViewModel: AuthViewModel = viewModel()
    val uiState: AuthUIState by authViewModel.authUIState.collectAsState()
    val authResource: AppResource<String>? by authViewModel.screenState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = uiState.email,
            onValueChange = { authViewModel.handleLogin(AuthActions.EmailChange(it)) },
            label = {
                Text(text = "Enter your email")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        )

        Button(
            onClick = { authViewModel.handleLogin(AuthActions.SubmitAuth) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp)) {
            Text(text = "Sign In")
        }

        if(authResource !is AppResource.Loading) return

        AppLoader()


    }
}


@ExperimentalMaterial3Api
@Preview
@Composable
fun AuthScreenPreview() {
    FoodieTheme {
        AuthScreen()
    }
}

@Composable
fun AppLoader() {
    CircularProgressIndicator(
        modifier = Modifier.size(50.dp)
    )
}
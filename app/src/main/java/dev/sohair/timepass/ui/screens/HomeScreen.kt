package dev.sohair.timepass.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.sohair.timepass.R
import dev.sohair.timepass.ui.Screen
import dev.sohair.timepass.ui.theme.Blue60

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.app_title))
            Spacer(modifier = Modifier.height(200.dp))

            val buttonColor = ButtonDefaults.buttonColors(containerColor = Blue60)
            Button(
                onClick = { navHostController.navigate(Screen.Generate.route) },
                colors = buttonColor
            ) {
                Text(text = "Generate Dogs!")
            }

            Button(
                onClick = { navHostController.navigate(Screen.History.route) },
                colors = buttonColor
            ) {
                Text(text = "My recently generated Dogs!")
            }
        }
    }
}
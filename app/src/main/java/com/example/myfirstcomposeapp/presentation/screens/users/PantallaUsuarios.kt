package com.example.myfirstcomposeapp.presentation.screens.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.myfirstcomposeapp.presentation.components.UiState
import com.example.myfirstcomposeapp.data.dto.Coordinates
import com.example.myfirstcomposeapp.data.dto.DataAge
import com.example.myfirstcomposeapp.data.dto.ID
import com.example.myfirstcomposeapp.data.dto.Location
import com.example.myfirstcomposeapp.data.dto.LoginData
import com.example.myfirstcomposeapp.data.dto.Name
import com.example.myfirstcomposeapp.data.dto.Picture
import com.example.myfirstcomposeapp.data.dto.Street
import com.example.myfirstcomposeapp.data.dto.Timezone
import com.example.myfirstcomposeapp.data.dto.User
import com.example.myfirstcomposeapp.presentation.theme.AppTheme
import com.example.myfirstcomposeapp.presentation.theme.backgroundColor
import com.example.myfirstcomposeapp.presentation.theme.surfaceColor

@Composable
fun PantallaUsuarios(
    modifier: Modifier = Modifier,
    name: String,
    onClickUsuario: (User) -> Unit
) {

    val viewModel: UserViewModel = viewModel(factory = UserViewModelFactory(UserContainer.useCase))

    LaunchedEffect(true) {
        viewModel.consultarUsuarios()
    }

    val estado by viewModel.uiState.collectAsState()

    PantallaUsuariosBody(
        modifier = modifier,
        estado = estado,
        name = name,
        onClickUsuario = onClickUsuario
    )

}

@Composable
fun PantallaUsuariosBody(
    modifier: Modifier = Modifier,
    estado: UiState,
    name: String,
    onClickUsuario: (User) -> Unit
) {
    Surface(color = surfaceColor, modifier = modifier.fillMaxSize()) {
        Column(modifier = modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues())
        ) {

            Text(
                text = "Bienvenido, $name",
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier.padding(16.dp)
            )

            when(estado){
                is UiState.Loading -> {
                    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                            Text("Obteniendo los usuarios...")
                        }
                    }
                }
                is UiState.Success<*> -> {
                    val usuarios = (estado as UiState.Success<List<User>>).data
                    LazyColumn(
                        modifier = modifier.background(backgroundColor)
                    ) {
                        items(usuarios) { usuario ->
                            UserCard(usuario = usuario, onClickUsuario = onClickUsuario)
                        }
                    }
                }
                is UiState.Error -> {
                    val errorMessage = (estado as UiState.Error).message
                    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(errorMessage)
                    }
                }
            }

        }
    }
}

@Composable
private fun UserCard(modifier: Modifier = Modifier, usuario: User, onClickUsuario: (User) -> Unit) {
    Column(
        modifier = modifier.padding(vertical = 12.dp).clickable{ onClickUsuario.invoke(usuario) }
    ) {
        HorizontalDivider(thickness = 1.dp, color = Color.Gray)
        Row(
            modifier = modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(usuario.picture.large),
                contentDescription = null,
                modifier = modifier.size(64.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = modifier.padding(start = 16.dp)
            ) {
                Text(usuario.login.username, fontWeight = FontWeight.Bold)
                Text("${usuario.name.first} ${usuario.name.last}")
            }
        }
        HorizontalDivider(thickness = 1.dp, color = Color.Gray)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PantallaUsuariosPreview(
    @PreviewParameter(UserPreviewParameterProvider::class) state: UiState
) {
    AppTheme {
        PantallaUsuariosBody(
            name = "Yael",
            estado = state,
            onClickUsuario = {}
        )
    }
}

class UserPreviewParameterProvider : PreviewParameterProvider<UiState> {
    override val values = sequenceOf(
        UiState.Loading,
        UiState.Success(listOf(sampleUser1(), sampleUser2())),
        UiState.Error("Ocurrió un error al cargar los datos")
    )
    private fun sampleUser1(): User {
        return User(
            gender = "male",
            name = Name("Mr", "Juan", "Pérez"),
            location = Location(
                street = Street(123, "Av. Siempre Viva"),
                city = "Buenos Aires",
                state = "Buenos Aires",
                country = "Argentina",
                postcode = "1000",
                coordinates = Coordinates("-34.6037", "-58.3816"),
                timezone = Timezone("-03:00", "Buenos Aires Time")
            ),
            email = "juan.perez@example.com",
            login = LoginData(
                uuid = "uuid-juan",
                username = "juanp",
                password = "password123",
                salt = "salt",
                md5 = "md5",
                sha1 = "sha1",
                sha256 = "sha256"
            ),
            dob = DataAge("1990-01-01", 34),
            registered = DataAge("2015-05-10", 9),
            phone = "1234567890",
            cell = "0987654321",
            id = ID(name = "DNI", value = "12345678"),
            picture = Picture(
                large = "https://randomuser.me/api/portraits/women/89.jpg",
                medium = "https://randomuser.me/api/portraits/women/89.jpg",
                thumbnail = "https://randomuser.me/api/portraits/women/89.jpg"
            ),
            nat = "AR"
        )
    }

    private fun sampleUser2(): User {
        return User(
            gender = "female",
            name = Name("Ms", "Laura", "Gómez"),
            location = Location(
                street = Street(456, "Calle Falsa"),
                city = "Medellín",
                state = "Antioquia",
                country = "Colombia",
                postcode = "62780",
                coordinates = Coordinates("6.2518", "-75.5636"),
                timezone = Timezone("-05:00", "Colombia Standard Time")
            ),
            email = "laura.gomez@example.com",
            login = LoginData(
                uuid = "uuid-laura",
                username = "laurag",
                password = "contraseña123",
                salt = "salt2",
                md5 = "md5laura",
                sha1 = "sha1laura",
                sha256 = "sha256laura"
            ),
            dob = DataAge("1985-07-20", 39),
            registered = DataAge("2012-11-05", 12),
            phone = "3001234567",
            cell = "3107654321",
            id = ID(name = "CC", value = "11223344"),
            picture = Picture(
                large = "https://randomuser.me/api/portraits/women/89.jpg",
                medium = "https://randomuser.me/api/portraits/women/89.jpg",
                thumbnail = "https://randomuser.me/api/portraits/women/89.jpg"
            ),
            nat = "CO"
        )
    }
}
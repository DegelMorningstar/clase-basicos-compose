package com.example.myfirstcomposeapp.presentation.screens.userDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.myfirstcomposeapp.presentation.screens.userDetail.tabs.ContactoTab
import com.example.myfirstcomposeapp.presentation.screens.userDetail.tabs.CumpleañosTab
import com.example.myfirstcomposeapp.presentation.screens.userDetail.tabs.DireccionTab
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
import kotlinx.coroutines.launch

@Composable
fun PantallaDetalleUsuario(modifier: Modifier = Modifier, usuario: User) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf("Contacto", "Cumpleaños", "Dirección")
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { tabs.size })

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = modifier.padding(top = 32.dp))

        Image(
            painter = rememberAsyncImagePainter(usuario.picture.large),
            contentDescription = null,
            modifier = modifier.size(128.dp).clip(CircleShape),
            contentScale = ContentScale.Crop,
        )

        Text(
            text = "${usuario.name.first} ${usuario.name.last}",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "@${usuario.login.username}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = modifier.padding(top = 32.dp))

        TabRow(selectedTabIndex = pagerState.currentPage) {

            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(text = title) }
                )

            }

        }
        HorizontalPager(
            state = pagerState,
            modifier = modifier.fillMaxSize()
        ) { page ->
            when(page){
                0 -> {
                    ContactoTab(usuario, context)
                }
                1 -> {
                    CumpleañosTab(usuario)
                }
                2 -> {
                    DireccionTab(usuario, context)
                }
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PantallaDetalleUsuarioPreview() {
    AppTheme {
        PantallaDetalleUsuario(usuario = User(
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
        )
    }
}
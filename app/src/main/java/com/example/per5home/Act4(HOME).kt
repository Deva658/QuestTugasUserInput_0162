package com.example.per5home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalStdlibApi::class)
@Composable

fun BiodataScreen() {
    var namaDepan by remember { mutableStateOf("") }
    var namaBelakang by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var tglLahir by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }

    var skillAndroid by remember { mutableStateOf(false) }
    var skillUiUx by remember { mutableStateOf(false) }
    var skillWeb by remember { mutableStateOf(false) }

    var expExpanded by remember { mutableStateOf(false) }
    var isSubmitted by remember { mutableStateOf(false) }

    var isFormValid =
        namaDepan.isNotBlank() && namaBelakang.isNotBlank() && email.isNotBlank() && tglLahir.isNotBlank()
        && (skillAndroid || skillUiUx || skillWeb)

    var expList = listOf(
        stringResource(id = R.string.exp_1),
        stringResource(id = R.string.exp_2),
        stringResource(id = R.string.exp_3),
    )
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            Image(
                painter = painterResource(id = R.drawable.poto),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().height(350.dp)
            )
        }
    }
}
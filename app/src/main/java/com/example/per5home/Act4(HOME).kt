package com.example.per5home

import android.graphics.drawable.Icon
import android.view.RoundedCorner
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
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

    val isFormValid =
        namaDepan.isNotBlank() && namaBelakang.isNotBlank() && email.isNotBlank() && tglLahir.isNotBlank()
        && (skillAndroid || skillUiUx || skillWeb)

    val expList = listOf(
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
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 220.dp), shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.bg_white)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                if (isSubmitted) {
                    ResultCard(
                        namaLengkap = "$namaDepan $namaBelakang",
                        email = email,
                        tglLahir = tglLahir,
                        experience = experience,
                        skills = listOfNotNull(
                            if (skillAndroid) stringResource(id = R.string.skill_android) else null,
                            if (skillUiUx) stringResource(id = R.string.skill_uiux) else null,
                            if (skillWeb) stringResource(id = R.string.skill_web) else null
                        ).joinToString(", "),
                        onBack = { isSubmitted = false }
                    )
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(24.dp).verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.form_title),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.theme_dark)
                        )
                        Text(
                            text = stringResource(id = R.string.form_subtitle),
                            fontSize = 12.sp,
                            color = colorResource(id = R.color.theme_gray)
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        Row(modifier = Modifier.fillMaxWidth()) {
                            OutlinedTextField(
                                value = namaDepan,
                                onValueChange = { namaDepan = it },
                                placeholder = { Text(stringResource(id = R.string.hint_nama_depan)) },
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            OutlinedTextField(
                                value = namaBelakang,
                                onValueChange = { namaBelakang = it },
                                placeholder = { Text(stringResource(id = R.string.hint_nama_belakang)) },
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            placeholder = { Text(stringResource(id = R.string.hint_email)) },
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        OutlinedTextField(
                            value = tglLahir,
                            onValueChange = { tglLahir = it },
                            placeholder = { Text(stringResource(id = R.string.hint_tgl_lahir)) },
                            trailingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) },
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        ExposedDropdownMenuBox(
                            expanded = expExpanded,
                            onExpandedChange = { expExpanded = !expExpanded },
                            modifier = Modifier.weight(1f)
                        ) {
                            OutlinedTextField(
                                value = experience,
                                onValueChange = { },
                                readOnly = true,
                                placeholder = { Text(stringResource(id = R.string.hint_experience)) },
                                trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = null) },
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier.menuAnchor()
                            )
                            ExposedDropdownMenu(
                                expanded = expExpanded,
                                onDismissRequest = { expExpanded = false }
                            ) {
                                expList.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        text = { Text(selectionOption) },
                                        onClick = {
                                            experience = selectionOption
                                            expExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(id = R.string.label_skill),
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.theme_dark),
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = skillAndroid,
                                onCheckedChange = { skillAndroid = it },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = colorResource(id = R.color.theme_dark),
                                )
                            )
                            Text(StringResource(id = R.string.skill_android), fontSize = 14.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { isSubmitted = true },
                        enabled = isFormValid,
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.theme_dark),
                            disabledContainerColor = colorResource(id = R.color.btn_disabled)
                        ),
                        modifier = Modifier.fillMaxWidth().height(50.dp).shadow(elevation = if (isFormValid) 8.dp else 0.dp, shape = RoundedCornerShape(16.dp)
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.btn_proceed),
                            color = if (isFormValid) colorResource(id = R.color.text_white) else colorResource(id = R.color.theme_gray),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ResultCard(
    namaLengkap: String,
    email: String,
    tglLahir: String,
    experience: String,
    skills: String,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.hasil_title),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.theme_dark)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Nama: $namaLengkap", fontSize = 16.sp, modifier = Modifier.padding(bottom = 4.dp))
        Text(text = "Email: $email", fontSize = 16.sp, modifier = Modifier.padding(bottom = 4.dp))
        Text(text = "Tanggal Lahir: $tglLahir", fontSize = 16.sp, modifier = Modifier.padding(bottom = 4.dp))
        Text(text = "Experience: $experience", fontSize = 16.sp, modifier = Modifier.padding(bottom = 4.dp))
        Text(text = "Skills: $skills", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onBack,
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.theme_dark)),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.btn_kembali),
                color = colorResource(id = R.color.text_white)
            )
        }
    }
}
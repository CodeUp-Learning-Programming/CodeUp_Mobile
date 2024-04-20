package com.example.codeup.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Date(
    modifier: Modifier = Modifier,
    onDateChange: (String) -> Unit
) {
    var color by remember{
        mutableStateOf(Color.Cyan)
    }

    val focusManager = LocalFocusManager.current
    var showDatePickerDialog by remember {
        mutableStateOf(false)
    }
    val datePickerState = rememberDatePickerState()
    var selectedDate by remember {
        mutableStateOf("")
    }

    Column (
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)


    ){
        TextoBranco(
            texto = "Data",
            tamanhoFonte = 12
        )

        if (showDatePickerDialog) {
            DatePickerDialog(
                onDismissRequest = { showDatePickerDialog = false },
                confirmButton = {
                    Button(
                        onClick = {
                            datePickerState
                                .selectedDateMillis?.let { millis ->
                                    selectedDate = millis.toBrazilianDateFormat()

                                    val formattedDate = selectedDate.toDatabaseDateFormat()
                                    onDateChange(formattedDate)
                                }
                            showDatePickerDialog = false
                        },
                        colors = ButtonDefaults.buttonColors(Color.Blue)
                    ) {
                        Text(text = "Selecionar")
                    }
                }) {
                DatePicker(state = datePickerState)
            }
        }
        TextField(
            value = selectedDate,
            onValueChange = {},
            Modifier
                .fillMaxWidth()
                .onFocusEvent {
                    if (it.isFocused) {
                        showDatePickerDialog = true
                        focusManager.clearFocus(force = true)
                    }
                }
                .onFocusChanged {
                    color = if (it.isFocused) Color.Blue else Color.Blue
                },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Gray,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Blue,
            )
        )
    }
}

fun String.toDatabaseDateFormat(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale("pt-br"))
    val date = formatter.parse(this)
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
}

fun Long.toBrazilianDateFormat(pattern: String = "dd/MM/yyyy"): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(pattern, Locale("pt-br"))
    return formatter.format(date)
}


@Preview(showBackground = true)
@Composable
fun DatePreview1() {
    Date()
}
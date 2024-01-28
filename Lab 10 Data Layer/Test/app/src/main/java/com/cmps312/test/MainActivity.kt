package com.cmps312.test

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.cmps312.test.ui.theme.TestTheme
import java.sql.Time
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import java.util.Locale


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    Dialog(onDismissRequest = { /*TODO*/ }) {
                        val currentDate = LocalDateTime.now()
                        val calendar = Calendar.getInstance()
                        var showHideDialog by  remember {
                            mutableStateOf(false)
                        }
                        calendar.set(
                            currentDate.year,
                            currentDate.monthValue,
                            currentDate.dayOfMonth
                        ) // add year, month (Jan), date

                        // set the initial date
                        val datePickerState =
                            rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)
                        val timePickerState = rememberTimePickerState(initialHour = currentDate.hour , initialMinute = currentDate.minute)

                        Column {
                            if (showHideDialog){
//                                DatePicker(
//                                    state = datePickerState
//                                )
                              Column(modifier = Modifier.fillMaxSize()) {
                                  Button(onClick = { showHideDialog = false }) {
                                      Text(text = "OK")
                                  }
                                  TimePicker(state = timePickerState)
                              }
                            }

                            val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.ROOT)
                            Text(
                                text = "Selected date: ${formatter.format(Date(datePickerState.selectedDateMillis!!))}"
                            )
                            Text(
                                text = "Selected Hour is : ${timePickerState.hour} : ${timePickerState.minute} : ${timePickerState}"
                            )
                            Button(onClick = { showHideDialog = true }) {
                                Text(text = "Select Date")
                            }
                        }
                    }


                }
            }
        }
    }
}


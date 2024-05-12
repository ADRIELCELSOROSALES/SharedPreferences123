package com.example.timepicker

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    private lateinit var etTime: EditText // Declarando la variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Encontrando la vista EditText por su id
        etTime = findViewById(R.id.etTime)
        // Recuperar la hora seleccionada de SharedPreferences
        val sharedPreferences = getSharedPreferences("MiAppPreferences", Context.MODE_PRIVATE)
        val horaSeleccionada = sharedPreferences.getString("horaSeleccionada", "")

        // Mostrar la hora seleccionada en el EditText
        etTime.setText("Has seleccionado las $horaSeleccionada")

        // Estableciendo el OnClickListener después de haber obtenido la referencia
        etTime.setOnClickListener { showTimePickerDialog() }
    }

    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment { onTimeSelected(it) }
        timePicker.show(supportFragmentManager, "time")
    }

    private fun onTimeSelected(time: String) {
        // Guardar la hora seleccionada en SharedPreferences
        val sharedPreferences = getSharedPreferences("MiAppPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("horaSeleccionada", time)
        editor.apply()

        // Actualizar el EditText con la hora seleccionada
        etTime.setText("Has seleccionado las $time")    }
}
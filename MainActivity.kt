package com.example.conversionapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    // Initializing the Input text , spinners and text view
    private lateinit var inputValueLayout: TextInputLayout
    private lateinit var output: TextView
    private lateinit var convertFrom: Spinner
    private lateinit var convertTo: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputValueLayout = findViewById(R.id.inputValue)
        output = findViewById(R.id.Output)
        convertFrom = findViewById(R.id.convertFrom)
        convertTo = findViewById(R.id.convertTo)

        convertFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                convertValue()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        convertTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                convertValue()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    // function to convert the values
    fun convertValue() {
        // Getting the value from user
        val inputValueString = inputValueLayout.editText?.text.toString()
        val inputValue = inputValueString.toFloatOrNull()

        if (inputValue != null) {
            val result = when (convertFrom.selectedItem.toString()) {
                "Kilometers" -> when (convertTo.selectedItem.toString()) {
                    "Miles" -> inputValue * 0.62
                    "Centimeters" -> inputValue * 100000
                    "Inches" -> inputValue * 39370
                    "Kilometers" -> inputValue
                    else -> null // if the convertTo is something else other than the given value it is considered as null
                }
                "Miles" -> when (convertTo.selectedItem.toString()) {
                    "Kilometers" -> inputValue * 1.61
                    "Centimeters" -> inputValue * 160934
                    "Inches" -> inputValue * 63360
                    "Miles" -> inputValue
                    else -> null
                }
                "Centimeters" -> when (convertTo.selectedItem.toString()) {
                    "Kilometers" -> inputValue / 100000
                    "Miles" -> inputValue / 160934
                    "Inches" -> inputValue / 2.54
                    "Centimeters" -> inputValue
                    else -> null
                }
                "Inches" -> when (convertTo.selectedItem.toString()) {
                    "Kilometers" -> inputValue / 39370
                    "Miles" -> inputValue / 63360
                    "Centimeters" -> inputValue * 2.54
                    "Inches" -> inputValue
                    else -> null
                }
                "Celsius" -> when (convertTo.selectedItem.toString()) {
                    "Kelvin" -> inputValue + 273.15
                    "Fahrenheit" -> inputValue * 1.8 + 32
                    "Celsius" -> inputValue
                    else -> null
                }
                "Kelvin" -> when (convertTo.selectedItem.toString()) {
                    "Celsius" -> inputValue - 273.15
                    "Fahrenheit" -> (inputValue - 273.15) * 1.8 + 32
                    "Kelvin" -> inputValue
                    else -> null
                }
                "Fahrenheit" -> when (convertTo.selectedItem.toString()) {
                    "Celsius" -> (inputValue - 32) * 0.56
                    "Kelvin" -> (inputValue - 32) * 0.56 + 273.15
                    "Fahrenheit" -> inputValue
                    else -> null
                }
                else -> null
            }
            
            if (result != null) {
                output.text = result.toString()
            } else {
                //if the result is not null it prints the following output 
                output.text = "Unable to convert"
            }
        } else {
            // if the input value is null then the following output is displayed
            output.text = "Enter a valid input value"
        }
    }
}



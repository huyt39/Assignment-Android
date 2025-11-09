package com.example.currencyconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var spinnerFrom: Spinner
    private lateinit var spinnerTo: Spinner
    private lateinit var editTextFrom: EditText
    private lateinit var editTextTo: EditText
    
    // ty gia co dinh (so voi USD)
    private val EXCHANGE_RATES = doubleArrayOf(
        1.0,      // USD
        0.85,     // EUR
        0.73,     // GBP
        110.0,    // JPY
        1.25,     // CAD
        1.35,     // AUD
        0.92,     // CHF
        6.45,     // CNY
        75.0,     // INR
        1.15,     // SGD
        8.5,      // HKD
        1.4       // NZD
    )
    
    private val currencies = arrayOf(
        "USD", "EUR", "GBP", "JPY", "CAD", "AUD", 
        "CHF", "CNY", "INR", "SGD", "HKD", "NZD"
    )
    
    private var selectedFromIndex = 0
    private var selectedToIndex = 0
    private var isUpdating = false
    private var isUpdatingFrom = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        spinnerFrom = findViewById(R.id.spinnerFrom)
        spinnerTo = findViewById(R.id.spinnerTo)
        editTextFrom = findViewById(R.id.editTextFrom)
        editTextTo = findViewById(R.id.editTextTo)
        
        // setup spinners
        val adapter = ArrayAdapter(this, 
            android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        
        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter
        
        // setup listeners
        spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedFromIndex = position
                convertCurrency()
            }
            
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        
        spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedToIndex = position
                convertCurrency()
            }
            
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        
        editTextFrom.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            
            override fun afterTextChanged(s: Editable?) {
                if (!isUpdating && !isUpdatingFrom) {
                    isUpdatingFrom = true
                    convertCurrencyFromTo()
                    isUpdatingFrom = false
                }
            }
        })
        
        editTextTo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            
            override fun afterTextChanged(s: Editable?) {
                if (!isUpdating && !isUpdatingFrom) {
                    isUpdatingFrom = true
                    convertCurrencyToFrom()
                    isUpdatingFrom = false
                }
            }
        })
    }
    
    private fun convertCurrencyFromTo() {
        val input = editTextFrom.text.toString().trim()
        if (input.isEmpty()) {
            if (!isUpdating) {
                isUpdating = true
                editTextTo.setText("")
                isUpdating = false
            }
            return
        }
        
        try {
            val amount = input.toDouble()
            
            // chuyen doi tu currency tu sang USD
            val amountInUSD = amount / EXCHANGE_RATES[selectedFromIndex]
            
            // chuyen doi tu USD sang currency dich
            val result = amountInUSD * EXCHANGE_RATES[selectedToIndex]
            
            isUpdating = true
            editTextTo.setText(String.format("%.2f", result))
            isUpdating = false
        } catch (e: NumberFormatException) {
            if (!isUpdating) {
                isUpdating = true
                editTextTo.setText("")
                isUpdating = false
            }
        }
    }
    
    private fun convertCurrencyToFrom() {
        val input = editTextTo.text.toString().trim()
        if (input.isEmpty()) {
            if (!isUpdating) {
                isUpdating = true
                editTextFrom.setText("")
                isUpdating = false
            }
            return
        }
        
        try {
            val amount = input.toDouble()
            
            // chuyen doi tu currency dich sang USD
            val amountInUSD = amount / EXCHANGE_RATES[selectedToIndex]
            
            // chuyen doi tu USD sang currency tu
            val result = amountInUSD * EXCHANGE_RATES[selectedFromIndex]
            
            isUpdating = true
            editTextFrom.setText(String.format("%.2f", result))
            isUpdating = false
        } catch (e: NumberFormatException) {
            if (!isUpdating) {
                isUpdating = true
                editTextFrom.setText("")
                isUpdating = false
            }
        }
    }
    
    private fun convertCurrency() {
        convertCurrencyFromTo()
    }
}


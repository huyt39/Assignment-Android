package com.example.currencyconverter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerFrom, spinnerTo;
    private EditText editTextFrom, editTextTo;
    
    // Tỷ giá cố định (so với USD)
    private static final double[] EXCHANGE_RATES = {
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
    };
    
    private String[] currencies = {
        "USD", "EUR", "GBP", "JPY", "CAD", "AUD", 
        "CHF", "CNY", "INR", "SGD", "HKD", "NZD"
    };
    
    private int selectedFromIndex = 0;
    private int selectedToIndex = 0;
    private boolean isUpdating = false;
    private boolean isUpdatingFrom = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        editTextFrom = findViewById(R.id.editTextFrom);
        editTextTo = findViewById(R.id.editTextTo);
        
        // Setup Spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 
            android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
        
        // Setup Listeners
        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedFromIndex = position;
                convertCurrency();
            }
            
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        
        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedToIndex = position;
                convertCurrency();
            }
            
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        
        editTextFrom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            
            @Override
            public void afterTextChanged(Editable s) {
                if (!isUpdating && !isUpdatingFrom) {
                    isUpdatingFrom = true;
                    convertCurrencyFromTo();
                    isUpdatingFrom = false;
                }
            }
        });
        
        editTextTo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            
            @Override
            public void afterTextChanged(Editable s) {
                if (!isUpdating && !isUpdatingFrom) {
                    isUpdatingFrom = true;
                    convertCurrencyToFrom();
                    isUpdatingFrom = false;
                }
            }
        });
    }
    
    private void convertCurrencyFromTo() {
        String input = editTextFrom.getText().toString().trim();
        if (input.isEmpty()) {
            if (!isUpdating) {
                isUpdating = true;
                editTextTo.setText("");
                isUpdating = false;
            }
            return;
        }
        
        try {
            double amount = Double.parseDouble(input);
            
            // Chuyển đổi từ currency từ sang USD
            double amountInUSD = amount / EXCHANGE_RATES[selectedFromIndex];
            
            // Chuyển đổi từ USD sang currency đích
            double result = amountInUSD * EXCHANGE_RATES[selectedToIndex];
            
            isUpdating = true;
            editTextTo.setText(String.format("%.2f", result));
            isUpdating = false;
        } catch (NumberFormatException e) {
            if (!isUpdating) {
                isUpdating = true;
                editTextTo.setText("");
                isUpdating = false;
            }
        }
    }
    
    private void convertCurrencyToFrom() {
        String input = editTextTo.getText().toString().trim();
        if (input.isEmpty()) {
            if (!isUpdating) {
                isUpdating = true;
                editTextFrom.setText("");
                isUpdating = false;
            }
            return;
        }
        
        try {
            double amount = Double.parseDouble(input);
            
            // Chuyển đổi từ currency đích sang USD
            double amountInUSD = amount / EXCHANGE_RATES[selectedToIndex];
            
            // Chuyển đổi từ USD sang currency từ
            double result = amountInUSD * EXCHANGE_RATES[selectedFromIndex];
            
            isUpdating = true;
            editTextFrom.setText(String.format("%.2f", result));
            isUpdating = false;
        } catch (NumberFormatException e) {
            if (!isUpdating) {
                isUpdating = true;
                editTextFrom.setText("");
                isUpdating = false;
            }
        }
    }
    
    private void convertCurrency() {
        convertCurrencyFromTo();
    }
}


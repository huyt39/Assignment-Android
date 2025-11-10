package com.example.numberlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var editTextLimit: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioOdd: RadioButton
    private lateinit var radioEven: RadioButton
    private lateinit var radioPrime: RadioButton
    private lateinit var radioSquare: RadioButton
    private lateinit var radioPerfect: RadioButton
    private lateinit var radioFibonacci: RadioButton
    private lateinit var listViewNumbers: ListView
    private lateinit var textViewEmpty: TextView
    private lateinit var adapter: ArrayAdapter<String>
    private val numberList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        editTextLimit = findViewById(R.id.editTextLimit)
        radioGroup = findViewById(R.id.radioGroup)
        radioOdd = findViewById(R.id.radioOdd)
        radioEven = findViewById(R.id.radioEven)
        radioPrime = findViewById(R.id.radioPrime)
        radioSquare = findViewById(R.id.radioSquare)
        radioPerfect = findViewById(R.id.radioPerfect)
        radioFibonacci = findViewById(R.id.radioFibonacci)
        listViewNumbers = findViewById(R.id.listViewNumbers)
        textViewEmpty = findViewById(R.id.textViewEmpty)
        
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numberList)
        listViewNumbers.adapter = adapter
        
        // dam bao chi chon duoc 1 radio button - khi click vao bat ky radio nao, set no la checked
        radioOdd.setOnClickListener { 
            radioGroup.check(R.id.radioOdd)
        }
        radioEven.setOnClickListener { 
            radioGroup.check(R.id.radioEven)
        }
        radioPrime.setOnClickListener { 
            radioGroup.check(R.id.radioPrime)
        }
        radioSquare.setOnClickListener { 
            radioGroup.check(R.id.radioSquare)
        }
        radioPerfect.setOnClickListener { 
            radioGroup.check(R.id.radioPerfect)
        }
        radioFibonacci.setOnClickListener { 
            radioGroup.check(R.id.radioFibonacci)
        }
        
        // set default selection
        radioOdd.isChecked = true
        
        // listeners
        editTextLimit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            
            override fun afterTextChanged(s: Editable?) {
                updateNumberList()
            }
        })
        
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            updateNumberList()
        }
        
        // khong goi updateNumberList() ngay vi EditText chua co gia tri
    }
    
    private fun updateNumberList() {
        val input = editTextLimit.text.toString().trim()
        if (input.isEmpty()) {
            numberList.clear()
            adapter.notifyDataSetChanged()
            showEmptyMessage(true)
            return
        }
        
        try {
            val limit = input.toInt()
            if (limit <= 0) {
                numberList.clear()
                adapter.notifyDataSetChanged()
                showEmptyMessage(true)
                return
            }
            
            numberList.clear()
            
            val checkedId = radioGroup.checkedRadioButtonId
            when (checkedId) {
                R.id.radioOdd -> generateOddNumbers(limit)
                R.id.radioEven -> generateEvenNumbers(limit)
                R.id.radioPrime -> generatePrimeNumbers(limit)
                R.id.radioSquare -> generateSquareNumbers(limit)
                R.id.radioPerfect -> generatePerfectNumbers(limit)
                R.id.radioFibonacci -> generateFibonacciNumbers(limit)
                else -> {
                    // neu khong co radio nao duoc chon
                    numberList.clear()
                }
            }
            
            adapter.notifyDataSetChanged()
            showEmptyMessage(numberList.isEmpty())
        } catch (e: NumberFormatException) {
            numberList.clear()
            adapter.notifyDataSetChanged()
            showEmptyMessage(true)
        }
    }
    
    private fun showEmptyMessage(show: Boolean) {
        if (show) {
            listViewNumbers.visibility = View.GONE
            textViewEmpty.visibility = View.VISIBLE
        } else {
            listViewNumbers.visibility = View.VISIBLE
            textViewEmpty.visibility = View.GONE
        }
    }
    
    private fun generateOddNumbers(limit: Int) {
        var i = 1
        while (i < limit) {
            numberList.add(i.toString())
            i += 2
        }
    }
    
    private fun generateEvenNumbers(limit: Int) {
        var i = 2
        while (i < limit) {
            numberList.add(i.toString())
            i += 2
        }
    }
    
    private fun generatePrimeNumbers(limit: Int) {
        for (i in 2 until limit) {
            if (isPrime(i)) {
                numberList.add(i.toString())
            }
        }
    }
    
    private fun generateSquareNumbers(limit: Int) {
        var i = 1
        while (i * i < limit) {
            numberList.add((i * i).toString())
            i++
        }
    }
    
    private fun generatePerfectNumbers(limit: Int) {
        for (i in 1 until limit) {
            if (isPerfect(i)) {
                numberList.add(i.toString())
            }
        }
    }
    
    private fun generateFibonacciNumbers(limit: Int) {
        if (limit <= 1) return
        
        var a = 1
        var b = 1
        
        numberList.add(a.toString())
        
        while (b < limit) {
            val next = a + b
            if (next >= limit) break
            numberList.add(next.toString())
            a = b
            b = next
        }
    }
    
    private fun isPrime(n: Int): Boolean {
        if (n < 2) return false
        if (n == 2) return true
        if (n % 2 == 0) return false
        var i = 3
        while (i * i <= n) {
            if (n % i == 0) return false
            i += 2
        }
        return true
    }
    
    private fun isPerfect(n: Int): Boolean {
        if (n < 2) return false
        var sum = 1
        var i = 2
        while (i * i <= n) {
            if (n % i == 0) {
                sum += i
                if (i * i != n) {
                    sum += n / i
                }
            }
            i++
        }
        return sum == n
    }
}

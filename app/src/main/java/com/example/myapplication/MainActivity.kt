package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.viewmodel.SuggestionViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var suggestionViewModel: SuggestionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        autoCompleteTextView = findViewById(R.id.autoCompleteTextview)
        suggestionViewModel = ViewModelProvider(this).get(SuggestionViewModel::class.java)

        autoCompleteTextView.addTextChangedListener { object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                TODO("Not yet implemented")
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                suggestionViewModel.getAutoCompleteSuggestions(s.toString()).observe(this@MainActivity,
                 Observer { suggestions ->
                        suggestions?.let{
                            val adapter = ArrayAdapter<String>(this@MainActivity,
                                android.R.layout.simple_dropdown_item_1line,it.map{item ->
                                    item.login
                                })
                            autoCompleteTextView.setAdapter(adapter)
                        }
                    })
            }
        } }
    }
}

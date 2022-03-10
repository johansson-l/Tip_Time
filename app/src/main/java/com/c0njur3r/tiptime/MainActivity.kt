package com.c0njur3r.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.c0njur3r.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

/*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}*/

class MainActivity : AppCompatActivity() {

    /**
     * This line declares a top-level variable in the class for the
     * binding object. It's defined at this level because it will
     * be used across multiple methods in MainActivity class.
     */
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * This line initializes the binding object which you'll
         * use to access Views in the activity_main.xml layout.
         */
        binding = ActivityMainBinding.inflate(layoutInflater)
        /**
         * Set the content view of the activity. Instead of passing
         * the resource ID of the layout, R.layout.activity_main,
         * this specifies the root of the hierarchy of views in
         * your app, binding.root
         */
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    fun calculateTip() {
        // Saves the input as an String variable
        val stringInTextView = binding.costOfService.text.toString()
        // Then convert the input to a Double
        val cost = stringInTextView.toDouble()
        // Get the selected radiobutton
        val selectedId = binding.tipOptions.checkedRadioButtonId
        // Then store the percentage of that option
        val tipPercentage = when (selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        // Calculate the tip
        var tip = tipPercentage * cost
        // Check if the round up switch is on/off
        val roundUp = binding.roundUpSwitch.isChecked
        // Round up the tip if the switch is on
        if (roundUp) {
            tip = ceil(tip)
        }
        // Convert to use a currency instance to display the cost
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        // Now set the text
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}


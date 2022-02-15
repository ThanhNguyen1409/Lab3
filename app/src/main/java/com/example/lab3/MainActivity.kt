package com.example.lab3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.lab3.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculate.setOnClickListener{ calculateTip() }
    }
    private fun calculateTip() {
        val stringText = binding.Const.text.toString()
        val cost = stringText.toDoubleOrNull()
        if (cost == null) {
            binding.tip.text = "0"
            return
        }
        val tipPercen = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_eighteen_percent -> 0.18
            R.id.option_twenty_percent -> 0.20
            else -> 0.15
        }
        var tip = tipPercen * cost
        if (binding.round.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tip.text = getString(R.string.tip_amount, formattedTip)
    }
}
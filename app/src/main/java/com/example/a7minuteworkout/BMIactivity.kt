package com.example.a7minuteworkout

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minuteworkout.databinding.ActivityBmiactivityBinding
import java.math.BigDecimal

class BMIactivity : AppCompatActivity() {

    companion object{
        private const val METRIC_UNITS_VIEW="METRIC_UNIT_VIEW"
        private  const val US_UNITS_VIEW="US_UNIT_VIEW"
    }

    private var currentVisibleView:String= METRIC_UNITS_VIEW

    private var binding :ActivityBmiactivityBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmiActivity)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="CALCULATE BMI"
        }

        binding?.toolbarBmiActivity?.setNavigationOnClickListener{
            onBackPressed()
        }

        makeVisibleMetricUnits()
        binding?.rgUnits?.setOnCheckedChangeListener{_,checkedID:Int->
            if(checkedID==R.id.rbMetricUnits){
                makeVisibleMetricUnits()
            }else{
                makeVisibleUSunits()
            }
        }

        binding?.btnBMI?.setOnClickListener {
            if(currentVisibleView== METRIC_UNITS_VIEW){
                bmiCalculator()
            }else{
                bmiUsCalculator()
            }
        }
    }


    private fun makeVisibleMetricUnits(){
        currentVisibleView= METRIC_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility=View.VISIBLE
        binding?.tilHeightMetric?.visibility=View.VISIBLE
        binding?.tilMetricUnitHeightFeet?.visibility=View.GONE
        binding?.tilMetricUnitHeightInch?.visibility=View.GONE

        binding?.etMetricUnitHeightFeet?.text!!.clear()
        binding?.etMetricUnitWeight?.text!!.clear()
        binding?.etMetricUnitHeightInch?.text!!.clear()

        binding?.etMetricUnitWeight?.hint="WEIGHT (kg)"

        binding?.llTexts?.visibility=View.INVISIBLE

    }

    private fun makeVisibleUSunits(){
        currentVisibleView= US_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility=View.VISIBLE
        binding?.tilHeightMetric?.visibility=View.GONE
        binding?.tilMetricUnitHeightFeet?.visibility=View.VISIBLE
        binding?.tilMetricUnitHeightInch?.visibility=View.VISIBLE
        binding?.etMetricUnitWeight?.hint="WEIGHT (llb)"

        binding?.etHeightMetric?.text!!.clear()
        binding?.etMetricUnitWeight?.text!!.clear()

        binding?.llTexts?.visibility=View.INVISIBLE
    }


    private fun bmiUsCalculator() {

        var isValid = true
        if (binding?.etMetricUnitWeight?.text?.toString()?.isEmpty() == true) {
            Toast.makeText(this, "please enter weight ", Toast.LENGTH_SHORT).show()
            isValid = false
        }
        if (binding?.etMetricUnitHeightFeet?.text?.toString()?.isEmpty() == true) {
            Toast.makeText(this, "please enter feet ", Toast.LENGTH_SHORT).show()
            isValid = false
        }
        if (binding?.etMetricUnitHeightInch?.text?.toString()?.isEmpty() == true) {
            Toast.makeText(this, "please enter inch ", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        if (isValid) {
            var weight: Double? = binding?.etMetricUnitWeight?.text?.toString()?.toDouble()
            var height: Double? = binding?.etMetricUnitHeightFeet?.text?.toString()?.toDouble()!! * 12 + binding?.etMetricUnitHeightInch?.text?.toString()?.toDouble()!!
            var bmi: Double? = weight?.div(height!! * height)!!*703;
            bmi = String.format("%.1f", bmi).toDouble()
            binding?.tvBMIvalue?.text = bmi?.toString()

            when (bmi) {
                in 0.0..18.5 -> {
                    binding?.tvWeight?.text = "UNDERWEIGHT"
                    binding?.tvMessage?.text = "you need to work on your diet"
                }
                in 18.5..24.9 -> {
                    binding?.tvWeight?.text = "NORMAL"
                    binding?.tvMessage?.text = "you are doing well"
                }
                in 25.0..29.9 -> {
                    binding?.tvWeight?.text = "OVERWEIGHT"
                    binding?.tvMessage?.text = "you need to work out more often"
                }
                in 30.0..99999.9 -> {
                    binding?.tvWeight?.text = "OBESITY"
                    binding?.tvMessage?.text =
                        "you need to work on your diet and workout more often"
                }
            }
            binding?.llTexts?.visibility = View.VISIBLE
        }
    }
    
    
    
    private fun bmiCalculator(){
         var isValid=true
        if(binding?.etMetricUnitWeight?.text?.toString()?.isEmpty() == true){
            Toast.makeText(this, "please enter weight ", Toast.LENGTH_SHORT).show()
            isValid=false
        }
        if(binding?.etHeightMetric?.text?.toString()?.isEmpty() == true){
            Toast.makeText(this, "please enter height ", Toast.LENGTH_SHORT).show()
            isValid=false
        }

        if(isValid){
            var weight:Double?=binding?.etMetricUnitWeight?.text?.toString()?.toDouble()
            var height:Double?=binding?.etHeightMetric?.text?.toString()?.toDouble()
            height=height?.div(100)
            var bmi:Double?=weight?.div(height!! *height);
            bmi = String.format("%.1f", bmi).toDouble()
            binding?.tvBMIvalue?.text=bmi?.toString()

            when(bmi){
                in 0.0..18.5->{
                    binding?.tvWeight?.text="UNDERWEIGHT"
                    binding?.tvMessage?.text="you need to work on your diet"
                }
                in 18.5..24.9 ->{
                    binding?.tvWeight?.text="NORMAL"
                    binding?.tvMessage?.text="you are doing well"
                }
                in 25.0..29.9 ->{
                    binding?.tvWeight?.text="OVERWEIGHT"
                    binding?.tvMessage?.text="you need to work out more often"
                }
                in 30.0..99999.9 ->{
                    binding?.tvWeight?.text="OBESITY"
                    binding?.tvMessage?.text="you need to work on your diet and workout more often"
                }
            }
            binding?.llTexts?.visibility=View.VISIBLE
        }


    }


}
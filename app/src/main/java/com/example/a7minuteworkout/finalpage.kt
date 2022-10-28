package com.example.a7minuteworkout

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.a7minuteworkout.databinding.ActivityExcerciseBinding
import com.example.a7minuteworkout.databinding.ActivityMainBinding
import com.example.a7minuteworkout.databinding.FinalpageBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class finalpage:AppCompatActivity() {

    private var binding:FinalpageBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=FinalpageBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarFinal)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarFinal?.setNavigationOnClickListener{
            onBackPressed()
        }
        binding?.btnFinish?.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val historyDao=(application as WorkoutApp).db.historyDao()
        addDateToDatabase(historyDao)

    }
    private fun addDateToDatabase(historyDao:HistoryDao){

        val c=Calendar.getInstance()
        val dateTime=c.time
        Log.e("Date: ",""+dateTime)

        val sdf=SimpleDateFormat("dd MM yyyy HH:mm:ss",Locale.getDefault())

        val date=sdf.format(dateTime)
        Log.e("formated date: ",""+date)
        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date))
            Log.e("Date: ","Added...")
        }
    }

}

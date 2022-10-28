package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minuteworkout.databinding.ActivityBmiactivityBinding.inflate
import com.example.a7minuteworkout.databinding.ActivityExcerciseBinding
import com.example.a7minuteworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.launch

class historyActivity : AppCompatActivity() {

    private var binding:ActivityHistoryBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarHistory)

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="HISTORY"
        }
        binding?.toolbarHistory?.setNavigationOnClickListener{
            onBackPressed()
        }

        val historyDao=(application as WorkoutApp).db.historyDao()
        getAllCompleteDate(historyDao)
    }

    private fun getAllCompleteDate(historyDao:HistoryDao){
        lifecycleScope.launch{
            historyDao.fetchAllDates().collect{ allCompletedDatesList ->
                if(allCompletedDatesList.isNotEmpty()){
                    binding?.tvHistory?.visibility= View.VISIBLE
                    binding?.rvHistory?.visibility=View.VISIBLE
                    binding?.tvNoData?.visibility=View.INVISIBLE

                    binding?.rvHistory?.layoutManager=LinearLayoutManager(this@historyActivity)

                    val dates=ArrayList<String>()
                    for(date in allCompletedDatesList){
                        dates.add(date.date)
                    }
                    val historyAdapter=HistoryAdapter(dates)
                    binding?.rvHistory?.adapter=historyAdapter

                }else{
                    binding?.tvHistory?.visibility= View.GONE
                    binding?.rvHistory?.visibility=View.GONE
                    binding?.tvNoData?.visibility=View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}
package com.example.a7minuteworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minuteworkout.databinding.ActivityExcerciseBinding
import com.example.a7minuteworkout.databinding.DialogCustomBackConfirmationBinding
import java.util.*
import kotlin.collections.ArrayList

class ExcerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

   private var binding:ActivityExcerciseBinding?=null

    private var restTimer: CountDownTimer?=null
    private var restProgress=0

    private var excerciseList:ArrayList<ExcerciseModel>?=null

    private var currentExcercisePosition=-1;

    private var tts: TextToSpeech?=null
    private var player: MediaPlayer?=null

    private var restTimerDuration:Long=10
    private var excerciseTimerDuration:Long=30

    private var excerciseAdapter:ExcerciseStatusAdapter?=null

    private fun setupExerciseStatusRecyclerView(){
        binding?.rvExcerciseStatus?.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        excerciseAdapter= ExcerciseStatusAdapter(excerciseList!!)
        binding?.rvExcerciseStatus?.adapter=excerciseAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityExcerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExcercise)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        tts= TextToSpeech(this,this)
        excerciseList=Constants.defaultExcerciseList()

        binding?.tvExcersieLast?.visibility= View.INVISIBLE

        binding?.toolbarExcercise?.setNavigationOnClickListener{
            customDialogForBackButton()
        }
        setupRestView()
        setupExerciseStatusRecyclerView()
    }

    override fun onBackPressed() {
        customDialogForBackButton()

    }

    private fun customDialogForBackButton() {
        val customDialog=Dialog(this)
        val dialogBinding=DialogCustomBackConfirmationBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.btnYes.setOnClickListener {
            this@ExcerciseActivity.finish()
            customDialog.dismiss()
        }
        dialogBinding.btnNo.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }


    private fun setRestProgressBar(){
        binding?.progressBar?.progress=restProgress
        binding?.progressBar?.max=10
            binding?.ivExcercise?.setImageResource(R.drawable.poster)

        binding?.tvTitle?.text="GET READY FOR "
        binding?.tvExcersieLast?.text=excerciseList?.get(currentExcercisePosition+1)?.getname()
        binding?.tvExcersieLast?.visibility=View.VISIBLE

        speakOut("please rest")
        restTimer=object:CountDownTimer(restTimerDuration *1000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.progressBar?.progress=10-restProgress
                binding?.tvTimer?.text=(10-restProgress).toString()
            }
            override fun onFinish() {
                Toast.makeText(this@ExcerciseActivity, "start the exercise", Toast.LENGTH_SHORT).show()
                currentExcercisePosition++

                excerciseList!![currentExcercisePosition].setIsSelected(true)
                excerciseAdapter!!.notifyDataSetChanged()
                    setExcerciseProgressBar()
            }
        }.start()
    }
    private fun setExcerciseProgressBar(){
        restProgress=0
        binding?.tvExcersieLast?.visibility=View.INVISIBLE
        binding?.tvTitle?.text=excerciseList?.get(currentExcercisePosition)?.getname()
        binding?.progressBar?.progress=restProgress
        binding?.progressBar?.max=30
        excerciseList?.get(currentExcercisePosition)?.getImage()
            ?.let { binding?.ivExcercise?.setImageResource(it) }

        speakOut(excerciseList!![currentExcercisePosition].getname())
        restTimer=object:CountDownTimer(excerciseTimerDuration*1000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.progressBar?.progress=30-restProgress
                binding?.tvTimer?.text=(30-restProgress).toString()
            }
            override fun onFinish() {
                Toast.makeText(this@ExcerciseActivity, "rest", Toast.LENGTH_SHORT).show()
                excerciseList!![currentExcercisePosition].setIsSelected(false)
                excerciseList!![currentExcercisePosition].setIsCompleted(true)
                excerciseAdapter!!.notifyDataSetChanged()
                if(currentExcercisePosition<excerciseList!!.size-1){
                    setupRestView()
                }else{
                    Toast.makeText(this@ExcerciseActivity, "congrats you have finished 7 minute workout", Toast.LENGTH_LONG).show()
                    val intent= Intent(this@ExcerciseActivity,finalpage::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }.start()
    }

    private fun setupRestView(){

        try{
            val soundURI=Uri.parse("android.resource://com.example.a7minuteworkout/"+R.raw.press_start)
            player=MediaPlayer.create(applicationContext,soundURI)
            player?.isLooping=false
            player?.start()
        }

        catch (e:Exception){
            e.printStackTrace()
        }

        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }
        setRestProgressBar()
    }

    override fun onDestroy(){
        super.onDestroy()
        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }
        if(tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }

        binding=null
    }

    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.UK)
            if(result == TextToSpeech.LANG_MISSING_DATA || result== TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("tts","the language is not supported")
            }
        }else{
            Log.e("TTS","initialization failed")
        }
    }
    private  fun speakOut(text:String){
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }
}
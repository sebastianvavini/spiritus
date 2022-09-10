package com.goodbit.spiritus

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import com.goodbit.spiritus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener  {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dinamicButton: Button

    var running = false
    var pausetopwatch: Long =0
    private var count:Int =0

    private  var duracaoRespira: Tempo= Tempo()


    private var duracaoAcao:Tempo= Tempo()
    var status:String="Parado"




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding = ActivityMainBinding.inflate(layoutInflater)

        fazerBinding()

        setContentView(binding.root)
        addOnClickListeners()
       /**  binding.start.setOnClickListener {
            Starttimer()
        } **/
       /* binding.pause.setOnClickListener{
            pausetimer()
        }
        */

        /* binding.reset.setOnClickListener{
            resettimer()
        }

         */
    }
    private fun fazerBinding(){
        dinamicButton=binding.dinamicButton
    }
    private fun addOnClickListeners(){
        binding.dinamicButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.dinamicButton) {

            count=++count
            binding.countText.setText("ESTALOS: ${count.toString()}")



            if(dinamicButton.text=="RESPIRA") {
                println("Inicio da Respiração...")
                status="RESPIRANDO"
                dinamicButton.setText("AÇÃO")
                dinamicButton.setBackgroundColor(Color.BLACK)
                binding.stopwatch.setTextColor(Color.BLUE)
                somarAcao(binding.stopwatch.text.toString())
                Starttimer()
                resettimer()



            }else {
                println("inicio da Ação...")
                dinamicButton.setText("RESPIRA")
                dinamicButton.setBackgroundColor(Color.RED)
                binding.stopwatch.setTextColor(Color.RED)

                somarRespira(binding.stopwatch.text.toString())
                if(count >=1){

                    binding.timeRespiraText.setText("RESPIRANDO:${duracaoRespira.toString()}")
                }
                resettimer()
            }

        }
    }

    private fun Starttimer(){
        if (!running){
            binding.stopwatch.base = SystemClock.elapsedRealtime() -pausetopwatch
            binding.stopwatch.start()
            running =true
        }

    }

    private  fun  pausetimer(){
        if (running){
            binding.stopwatch.stop()
            pausetopwatch = SystemClock.elapsedRealtime() - binding.stopwatch.base
            running =false
        }
    }


    private  fun resettimer(){
        binding.stopwatch.base = SystemClock.elapsedRealtime()
        pausetopwatch=0
    }
    private fun somarRespira(tempo:String){
       var duracao:Tempo= Tempo().convertStringEmTempo(tempo)
        this.duracaoRespira= this.duracaoRespira.somar(duracao)

    }

    private fun somarAcao(tempo:String){
        this.duracaoAcao=Tempo().convertStringEmTempo(tempo)

    }

}
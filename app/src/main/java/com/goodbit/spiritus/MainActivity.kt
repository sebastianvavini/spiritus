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
    private var inflexoes:Int =0
    private  var duractionbreathing: Tempo= Tempo()
    private var percentRespirando= String()
    private var duractionAction:Tempo= Tempo()
    private var percentEmAcao:String=String()
    private var totalDuraction:Tempo=duractionAction.somar(duractionbreathing)


    var status:String=Constants.STATUS.PARADO


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

            inflexoes=++inflexoes
            binding.countText.setText("${Constants.TEXTVIEW.INFLEXOES}: ${inflexoes.toString()}")

            if(dinamicButton.text==Constants.BUTTON.RESPIRA) {
                modoRespira()

            }else {
               modoAcao()
            }
            calculaPercentagem()
            exibirEstatisticas()


        }
    }
    private fun calculaPercentagem(){
        somarTotal()

        var breathing: Int =duractionbreathing.totalEmSegundos
        var action:Int = duractionAction.totalEmSegundos
        var totalTime:Int= totalDuraction.totalEmSegundos

        if(totalTime>0){
            percentEmAcao=  "%.2f".format((action.toDouble()/totalTime.toDouble()*100))
            percentRespirando= "%.2f".format((breathing.toDouble()/totalTime.toDouble()*100))

        }
    }
    private fun somarTotal(){

        this.totalDuraction= this.duractionAction.somar(duractionbreathing)


    }
    private fun exibirEstatisticas(){

        binding.timeTotalText.setText("TOTAL: ${totalDuraction.toString()}s ")
        binding.percentRespirandoText.setText (
            "RELAXANDO: ${duractionbreathing}/${totalDuraction} ")
        binding.percentConcentrandoText.setText("" +
                "CONCENTRANDO:${duractionAction}/${totalDuraction} ")
        binding.status.setText(status)
    }
    private fun modoRespira(){
        status=Constants.STATUS.RELAX
        dinamicButton.setText("AÇÃO")
        dinamicButton.setBackgroundColor(Color.BLACK)
        binding.stopwatch.setTextColor(Color.BLUE)
        calcularDuracaoAcao(binding.stopwatch.text.toString())


        println(status)
        Starttimer()
        resettimer()
    }
    private fun modoAcao(){
        status=Constants.STATUS.EM_ACAO
        dinamicButton.setText(Constants.BUTTON.RESPIRA)
        dinamicButton.setBackgroundColor(Color.RED)
        binding.stopwatch.setTextColor(Color.RED)

        calcularDuracaoRespira(binding.stopwatch.text.toString())

        println(status)
        resettimer()
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
    private fun calcularDuracaoRespira(tempo:String){
       var duracao:Tempo= Tempo().convertStringEmTempo(tempo)
        this.duractionbreathing= this.duractionbreathing.somar(duracao)

    }

    private fun calcularDuracaoAcao(tempo:String){
        var duracao:Tempo= Tempo().convertStringEmTempo(tempo)
        this.duractionAction= this.duractionAction.somar(duracao)

    }



}
package com.goodbit.spiritus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.goodbit.spiritus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener  {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dinamicButton: Button
    private var relogio:Relogio= Relogio()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding = ActivityMainBinding.inflate(layoutInflater)

        fazerBinding()

        setContentView(binding.root)
        addOnClickListeners()
    }
    private fun fazerBinding(){
        dinamicButton=binding.dinamicButton
    }
    private fun addOnClickListeners(){
        binding.dinamicButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.dinamicButton) {

            relogio.contarTempo(dinamicButton.text.toString())

            if(dinamicButton.text=="RESPIRA") {
                dinamicButton.setText("AÇÃO")



            }else {
                dinamicButton.setText("RESPIRA")

            }
        }
    }
}
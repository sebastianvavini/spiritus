package com.goodbit.spiritus

class Tempo {
     var minutos:Int=0
     var segundos:Int=0
     var totalEmSegundos:Int=0

     lateinit var  list:List<String>


    fun convertEmSegundos():Int{
       this. totalEmSegundos=this.minutos*60+this.segundos
        return totalEmSegundos
    }

    fun convertStringEmTempo(novo:String): Tempo {

        val delim = ":"
        list = novo.split(delim)
        list[0]
        println("minutos: ${list[0]}")
        println("segundos: ${list[1]}")
        this.segundos=list[1].toInt()+this.segundos
        this.minutos=list[0].toInt()+this.minutos
        convertEmSegundos()
       // this.totalEmSegundos= totalEmSegundos()
        return this
    }
    override fun toString():String{
        return this.totalEmSegundos.toString()
    }

    fun somar(tempo:Tempo):Tempo{
        
         var total= this.totalEmSegundos+tempo.totalEmSegundos
        var duracao:Tempo=Tempo()
        duracao.setTotalSegundos(total)

        return duracao
    }

    private fun setTotalSegundos(total: Int) {
        this.totalEmSegundos=total
    }

}
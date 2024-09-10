package com.example.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    private lateinit var textoPergunta: TextView
    private lateinit var botaoResposta1:Button
    private lateinit var botaoResposta2:Button
    private lateinit var botaoResposta3:Button

    private var indicePerguntaAtual = 0

    private val perguntas = listOf(

        Pergunta("Quem Descobriu o Brasil?",
        "Pedro Cabral",
         listOf("Pedro Cabral", "Maria","José")),

        Pergunta("Capital do Brasil?",
            "Brasilia",
            listOf("Sao Paulo", "Brasilia","Bahia")),

        Pergunta("Onde fica a Fatec?",
            "Americana",
            listOf("Limeira", "SBO","Americana"))

        )


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textoPergunta = findViewById(R.id.textoResultado)
        botaoResposta1 = findViewById(R.id.botaoResposta1)
        botaoResposta2 = findViewById(R.id.botaoResposta2)
        botaoResposta3 = findViewById(R.id.botaoResposta3)


        atualizarPergunta()

        botaoResposta1.setOnClickListener {
            verificarResposta(perguntas
                [indicePerguntaAtual].respostas[0])
        }
        botaoResposta2.setOnClickListener {
            verificarResposta(perguntas
                [indicePerguntaAtual].respostas[1])
        }
        botaoResposta3.setOnClickListener {
            verificarResposta(perguntas
                [indicePerguntaAtual].respostas[2])
        }

    }

    //Atualizando a Pergunta e Alternativas
    private fun atualizarPergunta(){

        val perguntaAtual = perguntas[indicePerguntaAtual]

        textoPergunta.text = perguntaAtual.texto

        botaoResposta1.text = perguntaAtual.respostas[0]
        botaoResposta2.text = perguntaAtual.respostas[1]
        botaoResposta3.text = perguntaAtual.respostas[2]

    }


    private fun verificarResposta(resposta: String){

        val mensagem = if(resposta ==
            perguntas[indicePerguntaAtual].respostaCorreta){
            "Correto!"

        }else{

            "Incorreto"
        }

        Toast.makeText(this, mensagem,
        Toast.LENGTH_SHORT).show()

        indicePerguntaAtual = (indicePerguntaAtual + 1) % perguntas.size
        atualizarPergunta()


    }

    data class Pergunta(val texto: String,
                        val respostaCorreta: String,
                        val respostas:List<String>
        )

}
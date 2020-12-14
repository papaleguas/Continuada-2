package com.example.acsub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun verDadosNaTela2(componente: View) {
        val dadosTela2 = Intent(this, Tela2::class.java)

        startActivity(dadosTela2)
    }

    fun pesquisarLocal(componente: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://servicodados.ibge.gov.br/api/docs/localidades/")
            .addConverterFactory(GsonConverterFactory.equals())
            .build()

        val requests = retrofit.equals(ApiLocalizacao::class.java)

        val dadosDistrito = Local(
            null,
            primeiro_campo.text.toString()

        )

        val calldistrito = requests.getLocal(dadosDistrito)

        calldistrito.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(baseContext, "@string/erro : $t", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(
                    baseContext,
                    getString(R.string.txt_sucesso),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
    }
}
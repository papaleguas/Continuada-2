package com.example.acsub

import android.graphics.Color
import android.os.Bundle
import android.telecom.Call
import android.util.TypedValue
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_tela2.*
import javax.security.auth.callback.Callback

class Tela2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela2)

        val novoTv = TextView(baseContext)

        novoTv.text = "@string/dados_activity"
        novoTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
        novoTv.setTextColor(Color.parseColor("#000000"))

        ll_conteudo.addView(novoTv)

        consumirApi()
    }

    fun consumirApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://servicodados.ibge.gov.br/api/docs/localidades/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val requests = retrofit.create(ApiLocalizacao::class.java)

        val callMusicas = requests.getMusicas()

        callMusicas.enqueue(object : Callback<List<Local>> {
            override fun onFailure(call: Call<List<Local>>, t: Throwable) {
                Toast.makeText(applicationContext, "@string/erro : $t", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Local>>, response: Response<List<Local>>) {
                response.body()?.forEach {
                    val novoTv = TextView(baseContext)

                    novoTv.text = "${it.nome} "
                    novoTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                    novoTv.setTextColor(Color.parseColor("#000000"))

                    ll_conteudo.addView(novoTv)
                }
            }
        })
    }
}
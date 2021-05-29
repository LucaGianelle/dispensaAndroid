package com.dispensa

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.popup_insert_aliment.*
import kotlinx.android.synthetic.main.prendi_alimento.*
import kotlinx.android.synthetic.main.prendi_alimento.quantitAliment
import kotlin.concurrent.thread


class Prendi_alimentoActivity: AppCompatActivity() {

    var alimentClick = DbCommunication.getClickedAliment()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prendi_alimento)

        textView.text = alimentClick.nameAliment
        textViewQuant.text = "${alimentClick.quantita} Gr"
        textViewCal.text = "${alimentClick.calorie} Kcal"
        textViewPro.text = "P: ${alimentClick.proteine}"
        textViewGras.text = "G: ${alimentClick.grassi}"
        textViewCarb.text = "C: ${alimentClick.carboidrati}"

        //"${dataUser.eta} anni"
        val buttonsi = findViewById<Button>(R.id.buttonYes)
        val buttonno = findViewById<Button>(R.id.buttonNo)

        buttonsi.setOnClickListener {
            val qt :String = quantitAliment.text.toString()
            if(qt != "")
            {

                thread (start = true){
                    DbCommunication.riduzioneAlimentoPersonale(qt)
                    DbCommunication.addDailyValues(qt.toInt())
                    Thread.sleep(500L)
                    DbCommunication.setMyaliment()
                    Thread.sleep(500L)
                    val disp = Intent (this, DispensaActivity::class.java)
                    startActivity(disp)
                }

            }
        }

        buttonno.setOnClickListener {
            val disp = Intent (this, DispensaActivity::class.java)
            startActivity(disp)
        }
    }
}
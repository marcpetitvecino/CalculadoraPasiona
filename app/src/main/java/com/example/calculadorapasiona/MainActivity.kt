package com.example.calculadorapasiona

import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.lang.Exception
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    private lateinit var button0: Button
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button
    private lateinit var buttonCE: Button
    private lateinit var buttonDel: Button
    private lateinit var buttonComa: Button
    private lateinit var buttonIgual: Button
    private lateinit var input: TextView
    private lateinit var output: TextView
    private lateinit var euros: TextView
    private lateinit var buttonDolar: Button
    private lateinit var buttonLliura: Button
    private lateinit var buttonYen: Button
    private lateinit var buttonYuan: Button
    private lateinit var pasiona: ImageView
    private var conversioDolar: Double = 1.0
    private var conversioLliura: Double = 1.0
    private var conversioYen: Double = 1.0
    private var conversioYuan: Double = 1.0
    private var conversioActual: Double = 1.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initListeners()
        input.text = "0"
        output.text = "0"
    }

    private fun initViews() {
        button0 = findViewById(R.id.btn0)
        button1 = findViewById(R.id.btn1)
        button2 = findViewById(R.id.btn2)
        button3 = findViewById(R.id.btn3)
        button4 = findViewById(R.id.btn4)
        button5 = findViewById(R.id.btn5)
        button6 = findViewById(R.id.btn6)
        button7 = findViewById(R.id.btn7)
        button8 = findViewById(R.id.btn8)
        button9 = findViewById(R.id.btn9)
        buttonCE = findViewById(R.id.btnCE)
        buttonDel = findViewById(R.id.btnDel)
        buttonComa = findViewById(R.id.btnComa)
        buttonIgual = findViewById(R.id.btnIgual)
        input = findViewById(R.id.inputDiners)
        output = findViewById(R.id.outputDiners)
        euros = findViewById(R.id.tvEuros)
        buttonDolar = findViewById(R.id.btnDolar)
        buttonLliura = findViewById(R.id.btnLibra)
        buttonYen = findViewById(R.id.btnYen)
        buttonYuan = findViewById(R.id.btnYuan)
        pasiona = findViewById(R.id.pasiona)
    }

    private fun initListeners() {
        button0.setOnClickListener {
            addNumero(0)
        }
        button1.setOnClickListener {
            addNumero(1)
        }
        button2.setOnClickListener {
            addNumero(2)
        }
        button3.setOnClickListener {
            addNumero(3)
        }
        button4.setOnClickListener {
            addNumero(4)
        }
        button5.setOnClickListener {
            addNumero(5)
        }
        button6.setOnClickListener {
            addNumero(6)
        }
        button7.setOnClickListener {
            addNumero(7)
        }
        button8.setOnClickListener {
            addNumero(8)
        }
        button9.setOnClickListener {
            addNumero(9)
        }
        buttonCE.setOnClickListener {
            input.text = "0"
            output.text = "0"
        }
        buttonDel.setOnClickListener {
            if (input.length() != 1)input.text = input.text.substring(0, input.length()-1) else input.text = "0"
        }
        buttonComa.setOnClickListener {
             if (!checkComa()){
                 input.text = "${input.text},"
             }
        }
        buttonIgual.setOnClickListener {
            if (conversioActual == 1.0) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Alerta!")
                builder.setMessage("No has escollit cap moneda per fer la conversió")
                val dialog = builder.create()
                dialog.show()
            } else {
                val comaToPoint = input.text.toString().replace(',','.')
                var resultat = comaToPoint.toDouble()*conversioActual
                if (resultat % 1.0 != 0.0) {
                    output.text = truncate(resultat.toString(), 2)!!.replace('.',',')
                } else output.text = resultat.toInt().toString()
            }
        }
        buttonDolar.setOnClickListener {

            if (conversioDolar == 1.0) {
                val builder = AlertDialog.Builder(this)
                val editText = EditText(this)
                val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT)
                editText.layoutParams = lp
                editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                builder.setTitle("Introdueix el valor de conversió del dòlar")
                builder.setView(editText)
                builder.setPositiveButton("Aceptar") { position, which ->
                    
                        try {
                            conversioDolar = editText.text.toString().toDouble()
                            conversioActual = conversioDolar
                        } catch (e: Exception) {
                            Toast.makeText(this, "Input no vàlid", Toast.LENGTH_SHORT).show()
                        }

                }
                builder.setNegativeButton("Cancelar", null)
                val dialog = builder.create()
                dialog.show()
            } else {
                conversioActual = conversioDolar
            }

            buttonDolar.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark))
            buttonLliura.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
            buttonYen.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
            buttonYuan.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
        }
        buttonLliura.setOnClickListener {

            if (conversioLliura == 1.0) {
                val builder = AlertDialog.Builder(this)
                val editText = EditText(this)
                val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT)
                editText.layoutParams = lp
                editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                builder.setTitle("Introdueix el valor de conversió de la lliura")
                builder.setView(editText)
                builder.setPositiveButton("Aceptar") { position, which ->
                    try {
                        conversioLliura = editText.text.toString().toDouble()
                        conversioActual = conversioLliura
                    } catch (e: Exception) {
                        Toast.makeText(this, "Input no vàlid", Toast.LENGTH_SHORT).show()
                    }
                }
                builder.setNegativeButton("Cancelar", null)
                val dialog = builder.create()
                dialog.show()
            } else {
                conversioActual = conversioLliura
            }

            buttonDolar.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
            buttonLliura.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark))
            buttonYen.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
            buttonYuan.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
        }
        buttonYen.setOnClickListener {
            if (conversioYen == 1.0) {
                val builder = AlertDialog.Builder(this)
                val editText = EditText(this)
                val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT)
                editText.layoutParams = lp
                editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                builder.setTitle("Introdueix el valor de conversió del yen")
                builder.setView(editText)
                builder.setPositiveButton("Aceptar") { position, which ->
                    try {
                        conversioYen = editText.text.toString().toDouble()
                        conversioActual = conversioYen
                    } catch (e: Exception) {
                        Toast.makeText(this, "Input no vàlid", Toast.LENGTH_SHORT).show()
                    }
                }
                builder.setNegativeButton("Cancelar", null)
                val dialog = builder.create()
                dialog.show()
            } else {
                conversioActual = conversioYen
            }

            buttonDolar.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
            buttonLliura.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
            buttonYen.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark))
            buttonYuan.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
        }
        buttonYuan.setOnClickListener {
            if (conversioYuan == 1.0) {
                val builder = AlertDialog.Builder(this)
                val editText = EditText(this)
                val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT)
                editText.layoutParams = lp
                editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                builder.setTitle("Introdueix el valor de conversió del yuan")
                builder.setView(editText)
                builder.setPositiveButton("Aceptar") { position, which ->
                    try {
                        conversioYuan = editText.text.toString().toDouble()
                        conversioActual = conversioYuan
                    } catch (e: Exception) {
                        Toast.makeText(this, "Input no vàlid", Toast.LENGTH_SHORT).show()
                    }
                }
                builder.setNegativeButton("Cancelar", null)
                val dialog = builder.create()
                dialog.show()
            } else {
                conversioActual = conversioYuan
            }

            buttonDolar.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
            buttonLliura.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
            buttonYen.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
            buttonYuan.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark))
        }

        pasiona.setOnClickListener {
            Toast.makeText(this, "Benvingut a Pasiona! La millor empresa del mòn", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addNumero(numero: Int) {
        val posicio = input.text.toString().lastIndexOf(",")
        if (input.length() < 12) {
            if (posicio > -1) {
                if (input.text.substring(posicio, input.text.length).length < 3) {
                    input.text = if (input.text != "0")"${input.text}$numero" else numero.toString()
                }
            } else {
                input.text = if (input.text != "0")"${input.text}$numero" else numero.toString()
            }
        }
    }

    private fun truncate(value: String, places: Int): String? {
        return BigDecimal(value).setScale(places, RoundingMode.DOWN).stripTrailingZeros().toString()
    }

    private fun checkComa(): Boolean {
        return input.text.contains(",")
    }
}
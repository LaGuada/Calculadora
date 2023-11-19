package com.example.calculadora

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora.databinding.ActivityMainBinding
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding //declaramos la variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater);
        val view: View = binding.root
        setContentView(view)

        acciones()
    }

    private fun acciones() {

        binding.button0.setOnClickListener(this@MainActivity)
        binding.button1.setOnClickListener(this@MainActivity)
        binding.button2.setOnClickListener(this@MainActivity)
        binding.button3.setOnClickListener(this@MainActivity)
        binding.button4.setOnClickListener(this@MainActivity)
        binding.button5.setOnClickListener(this@MainActivity)
        binding.button6.setOnClickListener(this@MainActivity)
        binding.button7.setOnClickListener(this@MainActivity)
        binding.button8.setOnClickListener(this@MainActivity)
        binding.button9.setOnClickListener(this@MainActivity)

        binding.buttonDecimal.setOnClickListener(this@MainActivity)
        binding.buttonSumar.setOnClickListener(this@MainActivity)
        binding.buttonRestar.setOnClickListener(this@MainActivity)
        binding.buttonMultiplicar.setOnClickListener(this@MainActivity)
        binding.buttonDividir.setOnClickListener(this@MainActivity)
        binding.buttonPorcentaje.setOnClickListener(this@MainActivity)

        binding.buttonBorrar.setOnClickListener(this@MainActivity)
        binding.buttonAC.setOnClickListener(this@MainActivity)

        binding.buttonIgual.setOnClickListener(this@MainActivity)

        binding.buttonPotencia?.setOnClickListener(this@MainActivity)
        binding.buttonRaizCuadrada?.setOnClickListener(this@MainActivity)
        binding.buttonSeno?.setOnClickListener(this@MainActivity)
        binding.buttonCoseno?.setOnClickListener(this@MainActivity)
        binding.buttonTangente?.setOnClickListener(this@MainActivity)



    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.button0 -> binding.resultado.append("0")
            R.id.button1 -> binding.resultado.append("1");
            R.id.button2 -> binding.resultado.append("2");
            R.id.button3 -> binding.resultado.append("3");
            R.id.button4 -> binding.resultado.append("4");
            R.id.button5 -> binding.resultado.append("5");
            R.id.button6 -> binding.resultado.append("6");
            R.id.button7 -> binding.resultado.append("7");
            R.id.button8 -> binding.resultado.append("8");
            R.id.button9 -> binding.resultado.append("9");

            R.id.buttonSumar -> escribirOperador(binding.resultado.text.toString(), "+");
            R.id.buttonRestar -> escribirOperador(binding.resultado.text.toString(), "-");
            R.id.buttonMultiplicar -> escribirOperador(binding.resultado.text.toString(), "x");
            R.id.buttonDividir -> escribirOperador(binding.resultado.text.toString(), "/");
            R.id.buttonPorcentaje -> escribirOperador(binding.resultado.text.toString(), "%");

            R.id.buttonPotencia -> escribirOperador(binding.resultado.text.toString(), "^");
            R.id.buttonRaizCuadrada -> escribirOperador(binding.resultado.text.toString(), "V");
            R.id.buttonSeno -> escribirOperador(binding.resultado.text.toString(), "sen");
            R.id.buttonCoseno -> escribirOperador(binding.resultado.text.toString(), "cos");
            R.id.buttonTangente -> escribirOperador(binding.resultado.text.toString(), "tan");


            R.id.buttonDecimal -> binding.resultado.append(".");

            R.id.buttonAC -> {
                binding.resultado.setText("")
                binding.historial.setText("")
            }

            R.id.buttonBorrar -> {
                var texto = binding.resultado.text.toString()
                if (texto.isNotEmpty()) {
                    binding.resultado.setText(texto.substring(0, texto.length - 1))
                }
            }


            R.id.buttonIgual -> {
                var operacion: String = binding.resultado.text.toString();
                if (operacion.contains("+")) {
                    operar(operacion, "+")
                } else if (operacion.contains("-")) {
                    operar(operacion, "-")
                } else if (operacion.contains("x")) {
                    operar(operacion, "x")
                } else if (operacion.contains("/")) {
                    operar(operacion, "/")
                } else if (operacion.contains("%")) {
                    operar(operacion, "%")
                } else if (operacion.contains("V")) {
                    operar(operacion, "V")
                } else if (operacion.contains("sen")) {
                    operar(operacion, "sen")
                } else if (operacion.contains("cos")) {
                    operar(operacion, "cos")
                } else if (operacion.contains("^")) {
                    operar(operacion, "^")
                } else if (operacion.contains("tan")) {
                    operar(operacion, "tan")
                } else {
                    binding.historial.setText("operador no soportado")
                }
            }


        }
    }
        fun operar(operacion: String, operador: String) {
            if (binding.resultado.text != null) {
                var operador1 = 0.0;
                var operador2 = 0.0;
                var operacionDividida = operacion.split(operador);
                var operadoresNoValidos = false


                //Operaciones de un miembro
                if (operacionDividida[1] == "") {
                    operador1 = operacionDividida[0].toDouble();
                    when (operador) {
                        "%" -> binding.resultado.setText(formatoResultado(operador1 / 100))
                        "+" -> binding.resultado.setText(formatoResultado(operador1 + operador1))
                        else -> binding.resultado.setText("Operación no soportada")

                    }
                    }

                    else if (operacionDividida[0] == ""){
                        operador2=operacionDividida[1].toDouble()
                        when (operador){
                            "sen" -> binding.resultado.setText(formatoResultado(sin(operador2)))
                            "cos" -> binding.resultado.setText(formatoResultado(cos(operador2)))
                            "tan" -> binding.resultado.setText(formatoResultado(tan(operador2)))
                            "V" -> binding.resultado.setText(formatoResultado(sqrt(operador2)))
                            else -> binding.resultado.setText("Operación no soportada")


                        }

                }




                    //Operaciones de dos miembros
                    else if (operacionDividida[0] !== ""&& operacionDividida[1] != ""){

                        operador1 = operacionDividida[0].toDouble()
                        operador2 = operacionDividida[1].toDouble()

                        when (operador) {
                            "+" -> binding.resultado.setText(formatoResultado(operador1 + operador2))
                            "-" -> binding.resultado.setText(formatoResultado(operador1 - operador2));
                            "x" -> binding.resultado.setText(formatoResultado(operador1 * operador2));
                            "/" -> binding.resultado.setText(formatoResultado(operador1 / operador2))
                            "%" -> binding.resultado.setText(formatoResultado((operador2 / 100) * operador1))
                            "^" -> binding.resultado.setText(formatoResultado(operador1 .pow(operador2)))
                            "V" -> binding.resultado.setText(formatoResultado(operador2.pow(1.0/operador1)))

                        }

                    } else {
                        binding.resultado.setText("");
                    }

                    binding.historial.setText(operacion);


                }

            }

            fun formatoResultado(valor: Double): String {
                if (valor % 1 == 0.0) {
                    return valor.toInt().toString()
                } else {
                    return valor.toString()
                }
            }



    fun escribirOperador(operacion: String, nuevoOperador: String) {

        val arrayOperadores = arrayOf("+", "-",  "x", "/", "%", "^", "V", "sen", "cos", "tan");
        var operadorEnOperacion = "";
        var nuevaOperacion = "";

        for (operador in arrayOperadores) {
            if (operacion.contains(operador)) {
                operadorEnOperacion = operador;
            }
        }

        if(operadorEnOperacion == "") {
            binding.resultado.append(nuevoOperador);
        }

        else {
            nuevaOperacion = operacion.replace(operadorEnOperacion, nuevoOperador);
            binding.resultado.setText(nuevaOperacion)
        }
    }
}





package com.example.aluno.prova1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivity extends Activity {
    EditText visor;
    int operando1, operando2;
    String operador;
    String cache = "";
    Boolean apertouIgual = false;
    Boolean apertouM = false;
    String resultadoConta = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        visor = (EditText) findViewById(R.id.visor_id);
    }

    public void acao(View v) {
        Button tecla = (Button) v;

        //programar ação para tecla:
        // = -> fazer o calculo e exibir no visor
        // +,-,*,/ -> guardar o operador
        // númneros -> guardar o operando1 ou operando2
        switch (tecla.getText().toString()) {
            case "=":
                if (operador != null && visor.getText().length() > 0) {
                    operando2 = Integer.parseInt(visor.getText().toString());
                    switch (operador) {
                        case "/":
                            DecimalFormat formato = new DecimalFormat("0.00");
                            resultadoConta = formato.format(operando1*1.0 / operando2);
                            visor.setText(getString(R.string.resultado) + resultadoConta);
                            break;
                        case "*":
                            resultadoConta = String.valueOf(operando1 * operando2);
                            visor.setText(getString(R.string.resultado) + resultadoConta);
                            break;
                        case "-":
                            resultadoConta = String.valueOf(operando1 - operando2);
                            visor.setText(getString(R.string.resultado) + resultadoConta);
                            break;
                        case "+":
                            resultadoConta = String.valueOf(operando1 + operando2);
                            visor.setText(getString(R.string.resultado) + resultadoConta);
                            break;
                    }
                    cache = operando1 + " " + operador + " " + operando2 + " = " + resultadoConta;
                    apertouIgual=true;
                }
                break;
            case "/":
            case "*":
            case "-":
            case "+":
                if (visor.getText().length() > 0) {
                    operando1 = Integer.parseInt(visor.getText().toString());
                    operador = ((Button) v).getText().toString();
                    visor.setText("");
                }
                break;
            case "M":
                if(apertouIgual){
                    visor.setText(cache);
                    cache = "";
                    apertouIgual=false;
                }
                apertouM = true;
                break;
            case "C":
                visor.setText("");
                cache = "";
            default:
                if(visor.getText().toString().contains(getString(R.string.resultado))||apertouM){
                    visor.setText("");
                }

                visor.append(((Button) v).getText());
                break;
        }
    }
}

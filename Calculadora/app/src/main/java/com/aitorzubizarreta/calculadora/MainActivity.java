package com.aitorzubizarreta.calculadora;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    // Variables
    private Float numero1, numero2;
    private String operacion;
    private ArrayList<Button> numberButtons;

    // Objectos
    private Button btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine;
    private Button btnSuma, btnResta, btnMultiplicacion, btnDivision, btnResultado, btnPunto;
    private TextView screen;

    // Listeners

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getButtonsFromLayout();

        // Obtenemos los botones
        btnZero = (Button) findViewById(R.id.btnZero);
        btnOne = (Button) findViewById(R.id.btnOne);
        btnTwo = (Button) findViewById(R.id.btnTwo);
        btnThree = (Button) findViewById(R.id.btnThree);
        btnFour = (Button) findViewById(R.id.btnFour);
        btnFive = (Button) findViewById(R.id.btnFive);
        btnSix = (Button) findViewById(R.id.btnSix);
        btnSeven = (Button) findViewById(R.id.btnSeven);
        btnEight = (Button) findViewById(R.id.btnEight);
        btnNine = (Button) findViewById(R.id.btnNine);

        btnSuma = (Button) findViewById(R.id.btnSuma);
        btnResta = (Button) findViewById(R.id.btnResta);
        btnMultiplicacion = (Button) findViewById(R.id.btnMultiplicacion);
        btnDivision = (Button) findViewById(R.id.btnDivision);

        btnResultado = (Button) findViewById(R.id.btnResultado);
        btnPunto = (Button) findViewById(R.id.btnPunto);

        screen = (TextView) findViewById(R.id.screen);

        CalcLogic Calculadora = new CalcLogic();

        // Crearemos los listeners en un método aparte
        this.addEventListeners();
    }

    private void getButtonsFromLayout() {
        // Para recoger los botones de manera automatica
        String[] numbers = {"One", "Two"};

        String id;
        Button bnt;

        for (int i=0; i < numbers.length; i++) {
            id = "btn" .concat(numbers[i]);
            //btn = (Button) findViewById(getResources().getIdentifier(id, "id", ))
        }
    }

    View.OnClickListener numberClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Obtenemos el botón pulsado. Tendremos que cambiarlo de View a Button.
            Button valor = (Button) v;

            //Log.d("CHANGE", "HOLA " + botoncito.getText());

            // Miramos si tiene algo en la pantalla y lo guardamos en un array para no perderlo
            String numeroPantalla = (String) screen.getText();
            String numeroBoton = (String) valor.getText();

            if (numeroPantalla.equals("0"))  {
                screen.setText(numeroBoton);
            } else {
                screen.setText(numeroPantalla + numeroBoton);
            }
        }
    };

    View.OnClickListener btnOperacion = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // Obtenemos el botón pulsado. Tendremos que cambiarlo de View a Button.
            Button operacion = (Button) v;
            
        }
    };

    private void addEventListeners() {
        btnZero.setOnClickListener(numberClick);
        btnOne.setOnClickListener(numberClick);
        btnTwo.setOnClickListener(numberClick);
        btnThree.setOnClickListener(numberClick);
        btnFour.setOnClickListener(numberClick);
        btnFive.setOnClickListener(numberClick);
        btnSix.setOnClickListener(numberClick);
        btnSeven.setOnClickListener(numberClick);
        btnEight.setOnClickListener(numberClick);
        btnNine.setOnClickListener(numberClick);

        btnSuma.setOnClickListener(btnOperacion);
        btnResta.setOnClickListener(btnOperacion);
        btnMultiplicacion.setOnClickListener(btnOperacion);
        btnDivision.setOnClickListener(btnOperacion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

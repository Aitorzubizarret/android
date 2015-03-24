package com.aitorzubizarreta.intents;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class detail extends ActionBarActivity {

    // Variables que vamos a utilizar
    private Button btnClose;
    private Button btnSend;
    private EditText inputText;
    private TextView receivedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Se ha cargado la interfaz, recogemos los objetos
        btnClose = (Button) findViewById(R.id.btnClose);
        btnSend = (Button) findViewById(R.id.btnSendText);
        receivedText = (TextView) findViewById(R.id.inputText);
        inputText = (EditText) findViewById(R.id.lblReceivedText);

        // Añadimos los listeners
        addListeners();

        // Recibimos el intent de la actividad principal
        processIntent();
    }

    private void addListeners() {
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CHANGE", "Close window");
                setResult(RESULT_CANCELED);
                finish(); // Cierra la ventana
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = inputText.getText().toString();
                if(message.length() > 0) {
                    Intent data = new Intent();
                    data.putExtra(MainActivity.EXTRA_MESSAGE, message);
                    setResult(RESULT_OK, data);
                    finish();
                } else {
                    Toast toast = Toast.makeText(detail.this, "Escribe algo ", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    private void processIntent() {
        Intent receiveText = getIntent(); // Recibimos el intent
        String intentMessage = receiveText.getStringExtra(MainActivity.EXTRA_MESSAGE); // Recibimnos el mensaje o los datos del intent
        Log.d("CHANGE", intentMessage);
        receivedText.setText(intentMessage); // Mostramos el mensaje recibido en el label.
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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

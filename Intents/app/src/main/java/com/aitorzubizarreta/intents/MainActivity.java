package com.aitorzubizarreta.intents;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    // Variables que vamos a utilizar
    private Button btnSendText;
    private Button btnTakePhoto;
    private EditText inputText;
    private ImageView imageCamera;
    private static final int COD_MESSAGE = 0;
    private static final int COD_PHOTO = 1;
    public static final String EXTRA_MESSAGE = "A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Se ha cargado la interfaz, recogemos los objetos
        btnSendText = (Button) findViewById(R.id.btnSendText);
        btnTakePhoto = (Button) findViewById(R.id.btnTakePhoto);
        inputText = (EditText) findViewById(R.id.textInput);
        imageCamera = (ImageView) findViewById(R.id.imageViewPhoto);

        // Añadimos los listeners a los botones
        addListeners();
    }

    private void addListeners() {
        // Listener del botón para enviar el texto a la pantalla 2
        btnSendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CHANGE", inputText.getText().toString());
                // Comprobar si el usuario ha escrito algo antes de enviarlo
                String textoIntroducido = inputText.getText().toString();
                if (textoIntroducido.length() > 0) {
                    Intent sendMessage = new Intent(MainActivity.this, detail.class);
                    sendMessage.putExtra(EXTRA_MESSAGE, textoIntroducido);
                    startActivity(sendMessage);
                    startActivityForResult(sendMessage, COD_MESSAGE);
                } else {
                    // getResources().getString(R.)
                    Toast toast = Toast.makeText(MainActivity.this, "Escribe algo ", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
       // Listener del botón para poder sacar una foto
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CHANGE", "Listener photo");
                Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (takePhoto.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePhoto, COD_PHOTO);
                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "Comprate un móvil con cámara", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    // Recibimos la respuesta de los intent (activityForResult)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case COD_MESSAGE:
                    String message = data.getStringExtra(EXTRA_MESSAGE);
                    break;
                case COD_PHOTO:
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    imageCamera.setImageBitmap(imageBitmap);
            }
        }
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

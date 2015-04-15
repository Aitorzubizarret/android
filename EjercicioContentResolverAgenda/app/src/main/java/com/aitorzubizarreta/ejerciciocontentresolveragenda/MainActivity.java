package com.aitorzubizarreta.ejerciciocontentresolveragenda;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // Var
    private TextView dataHolder;
    private ContentResolver cr;
    private String data;
    private Uri uri;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUIElements();

        // Obtenemos el ContactResolver
        cr = getContentResolver();

        writeContacts();
        readContacts();
    }
    private void getUIElements() {
        // Get UI Elements
        dataHolder = (TextView)findViewById(R.id.contactos);
    }
    private void writeContacts() {
        try {
            /*
            // URI
            uri = ContactsContract.RawContacts.CONTENT_URI;

            ContentValues cv = new ContentValues();
            cv.put(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, "User Name");
            cv.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "666 777 888");
            cr.insert(uri, cv);
            */
            /*
            ContentResolver cr = this.getContentResolver();
            ContentValues cv = new ContentValues();
            cv.put(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, "New Name");
            cv.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "1234567890");
            cv.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
            cr.insert(ContactsContract.RawContacts.CONTENT_URI, cv);
            */
            ContentValues values = new ContentValues();
            values.put(ContactsContract.Data.RAW_CONTACT_ID, 001);
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "            1-800-GOOG-411      ");
            values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_CUSTOM);
            values.put(ContactsContract.CommonDataKinds.Phone.LABEL, "free directory assistance");
            Uri dataUri = getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);

            Toast.makeText(this, "Contact added", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.d("Console", "Error escribiendo un contacto nuevo");
        }


    }
    private void readContacts() {
        // URI
        uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        // Projection = Columnas
        String[] myProjection = new String[]{
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };
        // Selection
        // SelectionArgs
        // SortOrder

        // Creamos un cursor para movernos a través de los datos recibidos por la query
        // Parametros de query():
        // - Uri
        // - projection
        // - selection
        // - selectionArgs
        // - sortOrder
        cursor = cr.query(uri, myProjection, null, null, null);

        while (cursor.moveToNext()) {
            data = "Datos de contacto:\n";
            dataHolder.append(data);
            data = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            dataHolder.append(data);
            dataHolder.append("\n");
            data = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            dataHolder.append(data);
            dataHolder.append("\n\n");
        }
    }
}

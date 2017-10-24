package fr.wcs.checkpoint2sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new DatabaseHelper(MainActivity.this);

        Button send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gets the data repository in write mode
                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                EditText brand = (EditText) findViewById(R.id.brand);
                EditText name = (EditText) findViewById(R.id.name);
                EditText plate = (EditText) findViewById(R.id.plate);
                EditText km = (EditText) findViewById(R.id.km);
                values.put(DatabaseContract.CarEntry.COLUMN_NAME_BRAND, brand.getText().toString());
                values.put(DatabaseContract.CarEntry.COLUMN_NAME_NAME, name.getText().toString());
                values.put(DatabaseContract.CarEntry.COLUMN_NAME_PLATE, plate.getText().toString());
                values.put(DatabaseContract.CarEntry.COLUMN_NAME_KM, Integer.parseInt(km.getText().toString()));

                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(DatabaseContract.CarEntry.TABLE_NAME, null, values);
                Toast.makeText(MainActivity.this, String.valueOf(newRowId), Toast.LENGTH_LONG).show();
            }
        });

        Button see = (Button) findViewById(R.id.see);
        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = mDbHelper.getReadableDatabase();

                // Define a projection that specifies which columns from the database
                // you will actually use after this query.
                String[] projection = {
                        DatabaseContract.CarEntry.COLUMN_NAME_PLATE
                };

                Cursor cursor = db.query(
                        DatabaseContract.CarEntry.TABLE_NAME,                     // The table to query
                        projection,                               // The columns to return
                        null,                                // The columns for the WHERE clause
                        null,                            // The values for the WHERE clause
                        null,                                     // don't group the rows
                        null,                                     // don't filter by row groups
                        null                                 // The sort order
                );

                while(cursor.moveToNext()) {
                    String plate = cursor.getString(
                            cursor.getColumnIndexOrThrow(DatabaseContract.CarEntry.COLUMN_NAME_PLATE));
                    Toast.makeText(MainActivity.this, plate, Toast.LENGTH_SHORT).show();
                }
                cursor.close();

            }
        });
    }
}

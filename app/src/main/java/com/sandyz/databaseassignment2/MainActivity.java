package com.sandyz.databaseassignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.sandyz.databaseassignment2.Databse.Data_base;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    private Data_base data_base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         autoCompleteTextView= (AutoCompleteTextView)findViewById(R.id.autotext);

        data_base = new Data_base(MainActivity.this);
        data_base.openDb();

        data_base.insert_products("hp");
        data_base.insert_products("Lenovo");


        String[] products= data_base.getAll_products();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list, products);
        autoCompleteTextView.setAdapter(adapter);

    }

    public void onDestroy()
    {
        super.onDestroy();
        data_base.close();
    }

}

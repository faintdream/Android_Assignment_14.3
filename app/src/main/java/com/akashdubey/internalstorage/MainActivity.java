package com.akashdubey.internalstorage;

import android.Manifest;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    //declaring objects
    EditText message;
    TextView viewer;
    Button save, read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //binding objects to views
        message = (EditText) findViewById(R.id.messageET);
        viewer = (TextView) findViewById(R.id.readMessageTV);
        save = (Button) findViewById(R.id.saveBtn);
        read = (Button) findViewById(R.id.readBtn);


        //defining save action
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a file object with path in internal storage
                File file = new File(getApplicationContext().getFilesDir(), "KWEHRJFJSDFK.txt");

                //storing data to msg
                String msg = message.getText().toString();

                //FOS object declared
                FileOutputStream fileOutputStream = null;
                try {
                    //initialised FOS contructor with file path
                    fileOutputStream = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    //stored contents of msg to FOS
                    fileOutputStream.write(msg.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {

                    //when all good close the file
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(MainActivity.this, "Save called", Toast.LENGTH_SHORT).show();

            }
        });


        //defining method to read file
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //declaring FIS object
                FileInputStream fileInputStream = null;
                try {

                    // storing file path on internal storage
                    fileInputStream = new FileInputStream(getApplicationContext().getFilesDir() + "/KWEHRJFJSDFK.txt");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                int reader;
                StringBuffer stringBuffer = new StringBuffer();
                try {

                    //reading the FIS content  till it is neg- and storing it in reader integer
                    while ((reader = fileInputStream.read()) != -1) {

                        //using stgringbuffer to store reader content as character
                        stringBuffer.append((char) reader);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {

                    //storing the file after its work is done.
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //setting viewer to content of stringbuffer
                viewer.setText(stringBuffer.toString());
                Toast.makeText(MainActivity.this, "Read Operation", Toast.LENGTH_SHORT).show();

            }
        });


    }
}



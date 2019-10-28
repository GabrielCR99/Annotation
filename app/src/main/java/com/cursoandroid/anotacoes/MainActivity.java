package com.cursoandroid.anotacoes;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {
    private EditText editText;
    private ImageView button;
    private static final String NOTE = "note.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.save_button);
        editText = findViewById(R.id.texto_id);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String typedText = editText.getText().toString();
                saveFile(typedText);
            }
        });
        if (readFile() != null){
            editText.setText(readFile());
        }
    }

    private void saveFile(String text) {
        try {

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(NOTE, Context.MODE_PRIVATE));
            outputStreamWriter.write(text);
            outputStreamWriter.close();

        } catch (IOException e) {
            Log.v("MainActivity", e.toString());

        }
    }

    private String readFile(){
        String result = "";

        try {

            //Open file

            InputStream inputStream = openFileInput(NOTE);
            if (inputStream != null){

                //Read file
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                //Generate Buffer
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                //Retrieve text file
                //Enquanto existir texto
                String linhaArq = "";
                while ( (linhaArq = bufferedReader.readLine() )!= null) {
                    result += linhaArq;

                }

                inputStreamReader.close();
            }

        }catch (IOException e){
            Log.v("MainActivity", e.toString());
        }
        return result;
    }
}

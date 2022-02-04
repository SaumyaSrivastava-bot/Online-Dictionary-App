package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddWordActivity extends AppCompatActivity {
    EditText et_word;
    EditText et_meaning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        et_word = findViewById(R.id.et_word);
        et_meaning = findViewById(R.id.et_meaning);
    }

    public void add(View view) {
        if(et_word.getText().toString().isEmpty())
        {
            et_word.setError("Empty");
            et_word.requestFocus();
        }
        else
        {
            if(et_meaning.getText().toString().isEmpty())
            {

                et_meaning.setError("Empty");
                et_meaning.requestFocus();
            }
            else
            {
             //   Toast.makeText(this, "Business Logic for add word", Toast.LENGTH_SHORT).show();
               DbManager dm=new DbManager();
                Connection con=dm.getCon(this);
                String word=et_word.getText().toString().trim();
                String meaning=et_meaning.getText().toString().trim();
                String query="insert into dict(word,meaning) values('"+word+"','"+meaning+"')";
                try {
                    PreparedStatement ps=con.prepareStatement(query);
                    ps.executeUpdate();
                    Toast.makeText(this, "Word and Meaning are added.", Toast.LENGTH_SHORT).show();
                    et_word.setText("");
                    et_meaning.setText("");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        }
    }
    public void back(View view)
    {
        Intent I=new Intent(AddWordActivity.this,MainActivity.class);
        startActivity(I);
        finish();
    }
}
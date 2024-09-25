package com.fandevv.velhinha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editPlayer1;
    private EditText editPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editPlayer1 = findViewById(R.id.editPlayer1);
        editPlayer2 = findViewById(R.id.editPlayer2);
        Button btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                String player1 = editPlayer1.getText().toString().toUpperCase();
                String player2 = editPlayer2.getText().toString().toUpperCase();

                if (player1.isEmpty() || player2.isEmpty()){
                    Toast.makeText(MainActivity.this, "Por favor, preencha os nomes",Toast.LENGTH_SHORT).show();
                }
                else{

                    intent.putExtra("player1", player1);
                    intent.putExtra("player2", player2);

                    startActivity(intent);
                }
            }
        });
    }
}
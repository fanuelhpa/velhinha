package com.fandevv.velhinha;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fandevv.velhinha.entities.Piece;
import com.fandevv.velhinha.entities.Player;
import com.fandevv.velhinha.entities.Position;

import java.util.Arrays;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private Player player1;
    private Player player2;
    private Integer playerSelector;
    private TextView txtWhoStart;

    //Check if there is a winner
    //Checa se houve vencedor
    Integer check = 0;

    //Array de ImageButton e ids dos imagebuttons para instanciação do array
    private final ImageButton[][] buttons = new ImageButton[3][3];
    private final Integer[][] btn_id = {{R.id.btn0, R.id.btn1,R.id.btn2},
                                    {R.id.btn3, R.id.btn4,R.id.btn5},
                                    {R.id.btn6,R.id.btn7,R.id.btn8}};

    private final Integer[][] game = new Integer[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();

        //Instancia os jogadores se o bundle não estiver nulo
        if(bundle != null){

            player1 = new Player(1,bundle.getString("player1"));
            player2 = new Player(2,bundle.getString("player2"));

            Random random = new Random();

            //Seletor da peça que irá começar o jogo
            int pieceSelector;
            pieceSelector = random.nextInt(2);
            //Seletor do player que começará o jogo
            playerSelector = random.nextInt(2);

            //peça que começará o jogo
            Piece piece1 = new Piece("X");
            Piece piece2 = new Piece("O");

            //Label que informará quem irá começar o jogo e com qual peça
            txtWhoStart = findViewById(R.id.txtWhoStart);

            //Preenche o array de ImageButton com instancias baseadas nos ids dos imagebuttons
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++ ){
                    buttons[i][j] = findViewById(btn_id[i][j]);
                }
            }
            //Preenche o array de game com zeros para evitar valores nulos
            for (int i = 0; i < game.length; i++){
                Arrays.fill(game[i], 0);
            }

            //seleciona o jogador que irá começar o jogo e a peça que ele irá usar
            switch(playerSelector){
                case 0:
                    switch(pieceSelector){
                        case 0:
                            player1.setPiece(piece1);
                            player2.setPiece(piece2);
                            txtWhoStart.setText(String.format("%s começará o jogo com a peça %s", player1.getName(), player1.getPiece().getName()));
                            break;
                        case 1:
                            player1.setPiece(piece2);
                            player2.setPiece(piece1);
                            txtWhoStart.setText(String.format("%s começará o jogo com a peça %s", player1.getName(), player1.getPiece().getName()));
                            break;
                    }
                    break;
                case 1:
                    switch(pieceSelector){
                        case 0:
                            player2.setPiece(piece1);
                            player1.setPiece(piece2);
                            txtWhoStart.setText(String.format("%s começará o jogo com a peça %s", player2.getName(), player2.getPiece().getName()));
                            break;
                        case 1:
                            player2.setPiece(piece2);
                            player1.setPiece(piece1);
                            txtWhoStart.setText(String.format("%s começará o jogo com a peça %s", player2.getName(), player2.getPiece().getName()));
                            break;
                    }
                    break;
            }

            //Botão 1
            buttons[0][0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Position position = new Position(0,0);
                    executeButton(position);
                }
            });

            buttons[0][1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Position position = new Position(0,1);
                    executeButton(position);
                }
            });

            buttons[0][2].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Position position = new Position(0,2);
                    executeButton(position);
                }
            });

            buttons[1][0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Position position = new Position(1,0);
                    executeButton(position);
                }
            });

            buttons[1][1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Position position = new Position(1,1);
                    executeButton(position);
                }
            });

            buttons[1][2].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Position position = new Position(1,2);
                    executeButton(position);
                }
            });

            buttons[2][0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Position position = new Position(2,0);
                    executeButton(position);
                }
            });

            buttons[2][1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Position position = new Position(2,1);
                    executeButton(position);
                }
            });

            buttons[2][2].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Position position = new Position(2,2);
                    executeButton(position);
                }
            });
        }
    }

    //Verifica se existe um vencedor
    private int checkVelha(){

        int result = 0;
        if((game[0][0] * game[0][1] * game[0][2] == 1) || (game[1][0] * game[1][1] * game[1][2] == 1)||
                (game[2][0] * game[2][1] * game[2][2] == 1) || (game[0][0] * game[1][1] * game[2][2] == 1)
                || (game[0][2] * game[1][1] * game[2][0] == 1)|| (game[0][0] * game[1][0] * game[2][0] == 1)
                || (game[0][2] * game[1][2] * game[2][2] == 1)|| (game[0][1] * game[1][1] * game[2][1] == 1)) {

            result = 1;
            return result;
        }
        else if((game[0][0] * game[0][1] * game[0][2] == 8) || (game[1][0] * game[1][1] * game[1][2] == 8)||
                (game[2][0] * game[2][1] * game[2][2] == 8) || (game[0][0] * game[1][1] * game[2][2] == 8)
                || (game[0][2] * game[1][1] * game[2][0] == 8) || (game[0][0] * game[1][0] * game[2][0] == 8)
                || (game[0][2] * game[1][2] * game[2][2] == 8)|| (game[0][1] * game[1][1] * game[2][1] == 8)) {

            result = 8;
            return result;
        };

        return result;
    }

    private void executeButton(Position position){
        if(game[position.getRow()][position.getColumn()] == 0){
            if(playerSelector == 0){

                game[position.getRow()][position.getColumn()] = player1.getId();

                //Configura o ícone da peça que o jogador possui
                if(player1.getPiece().getName() == "X"){
                    buttons[position.getRow()][position.getColumn()].setImageResource(R.drawable.ic_btnx);
                }
                else{
                    buttons[position.getRow()][position.getColumn()].setImageResource(R.drawable.ic_btno);
                }

                //Retorna ou o valor 1 ou o valor 8, dependendo de quem fechou primeiro a trinca
                check = checkVelha();

                //Verifica se o player 1 venceu
                if(check == 1){
                    txtWhoStart.setText(String.format("%s venceu o jogo", player1.getName()));
                }
                else{
                    playerSelector = 1;
                    txtWhoStart.setText(String.format("%s, é a sua vez", player2.getName()));
                }
            }
            else{
                game[position.getRow()][position.getColumn()] = player2.getId();
                if(player2.getPiece().getName() == "X"){
                    buttons[position.getRow()][position.getColumn()].setImageResource(R.drawable.ic_btnx);
                }
                else{
                    buttons[position.getRow()][position.getColumn()].setImageResource(R.drawable.ic_btno);
                }

                check = checkVelha();

                if(check == 8){
                    txtWhoStart.setText(String.format("%s venceu o jogo", player2.getName()));
                }

                else{
                    playerSelector = 0;
                    txtWhoStart.setText(String.format("%s, é a sua vez", player1.getName()));
                }
            }
        }
        else{
            Toast.makeText(GameActivity.this, "Esta casa já foi marcada",Toast.LENGTH_SHORT).show();
        }
    }
}
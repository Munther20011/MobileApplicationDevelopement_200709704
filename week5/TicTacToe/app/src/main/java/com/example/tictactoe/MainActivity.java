package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.telephony.TelephonyCallback;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final String PLAYER_1= "x";
    static final String PLAYER_2= "o";
    boolean player1turn = true;
    byte [][] board=new byte[3][3];
    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        table=findViewById(R.id.board);
        for (int i = 0 ; i<3 ;i++){
            TableRow row =(TableRow) table.getChildAt(i);
            for(int j=0;j<3;j++){
                Button button =(Button)row.getChildAt(j);
                button.setOnClickListener(new Celllistener(i,j));
            }
        }
    }
    class Celllistener implements View.OnClickListener{
        int row,col;

        public Celllistener(int row, int col) {
            this.row = row;
            this.col = col;
        }
        public void onClick(View v){
            if (!isValidMove(row,col)){
                Toast.makeText(MainActivity.this,"Cell is already occupied",Toast.LENGTH_LONG).show();
            }
            if (player1turn){
                ((Button)v).setText(PLAYER_1);
                board[row][col]=1;
            }
            else {
                ((Button)v).setText(PLAYER_2);
                board[row][col]=2;
            }
            if(gameEnded(row,col)== -1){
                player1turn=!player1turn;
            } else if (gameEnded(row,col)==0) {
                Toast.makeText(MainActivity.this,"Its a draw",Toast.LENGTH_LONG).show();
            } else if (gameEnded(row,col)==1) {
                Toast.makeText(MainActivity.this,"Player1 won",Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(MainActivity.this,"Player2 won",Toast.LENGTH_LONG).show();
            }
        }
        public boolean isValidMove(int row,int col){
            return board[row][col]==0;
        }
        public int gameEnded(int row,int col){
            int symbol = board[row][col];
            boolean win = true;
            for(int i=0;i<3;i++){
                if (board[i][col]!=symbol){
                    win=false;
                    break;
                }
            }
            if (win){
                return symbol;
            }
            return -1;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean("Player1Turn",player1turn);
        byte[] boardSingle=new byte[9];
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                boardSingle[3*i+j]=board[i][j];
            }
        }
        outState.putByteArray("board",boardSingle);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        player1turn=savedInstanceState.getBoolean("Player1Turn");
        byte[]boardSingle= savedInstanceState.getByteArray("board");
        for (int i=0; i<9;i++){
            board[i/3][i%3]=boardSingle[i];

        }
        TableLayout table=findViewById(R.id.board);
        for (int i =0 ;i<3;i++){
            TableRow row = (TableRow) table.getChildAt(i);
            for (int j=0; j<3;j++){
                Button button=(Button) row.getChildAt(j);
                if (board[i][j]==1){
                    button.setText("x");
                } else if (board[i][j]==2) {
                    button.setText("o");
                }
                {

                }
            }
        }


    }
}
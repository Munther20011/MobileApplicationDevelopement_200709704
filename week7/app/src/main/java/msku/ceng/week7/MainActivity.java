package msku.ceng.week7;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements BoardView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void newGame() {

    }

    @Override
    public void putSymbol(char symbol, byte row, byte col) {

    }

    @Override
    public void gameEnded(byte winner) {

    }

    @Override
    public void invalidPlay(byte row, byte col) {

    }
}
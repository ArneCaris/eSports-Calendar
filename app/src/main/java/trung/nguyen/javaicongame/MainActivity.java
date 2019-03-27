package trung.nguyen.javaicongame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.ow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String game = "ow";
                Intent intent = new Intent(MainActivity.this, MatchActivity.class);
                intent.putExtra("game", game);
                startActivity(intent);
            }
        });

        findViewById(R.id.csgo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String game = "csgo";
                Intent intent = new Intent(MainActivity.this, MatchActivity.class);
                intent.putExtra("game", game);
                startActivity(intent);
            }
        });

        findViewById(R.id.dota2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String game = "dota2";
                Intent intent = new Intent(MainActivity.this, MatchActivity.class);
                intent.putExtra("game", game);
                startActivity(intent);
            }
        });

        findViewById(R.id.lol).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String game = "lol";
                Intent intent = new Intent(MainActivity.this, MatchActivity.class);
                intent.putExtra("game", game);
                startActivity(intent);
            }
        });

    }
}

package calendar.esports;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.Serializable;

public class MatchDetails extends AppCompatActivity {

    Match match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);

        Serializable matchDetail = getIntent().getSerializableExtra("MatchDetail");

        if (matchDetail instanceof  Match){
            match = (Match) matchDetail;
        }

        initMatchDetailRecylerView(match);
    }

    private void initMatchDetailRecylerView(Match matchDetail){
        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MatchDetailsAdapter adapter      = new MatchDetailsAdapter(this, matchDetail);
        recyclerView.setAdapter(adapter);
    }
}

package calendar.esports;

import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts.Data;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Serializable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MatchDetails extends AppCompatActivity {

    Team team1;
    Team team2;
    Match match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);

        Serializable matchDetail = getIntent().getSerializableExtra("MatchDetail");

        if (matchDetail instanceof  Match){
            match = (Match) matchDetail;
            Opponents[] opponents = match.getOpponents();
            Team team1 = opponents[0].getOpponent();
            Team team2 = opponents[1].getOpponent();

            fetchJson(team1.getId(), team2.getId());
        }

    }



    private void initMatchDetailRecylerView(Match matchDetail, Team team1, Team team2){
        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MatchDetailsAdapter adapter      = new MatchDetailsAdapter(this, matchDetail, team1, team2);
        recyclerView.setAdapter(adapter);
    }

    private void fetchJson(int team1Id, int team2Id){
        System.out.println("This is a team 2 id: " + team2Id);
        int team1ID = team1Id;
        int team2ID = team2Id;
        String url1  = "https://api.pandascore.co/teams/"  + team1ID +"?token=9BPCErZhuBjMWp1vTRopSF3XIbkoHVjJv9Ry1fAwf6mtyVU6564";
        String url2  = "https://api.pandascore.co/teams/"  + team2ID +"?token=9BPCErZhuBjMWp1vTRopSF3XIbkoHVjJv9Ry1fAwf6mtyVU6564";

        Request request1 = new Request.Builder().url(url1).build();
        Request request2 = new Request.Builder().url(url2).build();
        OkHttpClient client = new OkHttpClient();

        try {
            Response response1    = client.newCall(request1).execute();
            Response response2    = client.newCall(request2).execute();

            String   body1        = response1.body().string();
            String   body2        = response2.body().string();

            Gson     gson         = new GsonBuilder().create();
            Team team1            = gson.fromJson(body1, Team.class);
            Team team2            = gson.fromJson(body2, Team.class);

            System.out.print(team2);
            initMatchDetailRecylerView(match, team1, team2);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

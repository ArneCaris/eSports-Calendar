package calendar.esports;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.Serializable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MatchDetails extends AppCompatActivity {

    Team team1;
    Team team2;
    TextView team1Name;
    ImageView team1Image;
    TextView  team2Name;
    ImageView team2Image;

    Match match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);

        Serializable matchDetail = getIntent().getSerializableExtra("MatchDetail");
        team1Name  = findViewById(R.id.textView_team1_name);
        team2Name  = findViewById(R.id.textView_team2_name);
        team1Image = findViewById(R.id.imageView_team1_img);
        team2Image = findViewById(R.id.imageView_team2_img);

        if (matchDetail instanceof  Match) {
            match = (Match) matchDetail;
            Opponents[] opponents = match.getOpponents();
            if (opponents.length > 0) {

                Team team1 = opponents[0].getOpponent();
                team1Name.setText(team1.getName().toString());
                if(team1.getImage_url() != null) {
                    Picasso.get().load(team1.getImage_url().toString()).into(team1Image);
                }

                if(opponents.length == 2) {
                    Team team2 = opponents[1].getOpponent();
                    team2Name.setText(team2.getName().toString());
                    if(team2.getImage_url() != null) {
                        Picasso.get().load(team2.getImage_url().toString()).into(team2Image);
                    }
                    fetchJson(team1.getId(), team2.getId());
                }
            }
        }
    }



    private void initMatchDetailAdapterView(Team team1, Team team2){

        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
//        CustomLinearLayoutManager customLayoutManager = new CustomLinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MatchDetailsAdapter adapter      = new MatchDetailsAdapter(this, team1, team2); //, team2);
        recyclerView.setAdapter(adapter);
        recyclerView.stopScroll();

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
            initMatchDetailAdapterView(team1, team2);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

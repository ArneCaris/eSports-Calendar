package calendar.esports;

import android.os.StrictMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MatchActivity extends AppCompatActivity {

    private static final String TAG = "MatchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        String game = getIntent().getStringExtra("game").toString();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getGame(game);
        fetchJSON();
    }

    private void getGame(String game) {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        switch (game) {
            case "lol":
                getSupportActionBar().setIcon(R.drawable.lol);
                break;
            case "ow":
                getSupportActionBar().setIcon(R.drawable.ow_small);
                break;
            case "dota2":
                getSupportActionBar().setIcon(R.drawable.dota2_small);
                break;
            case "csgo":
                getSupportActionBar().setIcon(R.drawable.csgo_white);
                break;
            default:
                getSupportActionBar().setTitle("Matches");
        }
    }

    private void fetchJSON(){
        String game = getIntent().getStringExtra("game").toString();
        String url  = "https://api.pandascore.co/" + game + "/matches/upcoming?token=9BPCErZhuBjMWp1vTRopSF3XIbkoHVjJv9Ry1fAwf6mtyVU6564";
        Request request = new Request.Builder().url(url).build();
        OkHttpClient client = new OkHttpClient();

        try {
            Response response = client.newCall(request).execute();
            String body       =  response.body().string();
            Gson gson         = new GsonBuilder().create();
            Match[] json      = gson.fromJson(body, Match[].class);
            List<Match> listOfJson = Arrays.asList(json);
            Collections.sort(listOfJson);

            Map<Date, List<Match>> groupJson = listOfJson.stream()
                    .collect(Collectors.groupingBy(Match::getBegin_at));

            TreeMap<Date, List<Match>> sorted = new TreeMap<>();
            sorted.putAll(groupJson);

            groupJson.size();
            groupJson.values();

            Match[] matches   = listOfJson.toArray(json);
            initMatchRecyclerView(matches);
        }
        catch (IOException e) {
            Log.d(TAG, "fetchJSON: There is errors" + e);
            e.printStackTrace();
        }
    }

    private void initMatchRecyclerView(Match[] matches){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MatchAdapter adapter      = new MatchAdapter(this, matches);
        recyclerView.setAdapter(adapter);
    }

}

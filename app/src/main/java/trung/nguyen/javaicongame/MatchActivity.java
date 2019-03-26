package trung.nguyen.javaicongame;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MatchActivity extends AppCompatActivity {

    private static final String TAG = "MatchActivity";

    private ArrayList<String> match_titles = new ArrayList<>();
    private ArrayList<String> match_times  = new ArrayList<>();
    private ArrayList<Match>  matches      = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        Log.d(TAG, "onCreate: started.");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

//        initImageBitMaps();
        fetchJSON();
    }



//    private void initImageBitMaps(){
//        Log.d(TAG, "initImageBitMaps: preparing bitmaps");
//
//        match_titles.add("X vs X");
//        match_titles.add("Y vs Y");
//        match_titles.add("Z vs Z");
//
//        initMatchRecylerView();
//    }

    private void fetchJSON(){
        Log.d(TAG, "Fetch JSON: init Match RecyclerView");

        String game = getIntent().getStringExtra("game").toString();

        String url  = "https://api.pandascore.co/" + game + "/matches/upcoming?token=9BPCErZhuBjMWp1vTRopSF3XIbkoHVjJv9Ry1fAwf6mtyVU6564";
        Request request = new Request.Builder().url(url).build();
        OkHttpClient client = new OkHttpClient();

        try {
            Response response = client.newCall(request).execute();
            String body       =  response.body().string();
            Gson gson         = new GsonBuilder().create();
//            ArrayList<Match> matches  = gson.fromJson(body, new ArrayList<Match>().getClass());
            Match[] matches = gson.fromJson(body, Match[].class);
            initMatchRecylerView(matches);
        }
        catch (IOException e) {
            Log.d(TAG, "fetchJSON: There is errors" + e);
            e.printStackTrace();
        }
    }

    private void initMatchRecylerView(Match[] matches){
        Log.d(TAG, "initMatchRecylerView: init Match RecyclerView");
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MatchAdapter adapter      = new MatchAdapter(this, matches);
        recyclerView.setAdapter(adapter);
    }
}

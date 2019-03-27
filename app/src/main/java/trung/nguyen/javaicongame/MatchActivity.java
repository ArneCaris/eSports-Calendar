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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MatchActivity extends AppCompatActivity {

    private static final String TAG = "MatchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        fetchJSON();
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
            Match[] matches = gson.fromJson(body, Match[].class);
            initMatchRecylerView(matches);
        }
        catch (IOException e) {
            Log.d(TAG, "fetchJSON: There is errors" + e);
            e.printStackTrace();
        }
    }

    private void initMatchRecylerView(Match[] matches){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MatchAdapter adapter      = new MatchAdapter(this, matches);
        recyclerView.setAdapter(adapter);
    }
}

package trung.nguyen.icongame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_match.*
import okhttp3.*
import java.io.IOException

class MatchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        recyclerView.layoutManager = LinearLayoutManager(this);

        fetchJson()
    }

    fun fetchJson(){
        println("JSON FETCHED");

        val game = intent.getStringExtra("game").toString()

        val url = "https://api.pandascore.co/" + game + "/matches/upcoming?token=9BPCErZhuBjMWp1vTRopSF3XIbkoHVjJv9Ry1fAwf6mtyVU6564"
//        val url = "https://api.letsbuildthatapp.com/youtube/home_feed"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                println(body);

                val gson = GsonBuilder().create()
                val gameFeed = gson.fromJson(body, Array<Match>::class.java)

                runOnUiThread {
                    recyclerView.adapter = MatchApdater(gameFeed);
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }
}

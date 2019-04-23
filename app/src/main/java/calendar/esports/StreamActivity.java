package calendar.esports;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class StreamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);

        WebView webView = (WebView) findViewById(R.id.web_view);

        webView.post(new Runnable() {
            @Override
            public void run() {
                //int width = webView.getWidth();
                //int height = webView.getHeight();
                webView.loadUrl("https://twitch.tv/directory/game/Just%20Chatting/");
            }
        });
    }
}

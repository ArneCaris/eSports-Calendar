package trung.nguyen.icongame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.dota2).setOnClickListener(View.OnClickListener {
            openDota2MatchActivity();
        })

        findViewById<View>(R.id.csgo).setOnClickListener(View.OnClickListener {
            openCsGoMatchActivity();
        })

        findViewById<View>(R.id.lol).setOnClickListener(View.OnClickListener {
            openLolMatchActivity();
        })

        findViewById<View>(R.id.ow).setOnClickListener(View.OnClickListener {
            openOWMatchActivity();
        })
    }

    private fun openDota2MatchActivity() {
        val intent = Intent(this, MatchActivity::class.java)
        intent.putExtra("game", "dota2")
        startActivity(intent)
    }

    private fun openCsGoMatchActivity() {
        val intent = Intent(this, MatchActivity::class.java)
        intent.putExtra("game", "csgo")
        startActivity(intent)
    }

    private fun openOWMatchActivity() {
        val intent = Intent(this, MatchActivity::class.java)
        intent.putExtra("game", "ow")
        startActivity(intent)
    }

    fun openLolMatchActivity(){
        val intent = Intent(this, MatchActivity::class.java)
        intent.putExtra("game", "lol")
        startActivity(intent)
    }
}

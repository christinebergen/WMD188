package edu.itas.christinelab02;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("christine123", "App started Running");


        setContentView(R.layout.activity_main);


        final MediaPlayer spaceLaserMP = MediaPlayer.create(this, R.raw.space_laser);

        Button playSpaceLaser = (Button) this.findViewById(R.id.playSpaceLaser);

        playSpaceLaser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spaceLaserMP.start();
            }
        });


        TextView textview = findViewById(R.id.textView);
        String myString = "";

        for (int i=1; i<=100; i++) {

            if (isPrime(i)) {


                myString = myString + i + " ";
                Log.d("christine123", "added number " + i);

            }
        }
        textview.setText(myString);

    }

    static boolean isPrime(int n) {
        // Corner case
        if (n <= 1)
            return false;

        // Check from 2 to n-1
        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }

    public void changeText (View view ) {
        TextView textview = findViewById(R.id.textView);
        textview.setText("This is working");



    }

    public void showWeb (View view ) {
        WebView myWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());

        myWebView.loadUrl("https://www.slashdot.org");

    }
}
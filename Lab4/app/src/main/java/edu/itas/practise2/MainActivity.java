package edu.itas.practise2;

/** Android app to practice for ITAS188 quiz.
 * @author cberg
 * 2023-Feb-02
 *
 * */

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    /**
     * @cberg I used the code from:
     * https://www.tutorialspoint.com/how-to-turn-on-flash-light-programmatically-in-android
     * to make the flashlight work
     */

    private CameraManager mCameraManager;
    private String mCameraId;
    private ToggleButton toggleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this, //testing a toast!
                "I really like toast!", Toast.LENGTH_SHORT).show();


        boolean isFlashAvailable = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT);

        if (!isFlashAvailable) {
            showNoFlashError();
        }

        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            mCameraId = mCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        toggleButton = findViewById(R.id.flashlight);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchFlashLight(isChecked);
            }
        });
    }

    public void showNoFlashError() {
        AlertDialog alert = new AlertDialog.Builder(this)
                .create();
        alert.setTitle("Oops!");
        alert.setMessage("Flash not available in this device...");
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alert.show();
    }

    public void switchFlashLight(boolean status) {
        try {
            mCameraManager.setTorchMode(mCameraId, status);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }



 //This code is credit to @croftd, with a few minor adjustments.
    public void translate (View view) {

            HashMap<String, String> wordMap = new HashMap<>();

            wordMap.put("evil", "cat");
            wordMap.put("war", "party");
            wordMap.put("good", "dog");
            wordMap.put("Islam", "animals");
            wordMap.put("Muslim", "furry");

            String input = "We're taking action against evil people. Because this great nation of many religions understands, our war is not against Islam, or against faith practiced by the Muslim people. Our war is a war against evil. This is clearly a case of good versus evil, and make no mistake about it - good will prevail.";
            // to remove all commas and periods
            input = input.replaceAll("[,.]" , "");

            String[] words = input.split(" "); // split using space as the delimeter
            // This will be our translation of input
            StringBuffer output = new StringBuffer();

            // This while loop should go through the individual words (e.g. first word will be "this")
            for (String word : words) {
                //System.out.println(word);
                Log.d("itas123", "Next word is: " + word);

                // is the current word in the dictionary or map?
                if (wordMap.containsKey(word)) {
                    // get the other value
                    String swapWord = wordMap.get(word);
                    output.append(swapWord + " ");

                } else {
                    output.append(word +  " ");
                }
            }
            TextView textView = findViewById(R.id.textView);
            textView.setText(output.toString());

            Log.d("itas123", "The translation is: " + output);
        }
    @Override
    protected void onStart() {
        super.onStart();
        StringBuffer sb = new StringBuffer();
        sb.append("B");
        sb.append("u");
        sb.append("i");
        sb.append("l");
        sb.append("d");
        sb.append("e");
        sb.append("r");
        sb.append(".com");
        Log.d("itas123", sb.toString());
        // More toast because I like toast! And also, the only way I could get this text to display...
        Toast toast = Toast.makeText(getApplicationContext(), sb, Toast.LENGTH_SHORT);
        toast.show();
    }
}


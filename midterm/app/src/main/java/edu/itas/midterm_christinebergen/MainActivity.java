package edu.itas.midterm_christinebergen;

/**
 * ITAS 188 MidTerm
 * This app will:
 * -convert english to a version of internet slang called brospeak.
 * -replace the word 'friend' with a random word assigned
 * -start the sentence with another randomized word.
 * -both text-to-speech and speech-to-text functions will be utilized.
 * -sound on/sound off are not working :(
 *
 * @author cbergen
 * 2023-Feb-09
 */

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private HashMap<String, String> wordMap;
    private TextToSpeech toSpeech;
    private ImageView micButton;
    private TextView textView2;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**This section will add a text-to-speech function.
         * credit for this code to https://www.sitepoint.com/using-android-text-to-speech-to-create-a-smart-assistant/
         * */
        toSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = toSpeech.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("itas123", "This Language is not supported");
                    }
                    //test by saying hello
                    speak("Hello");
                } else {
                    Log.e("itas123", "text to speech failed to run!");
                }
            }
        });

        /**This section will add a speech-to-text function.
         * credit for this code to https://www.geeksforgeeks.org/how-to-convert-speech-to-text-in-android/
         *
         * */
        micButton = findViewById(R.id.micButton);
        textView2 = findViewById(R.id.textView2);

        micButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent
                        = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                        Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

                try {
                    startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
                } catch (Exception e) {
                    Toast
                            .makeText(MainActivity.this, " " + e.getMessage(),
                                    Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        Log.d("itas123", "Loaded MainActivity and xml view file");

        wordMap = new HashMap<>();

        // the dictionary of english words to translate to brospeak
        wordMap.put("brother", "bro");
        wordMap.put("Mike", "dude");
        wordMap.put("John", "dude");
        wordMap.put("guy", "dude");
        wordMap.put("girl", "chick");
        wordMap.put("working", "partying");
        wordMap.put("studying", "partying");
        wordMap.put("bought", "snagged");
        wordMap.put("worked", "partied");
        wordMap.put("relationship", "bromance");
        wordMap.put("alcohol", "booze");
        wordMap.put("pretty", "hot");
        wordMap.put("chill", "party");
        wordMap.put("female", "chick");
        wordMap.put("bicycle", "brocycle");
        wordMap.put("toast", "broast");
        wordMap.put("robot", "brobot");
        wordMap.put("anniversary", "brocasion");
        wordMap.put("procrastinator", "brocrastinator");
        wordMap.put("nostalgia", "brostalgia");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                textView2.setText(
                        Objects.requireNonNull(result).get(0));
            }
        }

    }
    private void speak(String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            toSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }


    public void translate(View view) {

        Log.d("itas123", "Running translate method...");

        EditText editText = (EditText) findViewById(R.id.editText);
        String input = editText.getText().toString();

        StringBuffer output = new StringBuffer();

        // we are using StringTokenizer to help handle punctuation such as , ! etc.
        StringTokenizer st = new StringTokenizer(input, " .,!?;()", true);

        //add a random word at the beginning of the sentence
        //@chatGPT helped with the formatting of this.
        String[] startWord = {" ", "Yo", "Sup", " Daam"};
        String start = startWord[(int) (Math.random() * startWord.length)];
        output.insert(0, start + " ");
        // go through all the words from the input
        while (st.hasMoreTokens()) {

            String word = st.nextToken();
            Log.d("itas123", "The next word is: " + word);
            //check if the word is friend and exchange for another random word
            //credit to @chatGPT for suggesting to use (word.equals("friend"))
            if (word.equals("friend")) {
                String[] changeFriend = {"pal ", "bro ", "dawg "};
                String newWord = changeFriend[(int) (Math.random() * changeFriend.length)];
                Log.d("itas123", "The next word is: " + newWord);
                output.append(newWord);
            } else if (wordMap.containsKey(word)) {
                String swapWord = wordMap.get(word);
                output.append(swapWord);
            } else {
                output.append(word);
            }
        }

        TextView textView = findViewById(R.id.textView2);

        String finalOutput = output.toString();

        Log.d("itas123", "Final output is: " + finalOutput);

        textView.setText(finalOutput);

        speak(finalOutput);
        Log.d("itas123", "Text to speech working!");
        //make toast to display output
        Toast toast = Toast.makeText(getApplicationContext(), finalOutput, Toast.LENGTH_SHORT);
        toast.show();
    }
    @Override
    public void onDestroy() {
        if (toSpeech != null) {
            toSpeech.stop();
            toSpeech.shutdown();
        }
        super.onDestroy();
    }
}






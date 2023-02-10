// Copyright 2016 Google Inc.
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//      http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.googleio;

import static android.app.PendingIntent.getActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity<current> extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_done);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("the cat is black", "onStart() method called");

        Toast.makeText(MainActivity.this,
                "Activity onStart() running!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("the cat is black", "onRestart() method called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("the cat is black", "onResume() method called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("the cat is black", "onPause() method called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("the cat is black", "onStop() method called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("the cat is black", "onDestroy() method called");
    }

    public void startSecond(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("petType", "dog");
        startActivity(intent);
    }


}

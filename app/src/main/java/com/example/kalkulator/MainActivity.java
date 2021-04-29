package com.example.kalkulator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private Button buttonSimple;
  private Button buttonAdv;
  private Button buttonAbout;
  private Button buttonExit;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    buttonSimple = findViewById(R.id.buttonSimpleId);
    buttonAdv = findViewById(R.id.buttonAdvId);
    buttonAbout = findViewById(R.id.buttonAboutId);
    buttonExit = findViewById(R.id.buttonExitId);

    buttonAbout.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(i);
          }
        });

    buttonSimple.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), SimpleCalculatorActivity.class);
            startActivity(i);
          }
        });

    buttonAdv.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), AdvancedCalculatorActivity.class);
            startActivity(i);
          }
        });
    buttonExit.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
          }
        });
  }
}

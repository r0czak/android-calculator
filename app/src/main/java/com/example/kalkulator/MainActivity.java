package com.example.kalkulator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private Button buttonSimpleId;
  private Button buttonAdvId;
  private Button buttonAboutId;
  private Button buttonExitId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    buttonAboutId.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        
      }
    });
  }
}

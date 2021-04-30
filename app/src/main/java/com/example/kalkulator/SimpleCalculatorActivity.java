package com.example.kalkulator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.kalkulator.exceptions.IncorrectValueException;
import com.example.kalkulator.utils.MathOperations;
import com.example.kalkulator.utils.MathParser;

public class SimpleCalculatorActivity extends AppCompatActivity {

  private Button buttonAC;
  private Button buttonC;
  private Button buttonNegate;
  private Button button7;
  private Button button8;
  private Button button9;
  private Button button4;
  private Button button5;
  private Button button6;
  private Button button1;
  private Button button2;
  private Button button3;
  private Button button0;
  private Button buttonDivision;
  private Button buttonMulti;
  private Button buttonMinus;
  private Button buttonPlus;
  private Button buttonEquals;
  private Button buttonDecimal;
  private Button buttonPercent;

  private TextView textViewInput;
  private TextView textViewOutput;

  private MathOperations lastOperation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_simple_calculator);

    initialiseControlls();
    initialiseButtonListeners();
  }

  private void initialiseControlls() {
    buttonAC = findViewById(R.id.buttonBskp);
    buttonC = findViewById(R.id.buttonC);
    buttonNegate = findViewById(R.id.buttonNegate);
    button7 = findViewById(R.id.button7);
    button8 = findViewById(R.id.button8);
    button9 = findViewById(R.id.button9);
    button4 = findViewById(R.id.button4);
    button5 = findViewById(R.id.button5);
    button6 = findViewById(R.id.button6);
    button1 = findViewById(R.id.button1);
    button2 = findViewById(R.id.button2);
    button3 = findViewById(R.id.button3);
    button0 = findViewById(R.id.button0);
    buttonDivision = findViewById(R.id.buttonDivision);
    buttonMulti = findViewById(R.id.buttonMulti);
    buttonMinus = findViewById(R.id.buttonMinus);
    buttonPlus = findViewById(R.id.buttonPlus);
    buttonEquals = findViewById(R.id.buttonEquals);
    buttonDecimal = findViewById(R.id.buttonDecimal);

    textViewInput = findViewById(R.id.calculatorDisplayInputId);
    textViewOutput = findViewById(R.id.calculatorDisplayOutputId);
  }

  private void initialiseButtonListeners() {
    addButtonListener(button0);
    addButtonListener(button1);
    addButtonListener(button2);
    addButtonListener(button3);
    addButtonListener(button4);
    addButtonListener(button5);
    addButtonListener(button6);
    addButtonListener(button7);
    addButtonListener(button8);
    addButtonListener(button9);

    buttonAC.setOnClickListener(
        v -> {
          setInput("");
          setOutput("");
        });

    buttonC.setOnClickListener(
        v -> {
          if (getInput().length() > 0) {
            setInput(getInput().substring(0, getInput().length() - 1));
          }
        });

    buttonNegate.setOnClickListener(
        v -> {
          if (getOutput().isEmpty()) {
            setOutput("0");
          }
          try {
            MathParser parser = new MathParser();
            double value = parser.calculate(getOutput(), getInput(), MathOperations.NEGATE);
            if (value % 1 == 0) {
              setInput(Integer.toString((int) value));
            } else {
              setInput(Double.toString(value));
            }
            lastOperation = MathOperations.NEGATE;
          } catch (IncorrectValueException e) {
            e.printStackTrace();
          }
        });
    buttonDivision.setOnClickListener(
        v -> {
          if (getOutput().isEmpty()) {
            setOutput(getInput());
            setInput("");
            lastOperation = MathOperations.DIVIDE;
          } else if (getInput().isEmpty()) {
            lastOperation = MathOperations.DIVIDE;
          } else {
            try {
              divideValues();
              lastOperation = MathOperations.DIVIDE;
            } catch (IncorrectValueException e) {
              e.printStackTrace();
            }
          }
        });
    buttonEquals.setOnClickListener(
        v -> {
          switch (lastOperation) {
            case NEGATE:
              setOutput(getInput());
              setInput("");
              lastOperation = MathOperations.EQUALS;
            case DIVIDE:
              try {
                divideValues();
                lastOperation = MathOperations.EQUALS;
              } catch (IncorrectValueException e) {
                e.printStackTrace();
              }
          }
        });
  }

  private void divideValues() {
    MathParser parser = new MathParser();
    double value = parser.calculate(getOutput(), getInput(), MathOperations.DIVIDE);
    if (value % 1 == 0) {
      setOutput(Integer.toString((int) value));
      setInput("");
    } else {
      setOutput(Double.toString(value));
      setInput("");
    }
  }

  private String getInput() {
    return (String) textViewInput.getText();
  }

  private String getOutput() {
    return (String) textViewOutput.getText();
  }

  private void setInput(String input_value) {
    textViewInput.setText(input_value);
  }

  private void setOutput(String output_value) {
    textViewOutput.setText(output_value);
  }

  private void addButtonListener(Button button) {
    button.setOnClickListener(
        v -> {
          //          if (getInput().charAt(0) == '0' && getInput().length() == 1) {
          //            setInput(button.getText().toString());
          //          } else {
          setInput(getInput() + button.getText().toString());
          // }
        });
  }
}

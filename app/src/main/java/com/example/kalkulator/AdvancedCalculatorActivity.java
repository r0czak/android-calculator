package com.example.kalkulator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.kalkulator.utils.MathOperations;
import com.example.kalkulator.utils.MathParser;

import java.math.BigDecimal;

public class AdvancedCalculatorActivity extends AppCompatActivity {

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
  private Button buttonSin;
  private Button buttonCos;
  private Button buttonLn;
  private Button buttonTan;
  private Button buttonSqrt;
  private Button buttonX2;
  private Button buttonXY;
  private Button buttonLog;

  private TextView textViewInput;
  private TextView textViewOutput;

  private MathOperations lastOperation = MathOperations.NOTHING;

  private MathParser mathParser = new MathParser();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_advanced_calculator);

    initialiseControlls();
    initialiseButtonListeners();
  }

  private void initialiseControlls() {
    buttonAC = findViewById(R.id.buttonBskp2);
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
    buttonPercent = findViewById(R.id.buttonPercent);
    buttonSin = findViewById(R.id.buttonSin);
    buttonCos = findViewById(R.id.buttonCos);
    buttonLn = findViewById(R.id.buttonLn);
    buttonTan = findViewById(R.id.buttonTan);
    buttonSqrt = findViewById(R.id.buttonSqrt);
    buttonX2 = findViewById(R.id.buttonX2);
    buttonXY = findViewById(R.id.buttonXY);
    buttonLog = findViewById(R.id.buttonLog);

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
          mathParser.clear();
        });

    buttonC.setOnClickListener(
        v -> {
          if (getInput().length() > 0) {
            setInput(getInput().substring(0, getInput().length() - 1));
          }
        });

    buttonNegate.setOnClickListener(
        v -> {
          String inputValue = getInput();
          if (inputValue.equals("0")) {
            setInput("0");
          } else if (inputValue.isEmpty()) {
            setInput("");
          } else if (inputValue.charAt(0) == '-') {
            setInput(inputValue.substring(1));
          } else {
            setInput("-" + inputValue);
          }
        });

    buttonDecimal.setOnClickListener(
        v -> {
          if (getInput().isEmpty()) {
            setInput("0.");
          } else if (!getInput().contains(".")) {
            setInput(getInput() + ".");
          }
        });

    buttonPlus.setOnClickListener(
        v -> {
          addNumberToParser();
          if (!getOutput().isEmpty() && lastOperation != MathOperations.EQUALS) {
            lastOperation = MathOperations.ADD;
          }
        });

    buttonMinus.setOnClickListener(
        v -> {
          if (getInput().isEmpty()) {
            setInput("-");
          }

          addNumberToParser();
          if (!getOutput().isEmpty() && lastOperation != MathOperations.EQUALS) {
            lastOperation = MathOperations.SUBTRACT;
          }
        });

    buttonMulti.setOnClickListener(
        v -> {
          addNumberToParser();
          if (!getOutput().isEmpty() && lastOperation != MathOperations.EQUALS) {
            lastOperation = MathOperations.MULITPLY;
          }
        });

    buttonDivision.setOnClickListener(
        v -> {
          addNumberToParser();
          if (!getOutput().isEmpty() && lastOperation != MathOperations.EQUALS) {
            lastOperation = MathOperations.DIVIDE;
          }
        });

    buttonEquals.setOnClickListener(
        v -> {
          addNumberToParser();
          lastOperation = MathOperations.EQUALS;
          mathParser.clear();
        });

    buttonPercent.setOnClickListener(
        v -> {
          addNumberToParser();
          if (!getOutput().isEmpty() && lastOperation != MathOperations.EQUALS) {
            lastOperation = MathOperations.PERCENT;
          }
        });

    buttonXY.setOnClickListener(
        v -> {
          addNumberToParser();
          if (!getOutput().isEmpty() && lastOperation != MathOperations.EQUALS) {
            lastOperation = MathOperations.EXPONENT;
          }
        });
  }

  void addNumberToParser() {
    String inputValue = getInput();
    if (!inputValue.isEmpty()) {
      repairInput();
      mathParser.addNumber(Double.parseDouble(inputValue));

      double value = mathParser.getOutput();
      String valueString = new BigDecimal(value).toPlainString();

      if (value % 1 == 0) {
        setOutput(value + "");
        // setOutput(String.format("%.0f", value));
      } else {
        setOutput(value + "");
        // setOutput(String.format("%.10f", value));
      }
      setInput("");
    }
  }

  void addOperationToParser() {
    if (lastOperation == MathOperations.EQUALS) {
      setOutput("");
      lastOperation = MathOperations.NOTHING;
    } else if (lastOperation != MathOperations.NOTHING) {
      mathParser.addOperation(lastOperation);

      double value = mathParser.getOutput();
      String valueString = new BigDecimal(value).toPlainString();

      if (value % 1 == 0) {
        setOutput(value + "");
        // setOutput(String.format("%.0f", value));
      } else {
        setOutput(value + "");
        // setOutput(String.format("%f", value));
      }

      lastOperation = MathOperations.NOTHING;
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
          if (button.getText().toString().equalsIgnoreCase("0") && getInput().equals("0")) {
            setInput(button.getText().toString());
          } else {
            if (getInput().equals("0")) {
              setInput(button.getText().toString());
            } else {
              setInput(getInput() + button.getText().toString());
            }
          }
          addOperationToParser();
          // }
        });
  }

  void repairInput() {
    if (getInput().charAt(getInput().length() - 1) == '.') {
      setInput(getInput().substring(0, getInput().length() - 2));
    }
  }
}

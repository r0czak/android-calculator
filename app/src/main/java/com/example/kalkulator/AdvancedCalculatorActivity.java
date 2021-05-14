package com.example.kalkulator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.kalkulator.utils.MathOperations;
import com.example.kalkulator.utils.MathParser;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

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

  DecimalFormat decimalFormat = new DecimalFormat("0", new DecimalFormatSymbols(Locale.US));

  @Override
  protected void onSaveInstanceState(Bundle savedInstanceState) {
    super.onSaveInstanceState(savedInstanceState);

    savedInstanceState.putString("output", getOutput());
    savedInstanceState.putString("input", getInput());
    savedInstanceState.putSerializable("parser", mathParser);
    savedInstanceState.putSerializable("last_operation", lastOperation);
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);

    if (savedInstanceState != null) {
      setOutput(savedInstanceState.getString("output"));
      setInput(savedInstanceState.getString("input"));
      mathParser = (MathParser) savedInstanceState.getSerializable("parser");
      lastOperation = (MathOperations) savedInstanceState.getSerializable("last_operation");
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_advanced_calculator);

    decimalFormat.setMaximumFractionDigits(10);
    // decimalFormat.setMaximumIntegerDigits(15);
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
          if (!getInput().isEmpty() || !getOutput().isEmpty()) {
            setInput("");
            setOutput("");
            mathParser.clear();
            lastOperation = MathOperations.NOTHING;
          }
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
          if (inputValue.isEmpty()) {
            setInput("");
          } else if (Double.parseDouble(inputValue) == 0) {
            setInput("0");
          } else if (inputValue.charAt(0) == '-' && inputValue.length() > 1) {
            setInput(inputValue.substring(1));
          } else {
            setInput("-" + inputValue);
          }
        });

    buttonDecimal.setOnClickListener(
        v -> {
          if (getInput().isEmpty()) {
            setInput("0.");
            addOperationToParser();
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
          } else {
            addNumberToParser();
            if (!getOutput().isEmpty() && lastOperation != MathOperations.EQUALS) {
              lastOperation = MathOperations.SUBTRACT;
            }
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

    buttonSin.setOnClickListener(
        v -> {
          if (!getInput().isEmpty()) {
            setInput(
                decimalFormat.format(
                    mathParser.calculateSingleNumberOperation(
                        Double.parseDouble(getInput()), MathOperations.SIN)));
          }
        });

    buttonCos.setOnClickListener(
        v -> {
          if (!getInput().isEmpty()) {
            setInput(
                Double.toString(
                    mathParser.calculateSingleNumberOperation(
                        Double.parseDouble(getInput()), MathOperations.COS)));
          }
        });

    buttonTan.setOnClickListener(
        v -> {
          if (!getInput().isEmpty()) {
            setInput(
                decimalFormat.format(
                    mathParser.calculateSingleNumberOperation(
                        Double.parseDouble(getInput()), MathOperations.TAN)));
          }
        });

    buttonSqrt.setOnClickListener(
        v -> {
          if (!getInput().isEmpty()) {
            if (Double.parseDouble(getInput()) >= 0) {
              setInput(
                  decimalFormat.format(
                      mathParser.calculateSingleNumberOperation(
                          Double.parseDouble(getInput()), MathOperations.SQRT)));
            } else {
              showToastMessage("Value must be equal or greater than 0");
            }
          }
        });

    buttonX2.setOnClickListener(
        v -> {
          if (!getInput().isEmpty()) {
            setInput(
                decimalFormat.format(
                    mathParser.calculateSingleNumberOperation(
                        Double.parseDouble(getInput()), MathOperations.SQUARED)));
          }
        });

    buttonLog.setOnClickListener(
        v -> {
          if (!getInput().isEmpty()) {
            if (Double.parseDouble(getInput()) <= 0) {
              showToastMessage("Value must be greater than 0");
            } else {
              setInput(
                  decimalFormat.format(
                      mathParser.calculateSingleNumberOperation(
                          Double.parseDouble(getInput()), MathOperations.LOG)));
            }
          }
        });

    buttonLn.setOnClickListener(
        v -> {
          if (!getInput().isEmpty()) {
            if (Double.parseDouble(getInput()) <= 0) {
              showToastMessage("Value must be greater than 0");
            } else {
              setInput(
                  decimalFormat.format(
                      mathParser.calculateSingleNumberOperation(
                          Double.parseDouble(getInput()), MathOperations.LN)));
            }
          }
        });
  }

  void addNumberToParser() {
    String inputValue = getInput();
    if (!inputValue.isEmpty()) {
      if (Double.parseDouble(inputValue) == 0
          && mathParser.getOperation() == MathOperations.DIVIDE) {
        mathParser.clear();
        setInput("");
        setOutput("");
        showToastMessage("Can't divide by 0");
      } else {
        repairInput();
        mathParser.addNumber(Double.parseDouble(inputValue));

        double value = mathParser.getOutput();

        setOutput(decimalFormat.format(value));
        setInput("");
      }
    }
  }

  void addOperationToParser() {
    if (lastOperation == MathOperations.EQUALS) {
      setOutput("");
      mathParser.clear();
      lastOperation = MathOperations.NOTHING;
    } else if (lastOperation != MathOperations.NOTHING) {
      mathParser.addOperation(lastOperation);

      double value = mathParser.getOutput();

      setOutput(decimalFormat.format(value));

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
        });
  }

  void repairInput() {
    String inputValue = getInput();
    if (inputValue.charAt(inputValue.length() - 1) == '.') {

      setInput(inputValue.substring(0, inputValue.length() - 2));
    } else if (inputValue.equals("-") || inputValue.equals("-0") || inputValue.equals("0.0")) {
      setInput("0");
    }
  }

  private void showToastMessage(final String msg) {
    runOnUiThread(() -> Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show());
  }
}

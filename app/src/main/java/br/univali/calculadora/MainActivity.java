package br.univali.calculadora;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private String input = "";
    private String operator = "";
    private double value1 = Double.NaN;
    private double value2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        setNumberButtonListeners();
        setOperatorButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3,
                R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7,
                R.id.btn_8, R.id.btn_9, R.id.btn_dot
        };

        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                input += button.getText().toString();
                editText.setText(input);
            }
        };

        for (int id : numberIds) {
            findViewById(id).setOnClickListener(numberListener);
        }
    }

    private void setOperatorButtonListeners() {
        int[] operatorIds = {
                R.id.btn_plus, R.id.btn_minus, R.id.btn_multiply, R.id.btn_divide, R.id.btn_equals, R.id.btn_clear, R.id.btn_backspace
        };

        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String text = button.getText().toString();

                switch (text) {
                    case "C":
                        input = "";
                        value1 = Double.NaN;
                        value2 = Double.NaN;
                        editText.setText("");
                        break;
                    case "⌫":
                        if (input.length() > 0) {
                            input = input.substring(0, input.length() - 1);
                            editText.setText(input);
                        }
                        break;
                    case "=":
                        calculate();
                        operator = "";
                        break;
                    default:
                        if (!Double.isNaN(value1)) {
                            calculate();
                        }
                        operator = text;
                        value1 = Double.parseDouble(input);
                        input = "";
                        break;
                }
            }
        };

        for (int id : operatorIds) {
            findViewById(id).setOnClickListener(operatorListener);
        }
    }

    private void calculate() {
        if (!Double.isNaN(value1)) {
            value2 = Double.parseDouble(input);
            switch (operator) {
                case "+":
                    value1 = value1 + value2;
                    break;
                case "-":
                    value1 = value1 - value2;
                    break;
                case "*":
                    value1 = value1 * value2;
                    break;
                case "/":
                    if (value2 != 0)
                        value1 = value1 / value2;
                    break;
            }
            editText.setText(String.valueOf(value1));
            input = String.valueOf(value1);
        }
    }
}
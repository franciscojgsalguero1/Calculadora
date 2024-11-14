package practica.pruebas.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etResult;
    private String currentInput = "";
    private String operator = "";
    private double firstValue = 0.0;
    private double secondValue = 0.0;
    private boolean isNewInput = true;
    private Double memoryValue = null;  // Variable para almacenar el valor en memoria

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etResult = findViewById(R.id.etResult);

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btn0 = findViewById(R.id.btn0);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSub = findViewById(R.id.btnSub);
        Button btnMul = findViewById(R.id.btnMul);
        Button btnDiv = findViewById(R.id.btnDiv);
        Button btnClear = findViewById(R.id.btnClear);
        Button btnEqual = findViewById(R.id.btnEqual);
        Button btnDecimal = findViewById(R.id.btnDecimal);
        Button btnMemorySave = findViewById(R.id.btnMemorySave);
        Button btnMemoryRecall = findViewById(R.id.btnMemoryRecall);
        Button btnMemoryClear = findViewById(R.id.btnMemoryClear);

        // Prevent adding more than one decimal point
        btnDecimal.setOnClickListener(v -> {
            if (!currentInput.contains(".")) {
                currentInput += ".";
                etResult.setText(currentInput);
            }
        });

        // Set up listeners for number buttons
        View.OnClickListener numberClickListener = v -> {
            if (isNewInput) {
                currentInput = "";
                isNewInput = false;
            }
            currentInput += ((Button) v).getText().toString();
            etResult.setText(currentInput);
        };

        btn1.setOnClickListener(numberClickListener);
        btn2.setOnClickListener(numberClickListener);
        btn3.setOnClickListener(numberClickListener);
        btn4.setOnClickListener(numberClickListener);
        btn5.setOnClickListener(numberClickListener);
        btn6.setOnClickListener(numberClickListener);
        btn7.setOnClickListener(numberClickListener);
        btn8.setOnClickListener(numberClickListener);
        btn9.setOnClickListener(numberClickListener);
        btn0.setOnClickListener(numberClickListener);

        // Set up listener for operators
        View.OnClickListener operatorClickListener = v -> {
            if (!currentInput.isEmpty()) {
                firstValue = Double.parseDouble(currentInput);
                currentInput = "";
                operator = ((Button) v).getText().toString();
            }
        };

        btnAdd.setOnClickListener(operatorClickListener);
        btnSub.setOnClickListener(operatorClickListener);
        btnMul.setOnClickListener(operatorClickListener);
        btnDiv.setOnClickListener(operatorClickListener);

        // Listener for Clear button
        btnClear.setOnClickListener(v -> {
            currentInput = "";
            operator = "";
            firstValue = 0.0;
            secondValue = 0.0;
            etResult.setText("0");
            isNewInput = true;
        });

        // Listener for Equal button
        btnEqual.setOnClickListener(v -> {
            if (!currentInput.isEmpty() && !operator.isEmpty()) {
                secondValue = Double.parseDouble(currentInput);
                double result = 0.0;
                switch (operator) {
                    case "+":
                        result = firstValue + secondValue;
                        break;
                    case "-":
                        result = firstValue - secondValue;
                        break;
                    case "*":
                        result = firstValue * secondValue;
                        break;
                    case "/":
                        if (secondValue != 0) {
                            result = firstValue / secondValue;
                        } else {
                            etResult.setText("Error");
                            return;
                        }
                        break;
                }
                etResult.setText(String.valueOf(result));
                currentInput = String.valueOf(result);
                operator = "";
                firstValue = result;
                isNewInput = true;
            }
        });

        // Listener for memory save (M+)
        btnMemorySave.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                memoryValue = Double.parseDouble(currentInput);
            }
        });

        // Listener for memory recall (MR)
        btnMemoryRecall.setOnClickListener(v -> {
            if (memoryValue != null) {
                currentInput = memoryValue.toString();
                etResult.setText(currentInput);
            }
        });

        // Listener for memory clear (MC)
        btnMemoryClear.setOnClickListener(v -> {
            memoryValue = null;
        });
    }
}

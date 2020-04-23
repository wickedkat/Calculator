package com.wickedkat.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    private Double operand1 = null;
    private Double operand2 = null;
    private String pendingOperation = "=";
    private List<Button> numberButtons = new ArrayList<>();
    private List<Button> opButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        newNumber = findViewById(R.id.newNumber);
        displayOperation = findViewById(R.id.operation);


        Button button0 = findViewById(R.id.button0);
        numberButtons.add(button0);
        Button button1 = findViewById(R.id.button1);
        numberButtons.add(button1);
        Button button2 = findViewById(R.id.button2);
        numberButtons.add(button2);
        Button button3 = findViewById(R.id.button3);
        numberButtons.add(button3);
        Button button4 = findViewById(R.id.button4);
        numberButtons.add(button4);
        Button button5 = findViewById(R.id.button5);
        numberButtons.add(button5);
        Button button6 = findViewById(R.id.button6);
        numberButtons.add(button6);
        Button button7 = findViewById(R.id.button7);
        numberButtons.add(button7);
        Button button8 = findViewById(R.id.button8);
        numberButtons.add(button8);
        Button button9 = findViewById(R.id.button9);
        numberButtons.add(button9);
        Button buttonDot = findViewById(R.id.buttonDot);
        numberButtons.add(buttonDot);

        Button buttonEquals = findViewById(R.id.buttonEquals);
        opButtons.add(buttonEquals);
        Button buttonPlus = findViewById(R.id.buttonPlus);
        opButtons.add(buttonPlus);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        opButtons.add(buttonMinus);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        opButtons.add(buttonMultiply);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        opButtons.add(buttonDivide);

        View.OnClickListener numListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                newNumber.append(b.getText().toString());

            }

        };
        for (Button button:numberButtons
             ) {button.setOnClickListener(numListener);

        }
        View.OnClickListener operationListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String oper = b.getText().toString();
                String value = newNumber.getText().toString();
                try{
                    Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue, oper);
                }
                catch (NumberFormatException e){
                    newNumber.setText("");
                };
                pendingOperation = oper;
                displayOperation.setText(pendingOperation);
            }
        };

        for (Button button:opButtons
             ) {button.setOnClickListener(operationListener);

        }



    }
    @SuppressLint("SetTextI18n")
    private void performOperation(Double value, String operation){
        if(operand1 ==null){
            operand1 = value;
        }
        else{
            operand2 = value;
            if (pendingOperation.equals("=")) {

                pendingOperation = operation;
            }
            switch (pendingOperation){
                case"=":
                    operand1 = operand2;
                    break;
                case"/":
                    if(operand2==0){
                        operand1 =0.0;
                    }
                    else{
                        operand1 /= operand2;
                    }
                    break;
                case "*":
                    operand1 *= operand2;
                    break;
                case "-":
                    operand1 -=operand2;
                    break;
                case "+":
                    operand1 +=operand2;
                    break;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
    }
}

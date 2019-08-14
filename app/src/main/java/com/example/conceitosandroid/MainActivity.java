package com.example.conceitosandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String displayValue = "0";
    Boolean clearDisplay = false;
    String operation;
    List<Double> values = new ArrayList<>();
    int current = 0;

    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.result);
    }

    public void handleClean(View view) {
        displayValue = "0";
        current = 0;
        values.add(0, 0.0);
        display.setText(displayValue);
    }

    public void handleAdd(View view) {
        String value = ((Button)view).getText().toString();

        if(value.equals(".") && displayValue.contains(".")){
            return;
        }

        clearDisplay = displayValue == "0" || clearDisplay;
        String currentValue = clearDisplay ? " " : displayValue;
        displayValue = currentValue + value;
        clearDisplay = false;

        display.setText(displayValue);

        if(!value.equals(".")) {
            double newValue = Double.parseDouble(displayValue);
            values.add(current, newValue);
        };
    };


    public void handleOperation(View view) {
        System.out.println("equals " + ((Button) view).getText().toString());

        if (current == 0) {
            current = 1;
            operation = ((Button) view).getText().toString();
            clearDisplay = true;
        }

        if (operation != null) {
            Boolean equals = operation.equals("=");
            double calc = 0.0;
            switch (operation) {
                case "-":
                    calc = (values.get(0) - values.get(1));
                    break;
                case "+":
                    calc = (values.get(0) + values.get(1));
                    break;
                case "*":
                    calc = (values.get(0) * values.get(1));
                    break;
                case "/":
                    calc = (values.get(0) / values.get(1));
                    break;
                default:
                    return;
            }

            operation = equals ? "" : operation;
            current = equals ? 0 : 1;
            clearDisplay = !equals;

//          if(operation.equals("="))
            display.setText(String.valueOf(calc));

            values.add(0, calc);
            values.add(1, 0.0);
            operation = ((Button) view).getText().toString();
            System.out.println("operation " + operation);
        }
    }

}

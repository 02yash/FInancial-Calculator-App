package com.example.finalapp;
//
//import android.os.Bundle;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }
//}
//package com.example.financialcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editPrincipal, editInterest, editTime;
    private TextView resultView;
    private Button calcFV, calcPV, calcEMI, calcCI, calcSI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        editPrincipal = findViewById(R.id.editPrincipal);
        editInterest = findViewById(R.id.editInterest);
        editTime = findViewById(R.id.editTime);
        resultView = findViewById(R.id.resultView);
        calcFV = findViewById(R.id.calcFV);
        calcPV = findViewById(R.id.calcPV);
        calcEMI = findViewById(R.id.calcEMI);
        calcCI = findViewById(R.id.calcCI);
        calcSI = findViewById(R.id.calcSI);

        // Set up button listeners
        calcFV.setOnClickListener(v -> calculateFutureValue());
        calcPV.setOnClickListener(v -> calculatePresentValue());
        calcEMI.setOnClickListener(v -> calculateEMI());
        calcCI.setOnClickListener(v -> calculateCompoundInterest());
        calcSI.setOnClickListener(v -> calculateSimpleInterest());
    }

    private void calculateFutureValue() {
        try {
            double principal = Double.parseDouble(editPrincipal.getText().toString());
            double rate = Double.parseDouble(editInterest.getText().toString()) / 100;
            double time = Double.parseDouble(editTime.getText().toString());
            double futureValue = principal * Math.pow(1 + rate, time);
            resultView.setText("Future Value: " + String.format("%.2f", futureValue));
        } catch (Exception e) {
            resultView.setText("Please enter valid values");
        }
    }

    private void calculatePresentValue() {
        try {
            double futureValue = Double.parseDouble(editPrincipal.getText().toString());
            double rate = Double.parseDouble(editInterest.getText().toString()) / 100;
            double time = Double.parseDouble(editTime.getText().toString());
            double presentValue = futureValue / Math.pow(1 + rate, time);
            resultView.setText("Present Value: " + String.format("%.2f", presentValue));
        } catch (Exception e) {
            resultView.setText("Please enter valid values");
        }
    }

    private void calculateEMI() {
        try {
            double principal = Double.parseDouble(editPrincipal.getText().toString());
            double annualRate = Double.parseDouble(editInterest.getText().toString()) / 100;
            double time = Double.parseDouble(editTime.getText().toString());
            double monthlyRate = annualRate / 12;
            double n = time * 12;
            double emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, n)) / (Math.pow(1 + monthlyRate, n) - 1);
            resultView.setText("EMI: " + String.format("%.2f", emi));
        } catch (Exception e) {
            resultView.setText("Please enter valid values");
        }
    }

    private void calculateCompoundInterest() {
        try {
            double principal = Double.parseDouble(editPrincipal.getText().toString());
            double rate = Double.parseDouble(editInterest.getText().toString()) / 100;
            double time = Double.parseDouble(editTime.getText().toString());
            double compoundInterest = principal * Math.pow(1 + rate, time) - principal;
            resultView.setText("Compound Interest: " + String.format("%.2f", compoundInterest));
        } catch (Exception e) {
            resultView.setText("Please enter valid values");
        }
    }

    private void calculateSimpleInterest() {
        try {
            double principal = Double.parseDouble(editPrincipal.getText().toString());
            double rate = Double.parseDouble(editInterest.getText().toString()) / 100;
            double time = Double.parseDouble(editTime.getText().toString());
            double simpleInterest = principal * rate * time;
            resultView.setText("Simple Interest: " + String.format("%.2f", simpleInterest));
        } catch (Exception e) {
            resultView.setText("Please enter valid values");
        }
    }
}

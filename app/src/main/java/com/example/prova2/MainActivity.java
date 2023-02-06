package com.example.prova2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.prova2.databinding.ActivityMainBinding;

import BatchOperation.BatchOperationsManager;
import BatchOperation.Scheduler;
import TestOperation.ClickButton;
import TestOperation.WriteOnScreen;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(onClick);
        binding.startButton.setOnClickListener(onClick2);
        Scheduler.start();
        System.out.println("Partito la schedule");

    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            System.out.println(binding.button.getId());
            System.out.println(binding.button.getText());
            System.out.println("Ti sto insegnando");
            binding.textView.setText("Ti sto insegnando");

        }
    };

    private View.OnClickListener onClick2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            ClickButton click1 = new ClickButton(binding);
            WriteOnScreen write1 = new WriteOnScreen("Primo", binding);
            WriteOnScreen write2 = new WriteOnScreen("Secondo", binding);
            WriteOnScreen write3 = new WriteOnScreen("Terzo", binding);
            ClickButton click2 = new ClickButton(binding);
            System.out.println("Creato gli oggetti");
            BatchOperationsManager.getInstance().addOperation(click1);
            BatchOperationsManager.getInstance().addOperation(write1);
            BatchOperationsManager.getInstance().addOperation(write2);
            BatchOperationsManager.getInstance().addOperation(write3);
            BatchOperationsManager.getInstance().addOperation(click2);
            System.out.println("Inseriti dentro li amici");


        }
    };


}
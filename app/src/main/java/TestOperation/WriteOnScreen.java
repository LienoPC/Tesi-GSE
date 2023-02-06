package TestOperation;


import com.example.prova2.databinding.ActivityMainBinding;

import BatchOperation.BatchOperation;

public class WriteOnScreen implements BatchOperation {

    private String text;
    private ActivityMainBinding binding;

    public WriteOnScreen(String text, ActivityMainBinding binding){
        this.text = text;
        this.binding = binding;
    }

    @Override
    public void execute() {
        binding.textView.setText(text);
    }
}

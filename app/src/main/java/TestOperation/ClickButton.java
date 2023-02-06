package TestOperation;

import com.example.prova2.databinding.ActivityMainBinding;

import BatchOperation.BatchOperation;

public class ClickButton implements BatchOperation {

    private ActivityMainBinding binding;

    public ClickButton(ActivityMainBinding binding){
        this.binding = binding;
    }

    @Override
    public void execute() {
        binding.button.callOnClick();
    }
}

package com.kdanmobile.androidpractice.screen.calculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kdanmobile.androidpractice.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalculatorFragment extends Fragment {
    @Bind(R.id.textView_calculatorScreen)
    TextView calculatorScreenTextView;

    private String TAG = getClass().getSimpleName();
    private Calculator calculator = new Calculator();

    public CalculatorFragment() {
        // Required empty public construct
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({
            R.id.button_num0,
            R.id.button_num1,
            R.id.button_num2,
            R.id.button_num3,
            R.id.button_num4,
            R.id.button_num5,
            R.id.button_num6,
            R.id.button_num7,
            R.id.button_num8,
            R.id.button_num9,
            R.id.button_add,
            R.id.button_sub,
            R.id.button_multiply,
            R.id.button_devide,
            R.id.button_equal,
            R.id.button_cancel})
    public void onClick(Button button) {
        calculator.press(button.getText().toString());
        calculatorScreenTextView.setText(calculator.getScreen());
    }
}

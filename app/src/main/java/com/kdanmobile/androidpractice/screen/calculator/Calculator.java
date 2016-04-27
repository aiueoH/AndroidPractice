package com.kdanmobile.androidpractice.screen.calculator;

import android.text.TextUtils;

import java.text.DecimalFormat;

public class Calculator {
    private String input = "0";
    private String lastInputKey = "";
    private String lastOperation = "";

    public String getScreen() {
        return findLastNumOrOperator(input);
    }

    public void press(String key) {
        if (isNum(key)) {
            if (input.equals("0") || lastInputKey.equals("=")) input = "";
            input += key;
        }
        if (isOperator(key)) {
            if (isEndWithOperator(input))
                input = input.substring(0, input.length() - 2);
            else if (containsOperator(input))
                compute();
            input += key;
        }
        if (key.equals("C")) input = "0";
        if (key.equals("=")) {
            if (lastInputKey.equals("=")) input += lastOperation;
            compute();
        }
        lastInputKey = key;
    }

    private void compute() {
        String nums[];
        double result = 0;
        if (input.contains("+")) {
            nums = input.split("\\+");
            result = Double.parseDouble(nums[0]) + Double.parseDouble(nums[1]);
            lastOperation = "+" + nums[1];
        }
        if (input.contains("-")) {
            nums = input.split("-");
            result = Double.parseDouble(nums[0]) - Double.parseDouble(nums[1]);
            lastOperation = "-" + nums[1];
        }
        if (input.contains("*")) {
            nums = input.split("\\*");
            result = Double.parseDouble(nums[0]) * Double.parseDouble(nums[1]);
            lastOperation = "*" + nums[1];
        }
        if (input.contains("/")) {
            nums = input.split("/");
            result = Double.parseDouble(nums[0]) / Double.parseDouble(nums[1]);
            lastOperation = "/" + nums[1];
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.####");
        input = String.valueOf(decimalFormat.format(result));
    }

    private boolean isNum(String key) {
        try {
            Integer.parseInt(key);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String findLastNumOrOperator(String s) {
        String lastChar = s.substring(s.length() - 1);
        if (isOperator(lastChar))
            return lastChar;
        String operator = "";
        if (s.contains("+")) operator = "\\+";
        if (s.contains("-")) operator = "-";
        if (s.contains("*")) operator = "\\*";
        if (s.contains("/")) operator = "/";
        if (!TextUtils.isEmpty(operator))
            return s.split(operator)[1];
        return s;
    }

    private boolean containsOperator(String string) {
        return string.contains("+") ||
                string.contains("-") ||
                string.contains("*") ||
                string.contains("/");
    }

    private boolean isEndWithOperator(String s) {
        String lastChar = s.substring(s.length() - 1);
        return isOperator(lastChar);
    }

    private boolean isOperator(String key) {
        return key.equals("+") || key.equals("-") || key.equals("*") || key.equals("/");
    }
}

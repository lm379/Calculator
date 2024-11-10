package cn.lm379.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_main);
        Button BT_0 = findViewById(R.id.BT_0);
        Button BT_1 = findViewById(R.id.BT_1);
        Button BT_2 = findViewById(R.id.BT_2);
        Button BT_3 = findViewById(R.id.BT_3);
        Button BT_4 = findViewById(R.id.BT_4);
        Button BT_5 = findViewById(R.id.BT_5);
        Button BT_6 = findViewById(R.id.BT_6);
        Button BT_7 = findViewById(R.id.BT_7);
        Button BT_8 = findViewById(R.id.BT_8);
        Button BT_9 = findViewById(R.id.BT_9);
        // 加法
        Button BT_ADD = findViewById(R.id.BT_ADD);
        // 乘法
        Button BT_MT = findViewById(R.id.BT_MT);
        // 减法
        Button BT_SUB = findViewById(R.id.BT_SUB);
        // 除法
        Button BT_DIV = findViewById(R.id.BT_DIV);
        // 退格
        Button BT_DEL = findViewById(R.id.BT_DEL);
        // 百分号
        Button BT_PERCENT = findViewById(R.id.BT_PERCENT);
        // 等号
        Button BT_EQUAL = findViewById(R.id.BT_EQUAL);
        // 清除
        Button BT_CLEAR = findViewById(R.id.BT_CLEAR);
        // 小数点
        Button BT_DOT = findViewById(R.id.BT_DOT);
        editText = findViewById(R.id.editText);
        myOnClickListener myOnClickListener = new myOnClickListener();
        BT_0.setOnClickListener(myOnClickListener);
        BT_1.setOnClickListener(myOnClickListener);
        BT_2.setOnClickListener(myOnClickListener);
        BT_3.setOnClickListener(myOnClickListener);
        BT_4.setOnClickListener(myOnClickListener);
        BT_5.setOnClickListener(myOnClickListener);
        BT_6.setOnClickListener(myOnClickListener);
        BT_7.setOnClickListener(myOnClickListener);
        BT_8.setOnClickListener(myOnClickListener);
        BT_9.setOnClickListener(myOnClickListener);
        BT_ADD.setOnClickListener(myOnClickListener);
        BT_MT.setOnClickListener(myOnClickListener);
        BT_SUB.setOnClickListener(myOnClickListener);
        BT_DIV.setOnClickListener(myOnClickListener);
        BT_DEL.setOnClickListener(myOnClickListener);
        BT_PERCENT.setOnClickListener(myOnClickListener);
        BT_EQUAL.setOnClickListener(myOnClickListener);
        BT_CLEAR.setOnClickListener(myOnClickListener);
        BT_DOT.setOnClickListener(myOnClickListener);
    }

    private class myOnClickListener implements View.OnClickListener {
        private String strOp1 = "0";  // 第一个操作数
        private String strOp2 = "";  // 第二个操作数
        private String operation = "";  // 当前使用的运算符
        private String strResult = "0";  // 当前显示的运算结果
        private int lastInputType = 0; // 0: 数字 1: 运算符

        private void numInput(int num) {
//            Log.i(TAG, "numInput: " + num);
            if (lastInputType == 1 || strResult.equals("0")) {
                strResult = "" + num;
            } else {
                strResult = strResult + num;
            }
//            Log.i(TAG, "strResult: " + strResult);
            editText.setText(strResult);
            lastInputType = 0;
        }

        private void opInput(String op) {
//            Log.i(TAG, "operation: " + op);
            // 处理连续输入运算符的情况
            if (lastInputType == 1) {
                operation = op;
                lastInputType = 1;
                return;
            }
            if (operation.isEmpty()) {
                operation = op;
                strOp1 = strResult;
//                Log.i(TAG, "strResult:" + strResult + "strOp1: " + strOp1);
            } else if (!strOp1.isEmpty()) {
                strOp2 = strResult;
//                Log.i(TAG, "strOp2: " + strOp2);
                // 处理加法运算
                if (operation.equals("+")){
                    double num1 = Double.parseDouble(strOp1);
                    double num2 = Double.parseDouble(strOp2);
                    strResult = String.valueOf(num1 + num2);
                    editText.setText(strResult);
                    strOp1 = strResult;
                    strOp2 = "";
                    operation = op;
                } else if (op.equals("-")){
                    double num1 = Double.parseDouble(strOp1);
                    double num2 = Double.parseDouble(strOp2);
                    strResult = String.valueOf(num1 - num2);
                    editText.setText(strResult);
                    strOp1 = strResult;
                    strOp2 = "";
                    operation = op;
                } else if (op.equals("*")){
                    double num1 = Double.parseDouble(strOp1);
                    double num2 = Double.parseDouble(strOp2);
                    strResult = String.valueOf(num1 * num2);
                    editText.setText(strResult);
                    editText.setText(strResult);
                    strOp1 = strResult;
                    strOp2 = "";
                    operation = op;
                } else if (op.equals("/")){
                    double num1 = Double.parseDouble(strOp1);
                    double num2 = Double.parseDouble(strOp2);
                    if (num2 == 0) {
                        strResult = "Error";
                        editText.setText(strResult);
                        strOp1 = "0";
                        strOp2 = "";
                        operation = "";
                        return;
                    }
                    strResult = String.valueOf(num1 / num2);
                    editText.setText(strResult);
                    strOp1 = strResult;
                    strOp2 = "";
                    operation = op;
                }
            }
            lastInputType = 1;
        }

        private void delInput() {
            if (strResult.length() > 1) {
                strResult = strResult.substring(0, strResult.length() - 1);
            } else {
                strResult = "0";
            }
            editText.setText(strResult);
        }

        // 清除输入
        private void clearInput() {
            strResult = "0";
            strOp1 = "0";
            strOp2 = "";
            operation = "";
            lastInputType = 0;
            editText.setText(strResult);
        }

        // 输入小数点
        private void dotInput() {
            if (!strResult.contains(".")) {
                strResult = strResult + ".";
                editText.setText(strResult);
            }
        }

        // 百分号运算
        private void percentInput() {
            double num = Double.parseDouble(strResult) / 100;
            strResult = String.valueOf(num);
            editText.setText(strResult);
        }

        // 等号运算
        private void equalInput() {
            if (operation.isEmpty()) {
                return;
            }
            if (lastInputType == 1 && !strOp1.isEmpty()) {
                double num1 = Double.parseDouble(strOp1);
                double num2 = Double.parseDouble(strOp2);
                switch (operation) {
                    case "+":
                        strResult = String.valueOf(num1 + num2);
                        break;
                    case "-":
                        strResult = String.valueOf(num1 - num2);
                        break;
                    case "*":
                        strResult = String.valueOf(num1 * num2);
                        break;
                    case "/":
                        if (num2 == 0) {
                            strResult = "Error";
                            editText.setText(strResult);
                            strOp1 = "0";
                            strOp2 = "";
                            operation = "";
                            return;
                        }
                        strResult = String.valueOf(num1 / num2);
                        break;
                }
                editText.setText(strResult);
            } else if (!strOp1.isEmpty() && lastInputType == 0) {
                strOp2 = strResult;
                double num1 = Double.parseDouble(strOp1);
                double num2 = Double.parseDouble(strOp2);
                switch (operation) {
                    case "+":
                        strResult = String.valueOf(num1 + num2);
                        break;
                    case "-":
                        strResult = String.valueOf(num1 - num2);
                        break;
                    case "*":
                        strResult = String.valueOf(num1 * num2);
                        break;
                    case "/":
                        if (num2 == 0) {
                            strResult = "Error";
                            editText.setText(strResult);
                            strOp1 = "0";
                            strOp2 = "";
                            operation = "";
                            return;
                        }
                        strResult = String.valueOf(num1 / num2);
                        break;
                }
                editText.setText(strResult);
                strOp1 = "";
                strOp2 = "";
                operation = "";
            }
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.BT_0) {
                numInput(0);
            } else if (v.getId() == R.id.BT_1) {
                numInput(1);
            } else if (v.getId() == R.id.BT_2) {
                numInput(2);
            } else if (v.getId() == R.id.BT_3) {
                numInput(3);
            } else if (v.getId() == R.id.BT_4) {
                numInput(4);
            } else if (v.getId() == R.id.BT_5) {
                numInput(5);
            } else if (v.getId() == R.id.BT_6) {
                numInput(6);
            } else if (v.getId() == R.id.BT_7) {
                numInput(7);
            } else if (v.getId() == R.id.BT_8) {
                numInput(8);
            } else if (v.getId() == R.id.BT_9) {
                numInput(9);
            } else if (v.getId() == R.id.BT_ADD) {
                opInput("+");
            } else if (v.getId() == R.id.BT_SUB) {
                opInput("-");
            } else if (v.getId() == R.id.BT_MT) {
                opInput("*");
            } else if (v.getId() == R.id.BT_DIV) {
                opInput("/");
            } else if (v.getId() == R.id.BT_DEL) {
                delInput();
            } else if (v.getId() == R.id.BT_PERCENT) {
                percentInput();
            } else if (v.getId() == R.id.BT_EQUAL) {
                equalInput();
            } else if (v.getId() == R.id.BT_CLEAR) {
                clearInput();
            } else if (v.getId() == R.id.BT_DOT) {
                dotInput();
            }
        }
    }
}

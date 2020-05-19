package com.example.task312;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    boolean add, sub, mul, div, percnt;
    float res1, res2;

    public static final int REQUEST_PIC = 1;
    public int res;
    Bitmap bmp;

    ImageView imageView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView screen = findViewById(R.id.textViewNumb);
        imageView = findViewById(R.id.imageView);

        Button n0 = findViewById(R.id.btn_zero);
        Button n1 = findViewById(R.id.btn_one);
        Button n2 = findViewById(R.id.btn_two);
        Button n3 = findViewById(R.id.btn_three);
        Button n4 = findViewById(R.id.btn_four);
        Button n5 = findViewById(R.id.btn_five);
        Button n6 = findViewById(R.id.btn_six);
        Button n7 = findViewById(R.id.btn_seven);
        Button n8 = findViewById(R.id.btn_eight);
        Button n9 = findViewById(R.id.btn_nine);
        Button dot = findViewById(R.id.btn_dot);
        Button equals = findViewById(R.id.btn_equally);
        Button addit = findViewById(R.id.btnPlus);
        Button minus = findViewById(R.id.btnMinus);
        Button multip = findViewById(R.id.btnMultip);
        Button division = findViewById(R.id.btn_division);
        Button plusMinus = findViewById(R.id.btn_plus_minus);
        final Button percent = findViewById(R.id.btn_persent);
        Button clear = findViewById(R.id.btn_reset);

        final View.OnClickListener calculatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                switch (id) {
                    case R.id.btn_zero:
                        screen.append("0");
                        break;
                    case R.id.btn_one:
                        screen.append("1");
                        break;
                    case R.id.btn_two:
                        screen.append("2");
                        break;
                    case R.id.btn_three:
                        screen.append("3");
                        break;
                    case R.id.btn_four:
                        screen.append("4");
                        break;
                    case R.id.btn_five:
                        screen.append("5");
                        break;
                    case R.id.btn_six:
                        screen.append("6");
                        break;
                    case R.id.btn_seven:
                        screen.append("7");
                        break;
                    case R.id.btn_eight:
                        screen.append("8");
                        break;
                    case R.id.btn_nine:
                        screen.append("9");
                        break;
                    case R.id.btn_dot:
                        screen.append(".");
                        break;
                    case R.id.btnPlus:
                        if (screen == null) {
                            res1 = Float.parseFloat(screen.getText() + "");
                            add = true;
                            screen.setText(null);
                        }
                        break;
                    case R.id.btnMinus:
                        if (screen == null) {
                            res1 = Float.parseFloat(screen.getText() + "");
                            sub = true;
                            screen.setText(null);
                        }
                        break;
                    case R.id.btn_equally:
                        res2 = Float.parseFloat(screen.getText() + "");
                        if (add == true) {
                            screen.setText(res1 + res2 + "");
                            add = false;
                        }
                        if (sub == true) {
                            screen.setText(res1 - res2 + "");
                            sub = false;
                        }
                        if (mul == true) {
                            screen.setText(res1 * res2 + "");
                            mul = false;
                        }
                        if (div == true) {
                            screen.setText(res1 / res2 + "");
                            div = false;
                        }
                        if (percnt == true) {
                            screen.setText((res1 * res2) / 100 + "");
                            percnt = false;
                        }
                        break;
                    case R.id.btnMultip:
                        if (screen == null) {
                            res1 = Float.parseFloat(screen.getText() + "");
                            mul = true;
                            screen.setText(null);
                        }
                        break;
                    case R.id.btn_division:
                        if (screen == null) {
                            res1 = Float.parseFloat(screen.getText() + "");
                            div = true;
                            screen.setText(null);
                        }
                        break;
                    case R.id.btn_plus_minus:
                        String displayElements = screen.getText().toString();
                        int length = displayElements.length();
                        if (length > 0) {
                            displayElements = displayElements.substring(0, length - 1);
                            screen.setText(displayElements);
                        }
                        break;
                    case R.id.btn_persent:
                        if (screen == null) {
                            res1 = Float.parseFloat(screen.getText() + "");
                            percnt = true;
                            screen.setText(null);
                        }
                        break;
                    case R.id.btn_reset:
                        screen.setText("");
                        break;

                }
            }
        };

        n0.setOnClickListener(calculatorListener);
        n1.setOnClickListener(calculatorListener);
        n2.setOnClickListener(calculatorListener);
        n3.setOnClickListener(calculatorListener);
        n4.setOnClickListener(calculatorListener);
        n5.setOnClickListener(calculatorListener);
        n6.setOnClickListener(calculatorListener);
        n7.setOnClickListener(calculatorListener);
        n8.setOnClickListener(calculatorListener);
        n9.setOnClickListener(calculatorListener);
        dot.setOnClickListener(calculatorListener);
        equals.setOnClickListener(calculatorListener);
        addit.setOnClickListener(calculatorListener);
        minus.setOnClickListener(calculatorListener);
        multip.setOnClickListener(calculatorListener);
        division.setOnClickListener(calculatorListener);
        plusMinus.setOnClickListener(calculatorListener);
        percent.setOnClickListener(calculatorListener);
        clear.setOnClickListener(calculatorListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivityForResult(intent, REQUEST_PIC);
            Toast.makeText(MainActivity.this, R.string.setActivity, Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PIC && requestCode == RESULT_OK && null != data) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap pic = extras.getParcelable("data");
                imageView.setImageBitmap(pic);
            }
        }
    }
}






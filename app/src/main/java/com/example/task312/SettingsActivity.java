package com.example.task312;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SettingsActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_PERMISSION_READ_STORAGE = 10;
    public static final int RESULT_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button btn = findViewById(R.id.butOk);

        final int permissionStatus = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (permissionStatus == PackageManager.PERMISSION_GRANTED) {

                    LoadImg();
                } else {
                    ActivityCompat.requestPermissions(SettingsActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_PERMISSION_READ_STORAGE);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_READ_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LoadImg();
                } else {
                }
                return;
        }
    }

    private void LoadImg() {

        ImageView viewSet = findViewById(R.id.imageViewSet);
        if (isExternalStorageWritable()) {
            File logFile = new File(getApplicationContext().getExternalFilesDir(null), "log.txt");
            try {
                FileWriter logWriter = new FileWriter(logFile);
                logWriter.append("App loaded");
                logWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            final EditText editText = findViewById(R.id.editTextSet);
            String strText = editText.getText().toString();
            int intPic = Integer.parseInt(strText);
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    String.valueOf(intPic) + ".jpg");
            Bitmap b = BitmapFactory.decodeFile(file.getAbsolutePath());
//            Bundle extras = new Bundle();
//            extras.putParcelable("image", b);
//            Intent intent = new Intent();
//            intent.putExtras(extras);
//            ByteArrayOutputStream bs = new ByteArrayOutputStream();
//            b.compress(Bitmap.CompressFormat.PNG, 50, bs);
            Intent i = new Intent(this, MainActivity.class);
            viewSet.setImageBitmap(b);
            startActivityForResult(i, RESULT_IMAGE);
            Toast.makeText(this, file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        }
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


}



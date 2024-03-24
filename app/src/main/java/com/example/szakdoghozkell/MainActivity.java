package com.example.szakdoghozkell;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

import android.graphics.*;
import android.widget.TextView;

import com.example.szakdoghozkell.ml.ModelUnquant;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import javax.xml.transform.Result;


public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    Button predictButton;
    ImageButton imageButton;

    TextView textView;

    String result;
    ImageButton captureButton;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getPermission();

        String[] labels = new String[1002];
        int cnt =0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("labels.txt")));
            String line =bufferedReader.readLine();
            while (line!=null){
                labels[cnt]=line;
                cnt++;
                line =bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageView =findViewById(R.id.imageView);
        imageButton = findViewById(R.id.imageButton);
        predictButton = findViewById(R.id.predictbutton);
        captureButton = findViewById(R.id.captureButton);
        textView = findViewById(R.id.textView);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,1);


            }
        });
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,3);

            }
        });
        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    ModelUnquant model = ModelUnquant.newInstance(MainActivity.this);

                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
                    Bitmap input=Bitmap.createScaledBitmap(bitmap,224,224,true);
                    TensorImage image=new TensorImage(DataType.FLOAT32);
                    image.load(input);
                    ByteBuffer byteBuffer=image.getBuffer();
                    inputFeature0.loadBuffer(byteBuffer);

                    ModelUnquant.Outputs outputs = model.process(inputFeature0);

                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    textView.setText(labels[getMax(outputFeature0.getFloatArray())]+" ");

                    model.close();
                } catch (IOException e) {
                    // TODO Handle the exception
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1){
            if(data!= null){
                Uri uri = data.getData();
                try{
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        } else if (requestCode ==3) {
            bitmap = (Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    int getMax(float[]floats){
        int max=0;
        for (int i = 0; i < floats.length; i++) {
            if (floats[i] > floats[max]){
                max=i;
            }
        }
        return max;
    }
    void getPermission(){
        if(checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},2);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode ==2){
            if(grantResults.length >0){
                if(grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                    this.getPermission();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
package com.example.DiakMelo;

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
import android.widget.Toast;


import com.example.DiakMelo.ml.ModelUnquant;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;


public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    Button predictButton;
    ImageButton imageButton;

    Button exitButton;

    Button editPasswordButton;


    Button textRecognitionButton;
    TextView textView;

    String result;
    ImageButton captureButton;
    Bitmap bitmap;

    TextView Email;

    TextRecognizer textRecognizer;
    DatabaseHelper databaseHelper;
    String resultid;
    String Studentid;
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
        textRecognitionButton = findViewById(R.id.textRecogniton);
        textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        databaseHelper= new DatabaseHelper(this);
        exitButton = findViewById(R.id.exitbutton);
        editPasswordButton = findViewById(R.id.editPasswordbutton);
        Email = findViewById(R.id.main_email);
        Intent intent = getIntent();
        Email.setText(intent.getStringExtra("email"));
        String email = Email.getText().toString();
        Studentid = databaseHelper.getStudentId(email);

        editPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditPasswordForUserActivity.class);
                startActivity(intent);
            }
        });


        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


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
                if (bitmap == null) {
                    Toast.makeText(MainActivity.this, "Kérlek adj meg egy képet", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        ModelUnquant model = ModelUnquant.newInstance(MainActivity.this);

                        TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
                        Bitmap input = Bitmap.createScaledBitmap(bitmap, 224, 224, true);
                        TensorImage image = new TensorImage(DataType.FLOAT32);
                        image.load(input);
                        ByteBuffer byteBuffer = image.getBuffer();
                        inputFeature0.loadBuffer(byteBuffer);

                        ModelUnquant.Outputs outputs = model.process(inputFeature0);

                        TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                        float[] confidences = outputFeature0.getFloatArray();
                        int maxpos = 0;
                        float maxconfidence = 0;
                        for (int i = 0; i < confidences.length; i++) {
                            if (confidences[i] > maxconfidence) {
                                maxconfidence = confidences[i];
                                maxpos = i;
                            }
                        }

                        textView.setText(labels[maxpos]);
                        result = (String) textView.getText();
                        model.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });
        textRecognitionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bitmap == null){
                    Toast.makeText(MainActivity.this,"Kérlek adj meg egy képet",Toast.LENGTH_SHORT).show();
                }
                else {
                    recognizeTextFromImage();
                }
            }
        });
    }

    private void recognizeTextFromImage() {

        InputImage inputImage = InputImage.fromBitmap(bitmap, 0);

        Task<Text> textResult = textRecognizer.process(inputImage).addOnSuccessListener(new OnSuccessListener<Text>() {
            @Override
            public void onSuccess(Text text) {
                String recognizedText = text.getText();
                String[] asd =recognizedText.split("\n");
                for (int i = 0; i < asd.length; i++) {
                    if (asd[i].matches(Studentid)){
                        resultid =asd[i];
                    }
                }
                if (result.equals("0 Diák")){
                    Toast.makeText(MainActivity.this, "Sikeres volt a Verifikáció.", Toast.LENGTH_SHORT).show();
                    databaseHelper.updatevalidated(resultid);
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Nem sikerült a verifikáció kérlek probáld meg újra egy jobb minőségű képpel", Toast.LENGTH_SHORT).show();
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

    public String replaceAccents(String results){
        if(results == null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < results.length(); i++) {
            sb.append(replaceAccent(results.charAt(i)));
        }
        return sb.toString().toUpperCase();
    }
    public char replaceAccent(char letter){
        switch (letter){
            case 'Á':
                return 'A';
            case 'É':
                return  'E';
            case 'Ó':
            case 'Ő':
            case 'Ö':
                return 'O';
            case 'Ú':
            case 'Ű':
            case 'Ü':
                return 'U';
            case 'Í':
                return 'I';
            default: return letter;

        }
    }
}
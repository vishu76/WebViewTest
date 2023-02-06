package com.example.webviewtest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Home_Activity extends AppCompatActivity {
    private String currentphotopath;
    ImageView imageView;
    private EditText editLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imageView = findViewById(R.id.imageView);
        editLink = findViewById(R.id.et_link);
        Uri uri = getIntent().getData();
         if (uri != null) {
//             List<String> parameters = uri.getPathSegments();
//             String param = parameters.get(parameters.size() - 1);
             String sendlink=uri.toString().trim();
             Intent intent = new Intent(Home_Activity.this, MainActivity.class);
             intent.putExtra("link",sendlink);
             startActivity(intent);
             editLink.setText(sendlink);
        }

    }

    public void captureImage() {
        String filenale = "photo";
        File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {
            File imagefile = File.createTempFile(filenale, ".jpg", storageDirectory);
            Uri imageuri = FileProvider.getUriForFile(Home_Activity.this, "com.example.webviewtest.fileprovider", imagefile);
            currentphotopath = imagefile.getAbsolutePath();
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
            startActivityForResult(intent, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap bitmap = BitmapFactory.decodeFile(currentphotopath);
            imageView.setImageBitmap(bitmap);
//            rotateImage(bitmap);


        }
    }

    private void rotateImage(Bitmap bitmap) {
        ExifInterface exifInterface = null;
        try {
            exifInterface = new ExifInterface(currentphotopath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(190);
            default:
        }
        Bitmap rotatebitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        imageView.setImageBitmap(rotatebitmap);

    }

    public void takepictureImagebtn(View view) {
        captureImage();
    }


    public void redirectionn(View view) {
       /* Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.youtube.com/watch?v=bzSTpdcs-EI"));
        intent.setPackage("com.google.android.youtube");
        startActivity(intent);*/
//        https://www.linkedin.com/in/viswajeet-bharti-175912160/
//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://stackoverflow.com/questions/41378568/how-to-open-a-youtube-video-link-directly-from-android-app")));
//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/vishwjeetbharti44")));
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/viswajeet-bharti-175912160/")));

    }

    public void googlebtn(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.in/")));
    }

    public void facebookbtn(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/")));

    }

    public void redirectpage(View view) {
        if (editLink.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Paste Your URL", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(Home_Activity.this, MainActivity.class);
            intent.putExtra("link", editLink.getText().toString().trim());
            startActivity(intent);
            editLink.setText("");
        }

    }
}
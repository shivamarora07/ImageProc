package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    Bitmap srcBitmap = null;
    Bitmap dstBitmap = null;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnAGCIE_click(View view){
        AGCIE(srcBitmap,dstBitmap);
        View nImg = findViewById(R.id.imageViewOutput);
        ((ImageView)nImg).setImageBitmap(dstBitmap);
    }

    public void btnBIMEF_click(View view){
        BIMEF(srcBitmap,dstBitmap);
        View nImg = findViewById(R.id.imageViewOutput);
        ((ImageView)nImg).setImageBitmap(dstBitmap);
    }

    public void btnAGCWD_click(View view){
        AGCWD(srcBitmap,dstBitmap);
        View nImg = findViewById(R.id.imageViewOutput);
        ((ImageView)nImg).setImageBitmap(dstBitmap);
    }

    public void btnAGCIEDSUS_click(View view){
        AGCIEDSUS(srcBitmap,dstBitmap);
        View nImg = findViewById(R.id.imageViewOutput);
        ((ImageView)nImg).setImageBitmap(dstBitmap);
    }

    public void btnBIMEFDSUS_click(View view){
        BIMEFDSUS(srcBitmap,dstBitmap);
        View nImg = findViewById(R.id.imageViewOutput);
        ((ImageView)nImg).setImageBitmap(dstBitmap);
    }

    public void btnAGCWDDSUS_click(View view){
        AGCWDDSUS(srcBitmap,dstBitmap);
        View nImg = findViewById(R.id.imageViewOutput);
        ((ImageView)nImg).setImageBitmap(dstBitmap);
    }


    public void btnLoad_click(View view){
        openGallery();
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Pick an image"),RESULT_LOAD_IMAGE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_OK && requestCode == RESULT_LOAD_IMAGE){
            ImageView imageView = findViewById(R.id.imgView);
            try{
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                srcBitmap = BitmapFactory.decodeStream(inputStream);
                dstBitmap = srcBitmap.copy(srcBitmap.getConfig(), true);
                imageView.setImageBitmap(srcBitmap);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }

    public native String stringFromJNI();
    public native void myFlip(Bitmap bitmapIn,Bitmap bitmapOut);
    public native void AGCIE(Bitmap bitmapIn,Bitmap bitmapOut);
    public native void BIMEF(Bitmap bitmapIn,Bitmap bitmapOut);
    public native void AGCWD(Bitmap bitmapIn,Bitmap bitmapOut);
    public native void AGCIEDSUS(Bitmap bitmapIn,Bitmap bitmapOut);
    public native void AGCWDDSUS(Bitmap bitmapIn,Bitmap bitmapOut);
    public native void BIMEFDSUS(Bitmap bitmapIn,Bitmap bitmapOut);


    //public native fun myBlur(Bitmap bitmapIn,Bitmap bitmapOut, Float sigma);
    //public native fun myFlip(Bitmap bitmapIn,Bitmap bitmapOut);
}
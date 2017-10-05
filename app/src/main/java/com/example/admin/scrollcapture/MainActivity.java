package com.example.admin.scrollcapture;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "data_session_log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener event = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap i = takeScreenshot();
                saveBitmap(i);
            }
        };
        findViewById(R.id.btn).setOnClickListener(event);
        findViewById(R.id.btn2).setOnClickListener(event);
        findViewById(R.id.btn3).setOnClickListener(event);
        findViewById(R.id.btn1).setOnClickListener(event);

    }

    public Bitmap testScreen(View v) {

//        View screenView = view.getRootView();
//        screenView.setDrawingCacheEnabled(true);
//        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
//        screenView.setDrawingCacheEnabled(false);
//        return bitmap;

        Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Log.d(TAG, "view width: " + v.getWidth() + ", " + v.getHeight());
        Canvas c = new Canvas(b);
//        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return b;
    }

    public Bitmap takeScreenshot() {
        try {
            View rootView = getWindow().getDecorView().findViewById(R.id.child);
//            testScreen(rootView);
//            View childView = getWindow().getDecorView().findViewById(R.id.child);
//            Log.d(TAG, rootView.getMeasuredWidth() + ", " + rootView.getMeasuredHeight());
//            rootView.setDrawingCacheEnabled(true);
//
//            rootView.layout(0, 0, rootView.getWidth(), rootView.getHeight());
//
//            rootView.buildDrawingCache(true);
//            Bitmap b = Bitmap.createBitmap(rootView.getDrawingCache());
//            rootView.setDrawingCacheEnabled(false); // clear drawing cache
//            Log.d(TAG, "bitmap size: " + b.getWidth() + ", " + b.getHeight());
            return testScreen(rootView);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return null;
    }

    public void saveBitmap(Bitmap bitmap) {
        String root = Environment.getExternalStorageDirectory().toString();
        File newDir = new File(root + "/Folder");
        newDir.mkdir();
        Random gen = new Random();
        int n = 10000;
        n = gen.nextInt(n);
        String fotoname = "Photo-" + 5 + ".jpg";
        File file = new File(newDir, fotoname);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            Toast.makeText(getApplicationContext(),
                    "Saved in folder: 'Folder'", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Log.e(TAG, e.getMessage() + e);
        }
    }
}

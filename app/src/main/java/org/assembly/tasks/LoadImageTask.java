package org.assembly.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class LoadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;

    public LoadImageTask(ImageView imageView) {
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String... url) {
        Bitmap image = null;
        try {
            InputStream in = new URL(url[0]).openConnection().getInputStream();
            image = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    protected void onPostExecute(Bitmap result) {
        if (result != null)
            imageView.setImageBitmap(result);
    }
}

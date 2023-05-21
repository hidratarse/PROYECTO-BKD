package com.example.proyecto_bkd.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GallerySaver {
    public static Uri saveImageToGallery(Bitmap bitmap) {
        OutputStream fos = null;
        Uri imageUri = null;
        int nuevaAnchura = 456;
        int nuevaAltura = 494;
        try {
            File imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            String imageFileName = "IMG_" + timeStamp + ".jpg";

            File imageFile = new File(imagesDir, imageFileName);

            fos = new FileOutputStream(imageFile);
            Bitmap imagenRedimensionada = Bitmap.createScaledBitmap(bitmap, nuevaAnchura, nuevaAltura, true);
            imagenRedimensionada.compress(Bitmap.CompressFormat.JPEG, 100, fos);

            imageUri = Uri.fromFile(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imageUri;
    }

}

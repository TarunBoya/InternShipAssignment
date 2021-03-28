package com.tarun.internassignment.Retrofits;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class Converter {

    public static byte[] BitMapToByte(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }


    @NonNull
    public static Bitmap ByteToBitMap(byte[] array) {
        return BitmapFactory.decodeByteArray(array, 0, array.length);
    }

}

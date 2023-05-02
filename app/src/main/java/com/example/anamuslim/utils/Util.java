package com.example.anamuslim.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Util {


    @NonNull
    public static File fileLocation(String folderName) {
        String fname = "images.zip";

        //  root = context.getExternalFilesDir("");
        //   root = Environment.getExternalStoragePublicDirectory("");
        File root = Environment.getExternalStoragePublicDirectory("");

        Log.d("Ahmed20", "Lenght of file2: " + root);

        // File dir = new File(root + "/hisnmuslim");
        File dir = new File(root + "/AnaMuslim/pages_hd");

        if (!dir.exists())           //check if not created then create the firectory
            dir.mkdirs();

        File file = new File(dir, fname);

        return file;
    }

    @NonNull
    public static File audioFileLocation(int id) {

        String fname = "" + id + ".audio";


        File root = Environment.getExternalStoragePublicDirectory("");

        Log.d("Ahmed20", "Lenght of file2: " + root);


        File dir = new File(root + "/AnaMuslim/azkarAudio");

        if (!dir.exists())           //check if not created then create the firectory
            dir.mkdirs();

        File file = new File(dir, fname);

        return file;
    }

    public static boolean isConnected(Context context) {

        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null) {
            if (networkInfo.isConnected())
                return true;
            else
                return false;
        } else
            return false;

    }


    public static void unzip(String destDirectory) throws IOException {
        File root;

        root = Environment.getExternalStoragePublicDirectory("");


        File dir = new File(root + "/AnaMuslim/pages_hd");

        // File destDirectoryFolder = new File(dir);

        if (!dir.exists()) {
            dir.mkdir();
            // Toast.makeText(this, "جوه الفلدور", Toast.LENGTH_SHORT).show();
        }
        String filename = dir + "/images.zip";

        //Toast.makeText(this, "بره  الفلدور", Toast.LENGTH_SHORT).show();
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(filename));
        ZipEntry zipEntry = zis.getNextEntry();
        //  Toast.makeText(this, "55555" + zipEntry.getName(), Toast.LENGTH_SHORT).show();
        while (zipEntry != null) {
            String filePath = destDirectory + File.separator + zipEntry.getName();

            System.out.println("Unzipping " + filePath);
            if (!zipEntry.isDirectory()) {
                FileOutputStream fos = new FileOutputStream(filePath);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            } else {
                File dir1 = new File(filePath);
                dir1.mkdir();
            }
            zis.closeEntry();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
        System.out.println("Unzipping complete");

    }

}

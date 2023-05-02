package com.example.anamuslim.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.anamuslim.R;
import com.example.anamuslim.download.download_quan_image.DownloadListener;
import com.example.anamuslim.download.download_quan_image.DownloadQuranInterface;
import com.example.anamuslim.download.downloadazkaraduio.DownloadAudioInterface;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DownloadUtil {
    private static final String TAG = "DownloadUtil";

    public static Dialog dialog;
    public static ProgressBar progressBar;
    public static TextView view;


    public static void download(final DownloadListener downloadListener) {

        downloadListener.onStart();
     //   String API_BASE_URL = "https://firebasestorage.googleapis.com/v0/b/anamuslim-41793.appspot.com/";
        String API_BASE_URL = "https://firebasestorage.googleapis.com/v0/b/anamuslim-86d9a.appspot.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                // Obtained through thread pool 1 Threads, specifying callback Run in child threads.
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .build();

       // https://firebasestorage.googleapis.com/v0/b/anamuslim-86d9a.appspot.com/o/quran_images.zip?alt=media&token=914afbff-4207-4c6d-ad2f-a79d93dfabae
        DownloadQuranInterface service = retrofit.create(DownloadQuranInterface.class);

        Call<ResponseBody> call = service.download();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                writeResponseToDisk(response, downloadListener);
                Log.d(TAG, "onResponse: " + "تملم ");

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.d(TAG, "onResponse: " + "فشل  ");
                downloadListener.onFail(" Network Error ~ " + t);
            }
        });

    }

    public static void downloadAudio(final DownloadListener downloadListener, String url, int id) {

        downloadListener.onStart();
        String API_BASE_URL = "https://www.hisnmuslim.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                // Obtained through thread pool 1 Threads, specifying callback Run in child threads.
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .build();

        DownloadAudioInterface service = retrofit.create(DownloadAudioInterface.class);

        Call<ResponseBody> call = service.downloadAudio(url);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                writeAudioResponseToDisk(response, downloadListener, id);
                Log.d(TAG, "onResponse: " + "تملم ");

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.d(TAG, "onResponse: " + "فشل  ");
                downloadListener.onFail(" Network Error ~ " + t);
            }
        });

    }


    private static void writeResponseToDisk(Response<ResponseBody> response,
                                            DownloadListener downloadListener) {
        // From response Get the input stream and the total size
        Log.d(TAG, "onResponse: " + "تملم ");

        //  if (response.body() == null)
        writeFileFromIS(
                Util.fileLocation("quranImage"),
                response.body().byteStream(),
                response.body().contentLength(), downloadListener);
    }

    private static void writeAudioResponseToDisk(Response<ResponseBody> response,
                                                 DownloadListener downloadListener, int id) {
        // From response Get the input stream and the total size
        Log.d(TAG, "onResponse: " + "تملم ");

        //  if (response.body() == null)
        writeFileFromIS(
                Util.audioFileLocation(id),
                response.body().byteStream(),
                response.body().contentLength(), downloadListener);
    }

    private static final int sBufferSize = 8192;

    // Write the input stream to a file
    private static void writeFileFromIS(File file, InputStream is,
                                        long totalLength, DownloadListener downloadListener) {
        // Start downloading
        Log.d(TAG, "onResponse: " + "Start downloading ");


        // Create a file
        if (!file.exists()) {
            if (!Objects.requireNonNull(file.getParentFile()).exists())
                file.getParentFile().mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                downloadListener.onFail("createNewFile IOException");
            }
        }

        OutputStream os = null;
        long currentLength = 0;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            byte[] data = new byte[sBufferSize];
            int len;
            while ((len = is.read(data, 0, sBufferSize)) != -1) {
                os.write(data, 0, len);
                currentLength += len;

                // Calculate the current download progress

                downloadListener.onProgress((int) (100 * currentLength / totalLength));
            }
            // Download is complete and return to the saved file path
            //  downloadListener.onFinish(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            downloadListener.onFail("IOException");
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            downloadListener.onFinish(file.getAbsolutePath());
        }
    }



    @SuppressLint("CheckResult")
    public static void downloadAudio(String url, Context context, int id) {

        Observable.create(emitter ->
                        DownloadUtil.downloadAudio(new DownloadListener() {

                            @Override
                            public void onStart() {
                                dialog = new Dialog(context);
                                dialog.setContentView(R.layout.dialog_quran_download);
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                progressBar = dialog.findViewById(R.id.progress_down);
                                view = dialog.findViewById(R.id.tv_down);

                                progressBar.setMax(100);

                                dialog.show();
                            }

                            @Override
                            public void onProgress(int progress) {
                                emitter.onNext(progress);
                            }

                            @Override
                            public void onFinish(String path) {
                                dialog.dismiss();
                            }

                            @Override
                            public void onFail(String errorInfo) {

                            }
                        }, url, id)

                ).doOnNext(
                        c -> Log.d(TAG, "progress Ahmed main " + "doOnNext" + c))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    {
                        progressBar.setProgress((Integer) s);
                        view.setText(s + "");
                    }
                })

        ;
    }

    @SuppressLint("CheckResult")
    public static void downloadProgress(Context context) {

//
//        DownloadUtil.download(new DownloadListener() {
//            @Override
//            public void onStart() {
//
//                Log.d(TAG, "progress Ahmed main" + "ابداء ");
//            }
//
//            @Override
//            public void onProgress(int progress) {
////
////                        dow_progressBar.setProgress(progress);
////                        test_counter.setText(progress + "");
//                Log.d(TAG, "progress Ahmed main" + progress);
//
//
//            }
//
//            @Override
//            public void onFinish(String path) {
//                Log.d(TAG, "progress Ahmed main" + "تمام ");
//            }
//
//            @Override
//            public void onFail(String errorInfo) {
//                Log.d(TAG, "progress Ahmed main" + errorInfo);
//            }
//        });
        //  https://firebasestorage.googleapis.com/v0/b/anamuslim-41793.appspot.com/o/quran_images.zip?alt=media&token=a5883b3d-cceb-4515-abbf-84879c8d14e0
        Observable.create(emitter ->
                        DownloadUtil.download(new DownloadListener() {
                            @Override
                            public void onStart() {
                                dialog = new Dialog(context);
                                dialog.setContentView(R.layout.dialog_quran_download);
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                progressBar = dialog.findViewById(R.id.progress_down);
                                view = dialog.findViewById(R.id.tv_down);

                                progressBar.setMax(100);

                                dialog.show();
                            }

                            @Override
                            public void onProgress(int progress) {
                                emitter.onNext(progress);
                                Log.d(TAG, "progress Ahmed main" + progress);
                            }

                            @Override
                            public void onFinish(String path) {
                                File root;


                                root = Environment.getExternalStoragePublicDirectory("");


                                File dir = new File(root + "/AnaMuslim/pages_hd");

                                String fname = "images.zip";


                                try {

                                    Util.unzip(String.valueOf(dir));


                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                dialog.dismiss();
                            }

                            @Override
                            public void onFail(String errorInfo) {

                            }
                        })

                ).doOnNext(
                        c -> Log.d(TAG, "progress Ahmed main " + "doOnNext" + c))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    {
                        progressBar.setProgress((Integer) s);
                        view.setText(s + "");
                    }
                })

        ;
    }
}

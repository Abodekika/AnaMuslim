package com.example.anamuslim.download.download_quan_image;

public interface DownloadListener {

    void onStart();// Download Start

    void onProgress(int progress);// Download progress

    void onFinish(String path);// Download complete

    void onFail(String errorInfo);
}

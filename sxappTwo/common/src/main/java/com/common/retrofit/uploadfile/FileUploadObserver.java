package com.common.retrofit.uploadfile;

import java.io.File;

import rx.Subscriber;

/**
 * 上传文件的RxJava2回调
 */
public abstract class FileUploadObserver<T> extends Subscriber<T> {

    @Override
    public void onNext(T t) {
        onUpLoadSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onUpLoadFail(e);
    }

    @Override
    public void onCompleted() {
    }

    //监听进度的改变
    public void onProgressChange(File file, long bytesWritten, long contentLength) {
        onProgress((int) (bytesWritten * 100 / contentLength));
    }

    //上传成功的回调
    public abstract void onUpLoadSuccess(T t);

    //上传失败回调
    public abstract void onUpLoadFail(Throwable e);

    //上传进度回调
    public abstract void onProgress(int progress);

}

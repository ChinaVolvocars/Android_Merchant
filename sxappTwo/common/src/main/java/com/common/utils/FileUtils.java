package com.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {

	public static String SDPATH = Environment.getExternalStorageDirectory()
			+ "/formats/";

	public static String saveBitmap(Bitmap bm, String picName) {
		Log.e("", "保存图片");
		File f = null;
		try {
			if (!isFileExist("")) {
				File tempf = createSDDir("");
			}
			 f = new File(SDPATH, picName + ".JPEG");
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f.getPath();
	}

	public static Bitmap changeToBitmap(String path)
	{
		Bitmap rotateBitmap = null;
		try {
			Bitmap pathbitmap = PhotoUtils.revitionImageSize(path);
			rotateBitmap = PhotoUtils.rotateBitmap(pathbitmap, 90);
			return rotateBitmap;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rotateBitmap;
	}

	public static File createSDDir(String dirName) throws IOException {
		File dir = new File(SDPATH + dirName);
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			System.out.println("createSDDir:" + dir.getAbsolutePath());
			System.out.println("createSDDir:" + dir.mkdir());
		}
		return dir;
	}

	public static boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		file.isFile();
		return file.exists();
	}

	public static String getPicStorePath(Context ctx)
	{
		File file = ctx.getExternalFilesDir(null);
		if (file == null) {
			file = ctx.getFilesDir();
		}
		if (!file.exists()) {
			file.mkdir();
		}
		File imageStoreFile = new File(file.getAbsolutePath() + "/mq");
		if (!imageStoreFile.exists()) {
			imageStoreFile.mkdir();
		}
		return imageStoreFile.getAbsolutePath();
	}

	public static void delFile(String fileName){
		File file = new File(SDPATH + fileName);
		if(file.isFile()){
			file.delete();
        }
		file.exists();
	}

	public static void deleteDir() {
		File dir = new File(SDPATH);
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return;
		
		for (File file : dir.listFiles()) {
			if (file.isFile())
				file.delete(); // 删除所有文件
			else if (file.isDirectory())
				deleteDir(); // 递规的方式删除文件夹
		}
		dir.delete();// 删除目录本身
	}

	public static boolean fileIsExists(String path) {
		try {
			File f = new File(path);
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {

			return false;
		}
		return true;
	}

	public static byte[] getBitmapByte(Bitmap bitmap){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}


	public static Bitmap getBitmapFromByte(byte[] temp){
		if(temp != null){
			Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
//			Bitmap rotateBitmap = PhotoUtil.rotateBitmap(bitmap, 90);
			return bitmap;
		}else{
			return null;
		}
	}

	public static String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyy_MM_dd_HH_mm_ss");
		return dateFormat.format(date) + ".jpg";
	}
}

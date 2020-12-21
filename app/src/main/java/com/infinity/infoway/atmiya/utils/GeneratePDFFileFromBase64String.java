package com.infinity.infoway.atmiya.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class GeneratePDFFileFromBase64String {


//    private Context context;
//    private String fileName;
//    private String base64StringFile;
//    private String menuNameToCreateFolderInsideAtmiyaFolder;
//
//    public GeneratePDFFileFromBase64String(Context context, String fileName, String base64StringFile, String menuNameToCreateFolderInsideAtmiyaFolder) {
//        this.context = context;
//        this.fileName = fileName;
//        this.base64StringFile = base64StringFile;
//        this.menuNameToCreateFolderInsideAtmiyaFolder = menuNameToCreateFolderInsideAtmiyaFolder;
//    }
//
//    private void generatePdfFromBase64StringFile() {
//
//        File existingFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), CommonUtil.FOLDER_NAME + "/" + menuNameToCreateFolderInsideAtmiyaFolder + "/" + fileName);
//
//        if (existingFile.exists()) {
//
//            File inputFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), CommonUtil.FOLDER_NAME + "/" + menuNameToCreateFolderInsideAtmiyaFolder + "/" + fileName);
//
//            Intent target = new Intent(Intent.ACTION_VIEW);
//
//            if (Build.VERSION.SDK_INT > 24) {
//                Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file11);
//
//
//                target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                target.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                target.setDataAndType(uri, "application/pdf");
//                Intent intent = Intent.createChooser(target, "Open File");
//                try {
//                    context.startActivity(intent);
//                } catch (ActivityNotFoundException e) {
//                    Toast.makeText(context, "No Apps can performs This action", Toast.LENGTH_LONG).show();
//                }
//            } else {
//                target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                target.setDataAndType(Uri.fromFile(file11), "application/pdf");
//                target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                Intent intent = Intent.createChooser(target, "Open File");
//                try {
//                    startActivity(intent);
//                } catch (ActivityNotFoundException e) {
//                    Toast.makeText(Receipt_Activity.this, "No Apps can performs This action", Toast.LENGTH_LONG).show();
//                }
//
//
//            }
//
//        } else {
//
//        }
//
//
//    }


//    private String generateUniqueFileName(String fileName) {
//        return UUID.randomUUID().toString() + "_" + "news" + "_" + fileName;
//    }

}

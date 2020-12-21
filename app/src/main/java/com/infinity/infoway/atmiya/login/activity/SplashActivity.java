package com.infinity.infoway.atmiya.login.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.infinity.infoway.atmiya.BuildConfig;
import com.infinity.infoway.atmiya.R;
import com.infinity.infoway.atmiya.api.ApiImplementer;
import com.infinity.infoway.atmiya.login.pojo.CheckVersionApiPojo;
import com.infinity.infoway.atmiya.student.profile.StudentProfileActivity;
import com.infinity.infoway.atmiya.student.student_dashboard.activity.StudentDashboardActivity;
import com.infinity.infoway.atmiya.utils.ConnectionDetector;
import com.infinity.infoway.atmiya.utils.DialogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    AppCompatImageView ivSplashLogo;

    private final String[] RunTimePerMissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA};

    private final String[] BACKGROUND_LOCATION_RUN_TIME_PERMISSION = {Manifest.permission.ACCESS_BACKGROUND_LOCATION};
    private static final int MY_PERMISSIONS_REQUEST_READ_WRITE_STATE = 100;
    private static final int REQUEST_CODE_BACKGROND_LOCATION_PERMISSION_FOR_ANDROID_Q = 1002;

    ConnectionDetector connectionDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        loadSplashScreenAnimationAndAskForPermission();
    }

    private void initView() {
        connectionDetector = new ConnectionDetector(SplashActivity.this);
        ivSplashLogo = findViewById(R.id.ivSplashLogo);
    }

    private void loadSplashScreenAnimationAndAskForPermission() {
        Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_up);
        ivSplashLogo.startAnimation(slide_up);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(1600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (!hasPermissions(SplashActivity.this, RunTimePerMissions)) {
                            ActivityCompat.requestPermissions(SplashActivity.this, RunTimePerMissions, MY_PERMISSIONS_REQUEST_READ_WRITE_STATE);
                        } else {
                            permissionForBackgroundLocationAndroidQ();
                        }
                    } else {
                        checkVersionInfoApiCall();
                    }
                }
            }
        };
        timer.start();
    }

    private void redirectToLoginActivity() {
        Intent openMainActivity = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(openMainActivity);
        finish();
    }


    private static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean hasPermissionsForBackgroundLocation(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {

                    return false;
                }
            }
        }
        return true;
    }

    private void permissionForBackgroundLocationAndroidQ() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (!hasPermissionsForBackgroundLocation(SplashActivity.this, BACKGROUND_LOCATION_RUN_TIME_PERMISSION)) {
                ActivityCompat.requestPermissions(SplashActivity.this, BACKGROUND_LOCATION_RUN_TIME_PERMISSION,
                        REQUEST_CODE_BACKGROND_LOCATION_PERMISSION_FOR_ANDROID_Q);
            } else {
                checkVersionInfoApiCall();
            }
        } else {
            checkVersionInfoApiCall();
        }
    }

    private void alertAlert(String msg) {
        new MaterialAlertDialogBuilder(SplashActivity.this)
                .setTitle(Html.fromHtml("<b>" + getResources().getString(R.string.permission_request) + " </b>"))
                .setMessage(msg + " So Reopen the app and grant permission for well uses of app")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_WRITE_STATE) {

            if (grantResults.length == 5 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[3] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[4] == PackageManager.PERMISSION_GRANTED) {
                permissionForBackgroundLocationAndroidQ();
            } else {
                alertAlert(getResources().getString(R.string.permissions_has_not_grant));
            }
        } else if (requestCode == REQUEST_CODE_BACKGROND_LOCATION_PERMISSION_FOR_ANDROID_Q) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkVersionInfoApiCall();
            } else {
                alertAlert(getResources().getString(R.string.permissions_has_not_grant));
            }
        }
    }

    private void checkVersionInfoApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            PackageInfo pInfo = null;
            int appVersionCode = 0;
            try {
                pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                appVersionCode = pInfo.versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (pInfo == null) {
                appVersionCode = BuildConfig.VERSION_CODE;
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DialogUtil.showProgressDialogNotCancelable(SplashActivity.this, "");
                }
            });

            ApiImplementer.checkVersionInfoApiImplementer(appVersionCode, new Callback<CheckVersionApiPojo>() {
                @Override
                public void onResponse(Call<CheckVersionApiPojo> call, Response<CheckVersionApiPojo> response) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            DialogUtil.hideProgressDialog();
                        }
                    });
                    if (response.isSuccessful()) {
                        if (Integer.parseInt(response.body().getStatus()) == 1) {//1 For Opetional Update
                            new MaterialAlertDialogBuilder(SplashActivity.this)
                                    .setMessage("New version available.Would you like to update your app ?")
                                    .setCancelable(false)
                                    .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            try {
                                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                                                finish();
                                            } catch (android.content.ActivityNotFoundException anfe) {
                                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                                                finish();
                                            }
                                        }
                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            redirectToLoginActivity();
                                        }
                                    })
                                    .show();
                        } else if (Integer.parseInt(response.body().getStatus()) == 2) {//2 For Force Update
                            new MaterialAlertDialogBuilder(SplashActivity.this)
                                    .setMessage("New version available.Would you like to update your app ?")
                                    .setCancelable(false)
                                    .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            try {
                                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                                                finish();
                                            } catch (android.content.ActivityNotFoundException anfe) {
                                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                                                finish();
                                            }
                                        }
                                    }).show();
                        } else {// 0 for app is already up to date redirect to login activity
                            redirectToLoginActivity();
                        }
                    } else {
                        redirectToLoginActivity();
                    }
                }

                @Override
                public void onFailure(Call<CheckVersionApiPojo> call, Throwable t) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            DialogUtil.hideProgressDialog();
                        }
                    });
                    redirectToLoginActivity();
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(SplashActivity.this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }


}
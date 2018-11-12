package com.app.jiwon.tekken7_manual.Activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.app.jiwon.tekken7_manual.Items.VersionCheck;
import com.app.jiwon.tekken7_manual.Listener.SuccessListener;
import com.app.jiwon.tekken7_manual.R;
import com.app.jiwon.tekken7_manual.Util.SharedPreferencesUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.json.JSONObject;

import java.io.File;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    private int dbCount = 0;

    private Context context = SplashActivity.this;

    private Intent intent;

    private int count = 0;

    final ArrayList<DataSnapshot> arrayListDataSnapshot = new ArrayList<>();
    final ArrayList<String> arrayListKey = new ArrayList<>();

    @BindString(R.string.splashActivity_google_alert_message)
    String googleAlertMessage;

    @BindString(R.string.splashActivity_download_alert_message)
    String downloadAlertMessage;

    @BindString(R.string.splashActivity_permisson_alert_message)
    String permissonAlertMessage;

    @BindString(R.string.splashActivity_permisson_denied_message)
    String permissonDeniedMessage;

    @BindString(R.string.splashActivity_permisson_rationale_message)
    String permissonRationaleMessage;

    @BindString(R.string.alert_agree_button)
    String agree;

    @BindString(R.string.alert_refuse_button)
    String refuse;

    @BindString(R.string.download_fail)
    String fail;

    @BindString(R.string.splashActivity_download_network_alert_message)
    String networkAlert;

    @BindArray(R.array.versionKey)
    String[] versionKey;

    private static final String FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send";
    private static final String SERVER_KEY = "AAAAwn0NO-4:APA91bGv4lBoEVJYJVZYc2h8ociCjyo7IgMWHYSgIRS0H-yW_mwaKdShRxqPgwtG6So2E_9cHZGCFrkzO1KbeVSCTFdoY_Cry0zpHNXrOisnJ7lEx-lrNtfwPsAownCmymK2sypssqm5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Send("fFIdjd0-gHw:APA91bEKGTPWiMj8RhQnoW0ROGSIPmSurApLK18IHxmhJrRVIWbeNWLwxEbF6UIB5Iuw9m303hs-KEClOSi6LX3u6FRALxr3YG6c_AcRU5QiQl4jOwGozIdH73wWkuUjysqybinpXQc-");
//        Log.e("token", FirebaseInstanceId.getInstance().getToken());

        intent = new Intent(this, MainActivity.class);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {

                NetworkInfo info = getNetworkInfo();

                if (info != null && info.isConnected())
                    checkGoogle();
                else {
                    Toast.makeText(context, networkAlert, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(context, permissonAlertMessage, Toast.LENGTH_SHORT).show();
                finish();
            }
        };


        new TedPermission().with(context)
                .setPermissionListener(permissionListener)
                .setRationaleMessage(permissonRationaleMessage)
                .setDeniedMessage(permissonDeniedMessage)
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_NETWORK_STATE)
                .check();
    }

    //test
    public void Send(final String token) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject root = new JSONObject();
                    JSONObject notification = new JSONObject();
                    notification.put("body", "test");
                    root.put("notification", notification);
                    root.put("to", token);

                    URL Url = new URL(FCM_MESSAGE_URL);
                    HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.addRequestProperty("Authorization", "key=" + SERVER_KEY);
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setRequestProperty("Content-type", "application/json");
                    OutputStream os = conn.getOutputStream();
                    os.write(root.toString().getBytes("utf-8"));
                    os.flush();
                    conn.getResponseCode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private NetworkInfo getNetworkInfo() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        return info;
    }

    private void checkGoogle() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(context);

        if (status == ConnectionResult.SUCCESS) {
            //TODO 앱버전체크
            versionCheck();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this)
                    .setMessage(googleAlertMessage)
                    .setPositiveButton(agree, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dlg, int value) {
                            finish();
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

//    private void appVersionChecker() {
//        try {
//
//            String store_version = VersionChecker.getMarketVersion(getPackageName());
//            String device_version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
//
//            if (store_version.compareTo(device_version) > 0) {
//                // 업데이트 필요
//
//                Log.e("true_store", store_version);
//                Log.e("true_app", device_version);
//
//            } else {
//
//                Log.e("false_store", store_version);
//                Log.e("false_app", device_version);
//
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

//    public class MyAsyncTask extends AsyncTask {
//
//        @Override
//        protected Object doInBackground(Object[] objects) {
//            return null;
//        }
//
//    }

    public void versionChecker(final SuccessListener listener) {
        for (final String key : versionKey) {
            FirebaseDatabase.getInstance().getReference().child("download").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot item : dataSnapshot.getChildren()) {
                        final VersionCheck modal = item.getValue(VersionCheck.class);
                        int thisVersion = SharedPreferencesUtil.getIntPreferences(dataSnapshot.getKey(), item.getKey(), 0, context);

                        if (modal.version > thisVersion) {
//                            Log.e("adsfdf", key + ": " + modal.version + ", 기기 버전 : " + thisVersion);

                            arrayListDataSnapshot.add(item);
                            arrayListKey.add(dataSnapshot.getKey());
                        }
                    }

                    dbCount++;
//                    Log.e("adfadf", key + " onSuccess" + dbCount);
                    listener.OnSuccess(dbCount);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(context, fail, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void versionCheck() {
        versionChecker(new SuccessListener() {
            @Override
            public void OnSuccess(int key) {
                if (key == 5 && !arrayListDataSnapshot.isEmpty()) {
//                    Log.e("df", "다운로드 이행");

                    AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this)
                            .setMessage(downloadAlertMessage)
                            .setPositiveButton(agree, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dlg, int value) {
                                    down();
                                }
                            })
                            .setNegativeButton(refuse, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(intent);
                                    finish();
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else if (key == 5 && arrayListDataSnapshot.isEmpty()) {
//                    Log.e("adf", "다운로드 안하고 넘어감");
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void down() {
        for (int i = 0; i < arrayListDataSnapshot.size(); i++) {
            final DataSnapshot item = arrayListDataSnapshot.get(i);
            final String key0 = arrayListKey.get(i);

            final VersionCheck modal = item.getValue(VersionCheck.class);

            downloadData(key0, item.getKey(), modal.version);
        }
    }

    public void downloadData(final String downPath, final String characterName, final int version) {
        String sdCard = Environment.getExternalStorageState();
        String path;

        if (!sdCard.equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getRootDirectory().getAbsolutePath() + "/.tekken_manual/" + downPath;
        } else {
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/.tekken_manual/" + downPath;
        }

        File localFile = new File(path);

        if (!localFile.exists()) {
            localFile.mkdirs();
        }

        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
        StorageReference jsonRef = null;
        final File file;

        if (downPath.equals("dictionary") || downPath.equals("patchnote") || downPath.equals("profile")) {
            jsonRef = mStorageRef.child(downPath + "/" + downPath + ".json");
            file = new File(path, downPath + ".json");

        } else {
            jsonRef = mStorageRef.child(String.format(downPath + "/" + downPath + "_%s.json", characterName));
            file = new File(path, String.format(downPath + "_%s.json", characterName));
        }

        jsonRef.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                SharedPreferencesUtil.saveIntPreferences(downPath, characterName, version, context);

                count++;

                if (arrayListDataSnapshot.size() == count) {
                    startActivity(intent);
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, fail, Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
    }
}

package sabbir360.core.refreshrateutility;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public static int CODE_WRITE_SETTINGS_PERMISSION = 41;
    TextView tv_refresh_rate;
    Display display;
    Utility utility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GetPermission();


        //https://stackoverflow.com/questions/32083410/cant-get-write-settings-permission

        display = ((WindowManager) Objects.requireNonNull(getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay();
        utility = new Utility();

        setContentView(R.layout.activity_main);

        tv_refresh_rate = findViewById(R.id.refresh_rate);
        tv_refresh_rate.setText(utility.getRefreshRate(display));

    }

    public void GetPermission(){
        if(!Settings.System.canWrite(this)){
            Toast.makeText(getApplicationContext(),
                    "App will not work without the this permission, restart and allow!",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,  Uri.parse("package:" + getPackageName()));

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
            startActivityForResult(intent, MainActivity.CODE_WRITE_SETTINGS_PERMISSION);

        }else{
            Toast.makeText(getApplicationContext(),
                    "Thanks for permission, its safe!",
                    Toast.LENGTH_LONG).show();
        }

    }


    public void onSet48HZ(View view) {
        if (utility.setRefreshRate(this, "48"))
            tv_refresh_rate.setText("48");
    }

    public void onSet60HZ(View view) {
        if (utility.setRefreshRate(this, "60"))
            tv_refresh_rate.setText("60");
    }

    public void onSet96HZ(View view) {
        if (utility.setRefreshRate(this, "96"))
            tv_refresh_rate.setText("96");
    }

    public void onSet120HZ(View view) {
        if (utility.setRefreshRate(this, "120"))
            tv_refresh_rate.setText("120");
    }
}



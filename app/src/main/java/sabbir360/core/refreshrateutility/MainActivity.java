package sabbir360.core.refreshrateutility;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
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

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView tv_refresh_rate;
    Display display;
    Utility utility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //https://stackoverflow.com/questions/32083410/cant-get-write-settings-permission
//        requestPermissions();
        display = ((WindowManager) Objects.requireNonNull(getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay();
        utility = new Utility();

        setContentView(R.layout.activity_main);

        tv_refresh_rate = findViewById(R.id.refresh_rate);
        tv_refresh_rate.setText(utility.getRefreshRate(display));

    }


    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.WRITE_SETTINGS)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_SETTINGS}, 1);
            }else{
                Toast.makeText(this, "Permission Required", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*public static void CheckRequiredPermissionCode(Activity context){
        boolean permission;
        permission = Settings.System.canWrite(context);
        if (permission) {
            Toast.makeText(context, "Write Permission Found!", Toast.LENGTH_SHORT).show();
        }  else {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivityForResult(intent, MainActivity.CODE_WRITE_SETTINGS_PERMISSION);
        }
    }

    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MainActivity.CODE_WRITE_SETTINGS_PERMISSION && Settings.System.canWrite(this)){
            Log.d("TAG", "MainActivity.CODE_WRITE_SETTINGS_PERMISSION success");
            Toast.makeText(this, "Write Permission Found!", Toast.LENGTH_SHORT).show();
        }else{
            CheckRequiredPermissionCode(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MainActivity.CODE_WRITE_SETTINGS_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Write Permission Found!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Write Permission Found!", Toast.LENGTH_SHORT).show();
        }
    }*/

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



package sabbir360.core.refreshrateutility;

import android.content.ContentValues;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

public class Utility {

    public String getRefreshRate(Display display){
        float refreshRate = display.getRefreshRate();
        return String.valueOf(Math.round(refreshRate));
    }

    private boolean setConfig(Context context, String key, String value){
        ContentResolver contextResolver = context.getContentResolver();
        try{
            ContentValues contentValues = new ContentValues(2);
            contentValues.put("name", key);
            contentValues.put("value", value);
            contextResolver.insert(Uri.parse("content://settings/system"), contentValues);
            if(!key.equals("min_refresh_rate")){
                Toast.makeText(context, "Applied: "+value, Toast.LENGTH_SHORT).show();
            }

            return true;
        }catch (Exception e){
//            Toast.makeText(context, "Failed to set value: "+value, Toast.LENGTH_SHORT).show();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return false;
    }

    public boolean setRefreshRate(Context context, String refreshRate){
        return setConfig(context, "min_refresh_rate", "48") && setConfig(context, "peak_refresh_rate", refreshRate);
//        return success;
    }
}

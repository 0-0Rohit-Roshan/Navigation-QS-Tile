package ro.gesturetile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.service.quicksettings.TileService.requestListeningState;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //when call requestListeningState, onStartListening in MyTileService.class is run
        ComponentName componentName = new ComponentName(getApplicationContext(), MyTileService.class);
        requestListeningState(this, componentName);

        // Get the button and add onClickListener.
        final Button writeSettingsPermissionButton = (Button)findViewById(R.id.write_settings_permission_button);
        writeSettingsPermissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                // Check whether has the write settings permission or not.
                boolean settingsCanWrite = Settings.System.canWrite(context);
                if(!settingsCanWrite) {
                    // If do not have write settings permission then open the Can modify system settings panel.
                    Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                    startActivity(intent);
                }else {
                    // If has permission then show an alert dialog with message.
                   // Toast.makeText(this, "You have system write settings permission now.",occuring error).show();
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setMessage("You have system write settings permission now.");
                    alertDialog.show(); 
                }
            }
        });
    }
}


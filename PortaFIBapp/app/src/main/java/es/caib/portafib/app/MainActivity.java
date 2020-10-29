package es.caib.portafib.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.security.KeyChain;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Pantalla principal.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Si les notificacions estan activades llançam el worker de consulta de notificacions.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings_option:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.install_cert_option:
                Intent installIntent = KeyChain.createInstallIntent();
                startActivity(installIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
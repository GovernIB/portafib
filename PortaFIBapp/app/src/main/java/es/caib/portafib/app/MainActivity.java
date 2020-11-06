package es.caib.portafib.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.security.KeyChain;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.concurrent.ExecutorService;

/**
 * Pantalla principal.
 */
public class MainActivity extends AppCompatActivity {

    private final ExecutorService executorService = PortaFIBApplication.getInstance().getExecutorService();

    private NotificacioViewModel viewModel;

    /**
     * Si les notificacions estan activades llançam el worker de consulta de notificacions.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(NotificacioViewModel.class);

        TextView errorTextView = findViewById(R.id.errorTextView);
        viewModel.getException().observe(this, e -> {
            String message = getString(R.string.error_notificacions_message, e.getMessage());
            errorTextView.setText(message);
            errorTextView.setVisibility(View.VISIBLE);
        });
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
            case R.id.update_option:
                executorService.submit(viewModel::load);
                return true;
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
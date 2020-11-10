package es.caib.portafib.app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.KeyChain;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;

import es.caib.portafib.app.client.SSLUtil;
import es.caib.portafib.app.worker.WorkerHelper;

public class SettingsActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.i("onShared", "key:" + key);
        PortaFIBApplication.getInstance().getRestClient().setEndpoint(
                ServerUrlUtil.getRestEndpoint(this)
        );
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            SwitchPreferenceCompat notificacionsSw = findPreference("notificacions_sw");
            assert notificacionsSw != null;
            notificacionsSw.setOnPreferenceChangeListener(
                    (preference, newValue) -> {
                        boolean actiu = (boolean) newValue;
                        if (actiu) {
                            WorkerHelper.startWorker(requireContext());
                        } else {
                            WorkerHelper.cancelWorker(requireContext());
                        }
                        return true;
                    }
            );

            SwitchPreferenceCompat clientAliasCertSw = findPreference("client_alias_cert_sw");
            assert clientAliasCertSw != null;
            clientAliasCertSw.setOnPreferenceClickListener(
                    preference -> {
                        if (!clientAliasCertSw.isChecked()) {
                            saveClientAliasCert(null);
                            return true;
                        }

                        KeyChain.choosePrivateKeyAlias(
                                requireActivity(),
                                this::saveClientAliasCert,
                                null, null, null, -1, null);
                        return true;
                    }
            );

            EditTextPreference clientAliasCert = findPreference("client_alias_cert");
            assert clientAliasCert != null;
            clientAliasCert.setSummary(clientAliasCert.getText());
            clientAliasCert.setShouldDisableView(clientAliasCert.getText() == null);
        }

        private void saveClientAliasCert(String alias) {
            requireActivity().runOnUiThread(() -> {
                SwitchPreferenceCompat clientAliasCertSw = findPreference("client_alias_cert_sw");
                assert clientAliasCertSw != null;
                clientAliasCertSw.setChecked(alias != null);

                EditTextPreference clientAliasCert = findPreference("client_alias_cert");
                assert clientAliasCert != null;
                clientAliasCert.setText(alias);
                clientAliasCert.setSummary(alias);
                clientAliasCert.setShouldDisableView(alias == null);
            });
            SSLUtil.prepareSsl(requireContext(), alias);
        }
    }
}
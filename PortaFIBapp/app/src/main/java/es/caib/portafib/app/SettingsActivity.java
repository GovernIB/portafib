package es.caib.portafib.app;

import android.os.Bundle;
import android.security.KeyChain;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import es.caib.portafib.app.client.SSLUtil;
import es.caib.portafib.app.worker.WorkerHelper;

public class SettingsActivity extends AppCompatActivity {

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
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            EditTextPreference serverBaseUrl = findPreference("server_baseurl");
            assert serverBaseUrl != null;
            serverBaseUrl.setOnPreferenceChangeListener(
                    ((preference, newValue) -> {
                        String newBaseUrl = (String) newValue;
                        PortaFIBApplication.getInstance().getRestClient().setBaseUrl(newBaseUrl);
                        return true;
                    })
            );

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
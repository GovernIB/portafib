package es.caib.portafib.app;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Collections;
import java.util.List;

import es.caib.portafib.app.client.NotificacioRest;
import es.caib.portafib.app.client.RestClient;

public class NotificacioViewModel extends ViewModel {

    private final RestClient restClient = PortaFIBApplication.getInstance().getRestClient();

    private final MutableLiveData<List<NotificacioRest>> notificacions = new MutableLiveData<>();
    private final MutableLiveData<Exception> exception = new MutableLiveData<>();

    public MutableLiveData<List<NotificacioRest>> getNotificacions() {
        return notificacions;
    }

    public MutableLiveData<Exception> getException() {
        return exception;
    }

    public void load() {
        try {
            List<NotificacioRest> data = restClient.getNotificacions();
            notificacions.postValue(data);
            exception.postValue(null);
        } catch (Exception e) {
            Log.e("NotificacioViewModel", "Error", e);
            exception.postValue(e);
            notificacions.postValue(Collections.emptyList());
        }
    }
}

package es.caib.portafib.app;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import es.caib.portafib.app.client.NotificacioRest;
import es.caib.portafib.app.client.RestClient;

public class NotificacioViewModel extends AndroidViewModel {

    private final MutableLiveData<List<NotificacioRest>> notificacions = new MutableLiveData<>();
    private final MutableLiveData<Exception> exception = new MutableLiveData<>();

    public NotificacioViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<NotificacioRest>> getNotificacions() {
        return notificacions;
    }

    public MutableLiveData<Exception> getException() {
        return exception;
    }

    public void load() {
        try {
            List<NotificacioRest> data = RestClient.getNotificacions(getApplication());
            notificacions.postValue(data);
        } catch (Exception e) {
            Log.e("NotificacioViewModel", "Error", e);
            exception.postValue(e);
        }
    }
}

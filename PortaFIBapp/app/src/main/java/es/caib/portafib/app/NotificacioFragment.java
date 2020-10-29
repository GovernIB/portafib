package es.caib.portafib.app;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Data;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

import es.caib.portafib.app.client.NotificacioRest;
import es.caib.portafib.app.client.NotificacioUtil;
import es.caib.portafib.app.client.RestClient;
import es.caib.portafib.app.worker.WorkerHelper;

/**
 * A fragment representing a list of Items.
 */
public class NotificacioFragment extends Fragment {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NotificacioFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("NotificacioFragment", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_notificacio_list, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new NotificacioRecyclerViewAdapter(Collections.emptyList()));

            new GetNotificacionsAsyncTask(recyclerView).execute();
        }
        return view;
    }

    public static class GetNotificacionsAsyncTask extends AsyncTask<Void, Void, List<NotificacioRest>> {

        private final WeakReference<RecyclerView> recyclerViewRef;

        public GetNotificacionsAsyncTask(RecyclerView recyclerView) {
            this.recyclerViewRef = new WeakReference<>(recyclerView);
        }

        @Override
        protected List<NotificacioRest> doInBackground(Void... voids) {
            RecyclerView recyclerView = recyclerViewRef.get();
            if (recyclerView != null) {
                Context context = recyclerView.getContext();
                try {
                    String json = RestClient.getNotificacions(context);
                    return NotificacioUtil.fromJson(json);
                } catch (Exception e) {
                    Log.e("GetNotificacionsAsyncTask", "Error obtenint notificacions", e);
                }

            }
            return Collections.emptyList();
        }

        @Override
        protected void onPostExecute(List<NotificacioRest> result) {
            RecyclerView recyclerView = recyclerViewRef.get();
            if (recyclerView != null) {
                recyclerView.setAdapter(new NotificacioRecyclerViewAdapter(result));
            }
        }
    }
}
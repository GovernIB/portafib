package es.caib.portafib.app;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.Collections;
import java.util.List;

import es.caib.portafib.app.client.NotificacioRest;
import es.caib.portafib.app.client.NotificacioUtil;
import es.caib.portafib.app.client.RestClient;

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
            //recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

            List<NotificacioRest> notificacioRestList;
            String json = PreferenceHelper.getLastJsonResponse(context);
            if (json != null) {
                notificacioRestList = NotificacioUtil.fromJson(json);
            } else {
                notificacioRestList = Collections.emptyList();
            }

            NotificacioRecyclerViewAdapter adapter = new NotificacioRecyclerViewAdapter(notificacioRestList);
            recyclerView.setAdapter(adapter);

            SwipeRefreshLayout swipeRefreshLayout = requireActivity().findViewById(R.id.swipeRefreshLayout);
            swipeRefreshLayout.setOnRefreshListener(() -> PortaFIBApplication.getInstance().getExecutorService().submit(
                    () -> {
                        try {
                            String jsonData = RestClient.getNotificacions(getContext());
                            PreferenceHelper.setLastJsonResponse(getContext(), jsonData);
                            List<NotificacioRest> list = NotificacioUtil.fromJson(jsonData);
                            requireActivity().runOnUiThread(() -> adapter.updateList(list));
                        } catch (Throwable e) {
                            Log.e("NotificacioFragment", "Error a onRefresh", e);
                            PreferenceHelper.setLastJsonResponse(getContext(), null);
                            requireActivity().runOnUiThread(() -> {
                                adapter.updateList(Collections.emptyList());
                                Toast toast = Toast.makeText(context, R.string.error_notificacions_message, Toast.LENGTH_LONG);
                                toast.show();
                            });
                        } finally {
                            requireActivity().runOnUiThread(
                                    () -> swipeRefreshLayout.setRefreshing(false));
                        }
                    }));
        }
        return view;
    }
}
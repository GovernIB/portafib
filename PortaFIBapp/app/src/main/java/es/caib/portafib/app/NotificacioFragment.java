package es.caib.portafib.app;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.concurrent.ExecutorService;

/**
 * A fragment representing a list of Items.
 */
public class NotificacioFragment extends Fragment {

    private final ExecutorService executorService = PortaFIBApplication.getInstance().getExecutorService();

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
        View view = inflater.inflate(R.layout.fragment_notificacio_list, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            NotificacioRecyclerViewAdapter adapter = new NotificacioRecyclerViewAdapter();
            recyclerView.setAdapter(adapter);

            FragmentActivity activity = requireActivity();
            NotificacioViewModel viewModel = new ViewModelProvider(activity).get(NotificacioViewModel.class);
            viewModel.getNotificacions().observe(activity, adapter::updateList);

            SwipeRefreshLayout swipeRefreshLayout = activity.findViewById(R.id.swipeRefreshLayout);
            swipeRefreshLayout.setOnRefreshListener(() -> executorService.submit(
                    () -> {
                        try {
                            viewModel.load();
                        } finally {
                            activity.runOnUiThread(() -> swipeRefreshLayout.setRefreshing(false));
                        }
                    }));

            // Feim la primera càrrega de dades al principi?
            //executorService.submit(viewModel::load);
        }
        return view;
    }
}
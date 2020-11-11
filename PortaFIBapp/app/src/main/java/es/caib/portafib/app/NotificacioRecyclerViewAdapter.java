package es.caib.portafib.app;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import es.caib.portafib.app.client.NotificacioRest;

/**
 * Adapter per actualitzar la llista de avisos
 */
public class NotificacioRecyclerViewAdapter extends RecyclerView.Adapter<NotificacioRecyclerViewAdapter.ViewHolder> {

    private List<NotificacioRest> mValues = Collections.emptyList();

    public NotificacioRecyclerViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_notificacio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        String label = NotificacioUtil.getLabel(holder.mContentView.getContext(), holder.mItem);
        String url = NotificacioUtil.getUrl(holder.mContentView.getContext(), holder.mItem);
        holder.mContentView.setText(label);
        holder.mContentView.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
            browserIntent.setData(Uri.parse(url));
            v.getContext().startActivity(browserIntent);
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void updateList(List<NotificacioRest> mValues) {
        this.mValues = mValues;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public NotificacioRest mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = view.findViewById(R.id.content);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
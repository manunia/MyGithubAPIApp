package ru.marial.mygithubapiapp.ui;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.marial.mygithubapiapp.R;
import ru.marial.mygithubapiapp.dao.UsersDataSource;
import ru.marial.mygithubapiapp.model.Users;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private UsersDataSource datasourse;
    private Activity activity;

    private OnItemClickListener itemClickListener;

    public UsersDataSource getDatasourse() {
        return datasourse;
    }

    public ListAdapter(UsersDataSource dataset, Activity activity) {
        this.datasourse = dataset;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);

        ViewHolder vh = new ViewHolder(v);
        if (itemClickListener != null) {
            vh.setOnClickListener(itemClickListener);
        }
        Log.e("Adapter", "onCreateViewHolder");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int i) {
        List<Users> users = datasourse.getUsers();
        Users oneOfUsers = users.get(i);

        holder.userName.setText(oneOfUsers.getLogin());
        Picasso.get()
                .load(oneOfUsers.getAvatar()).into(holder.avatar);

        if (activity != null) {
            activity.registerForContextMenu(holder.cardView);
        }

        Log.e("Adapter", "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return (int) datasourse.getCountUsers();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, String name, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView userName;
        private ImageView avatar;
        View cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView;
            userName = itemView.findViewById(R.id.itemLogin);
            avatar = itemView.findViewById(R.id.itemAvatar);
        }

        public void setOnClickListener(final OnItemClickListener listener) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getAdapterPosition();

                    if (adapterPosition == RecyclerView.NO_POSITION) return;

                    listener.onItemClick(v, getDatasourse().getUsers().get(adapterPosition).getLogin(), adapterPosition);
                }
            });
        }
    }
}

package com.inferno.mobile.accountent.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.databinding.AdminItemBinding;
import com.inferno.mobile.accountent.models.User;
import com.inferno.mobile.accountent.ui.admin.remove_admin.RemoveViewModel;

import java.util.ArrayList;

public class AdminRVAdapter extends RecyclerView.Adapter<AdminRVAdapter.AdminHolder> {
    private final Context context;
    private final ArrayList<User> users;
    private RemoveItemListener<User> listener;

    public void setRemoveItemListener(RemoveItemListener<User> listener) {
        this.listener = listener;
    }

    public AdminRVAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public AdminHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdminHolder(LayoutInflater.from(context).
                inflate(R.layout.admin_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminHolder holder,int position) {
        User user = users.get(position);
        holder.binding.adminName.setText(user.getUserName());
//        holder.binding.adminEmail.setText(user.getEmail());
        holder.binding.removeBtn.setOnClickListener(v->{
            if (listener!=null)
                listener.onRemove(user,holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class AdminHolder extends RecyclerView.ViewHolder {
        AdminItemBinding binding;
        public AdminHolder(@NonNull View itemView) {
            super(itemView);
            binding = AdminItemBinding.bind(itemView);
        }
    }
}

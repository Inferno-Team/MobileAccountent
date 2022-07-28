package com.inferno.mobile.accountent.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.databinding.CheckOutItemBinding;
import com.inferno.mobile.accountent.models.ReceiptItem;

import java.util.ArrayList;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ReceiptHolder> {
    private final ArrayList<ReceiptItem> items;
    private final Context context;

    public ReceiptAdapter(ArrayList<ReceiptItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ReceiptHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReceiptHolder(LayoutInflater.from(context)
                .inflate(R.layout.check_out_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptHolder holder, int position) {
        ReceiptItem item = items.get(position);
        String price = context.getString(R.string.currency, String.valueOf(item.getPrice()));
        String fullPrice = context.getString(R.string.currency, String.valueOf(item.getFullPrice()));

        holder.binding.itemName.setText(item.getCategoryName());
        holder.binding.fullPrice.setText(fullPrice);
        holder.binding.itemPrice.setText(price);
        holder.binding.itemCount.setText(String.valueOf(item.getCount()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ReceiptHolder extends RecyclerView.ViewHolder {
        public CheckOutItemBinding binding;

        public ReceiptHolder(@NonNull View itemView) {
            super(itemView);
            binding = CheckOutItemBinding.bind(itemView);
        }
    }
}

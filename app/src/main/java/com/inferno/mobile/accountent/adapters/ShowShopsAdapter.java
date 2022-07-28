package com.inferno.mobile.accountent.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.databinding.ShopeItemBinding;
import com.inferno.mobile.accountent.models.Shop;

import java.util.ArrayList;

public class ShowShopsAdapter extends RecyclerView.Adapter<ShowShopsAdapter.ShowShopHolder> {
    private final ArrayList<Shop> shops;
    private final Context context;

    public ShowShopsAdapter(ArrayList<Shop> shops, Context context) {
        this.shops = shops;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowShopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShowShopHolder(LayoutInflater.from(context).
                inflate(R.layout.shope_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShowShopHolder holder, int position) {
        Shop shop = shops.get(position);
        holder.binding.shopName.setText(shop.getShopName());
        holder.binding.requestType.setVisibility(View.GONE);
        holder.binding.requestTimeValue.setVisibility(View.GONE);
        holder.binding.requestTimeTxt.setVisibility(View.GONE);
        holder.binding.shopLocation.setText(shop.getLocation());

    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public static class ShowShopHolder extends RecyclerView.ViewHolder {
        ShopeItemBinding binding;

        public ShowShopHolder(@NonNull View itemView) {
            super(itemView);
            binding = ShopeItemBinding.bind(itemView);
        }
    }
}

package com.inferno.mobile.accountent.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.databinding.BillItemBinding;
import com.inferno.mobile.accountent.models.BillDetails;
import com.inferno.mobile.accountent.utils.ContextUtils;

import java.util.ArrayList;
import java.util.Calendar;

public class MyBillsAdapter extends RecyclerView.Adapter<MyBillsAdapter.MyBillViewHolder> {

    private final Context context;
    private final ArrayList<BillDetails> details;
    private AdapterItemListener onItemClickListener, onShopNameClickListener;

    public void setOnItemClickListener(AdapterItemListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MyBillsAdapter(Context context, ArrayList<BillDetails> details) {
        this.context = context;
        this.details = details;
    }

    @NonNull
    @Override
    public MyBillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyBillViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.bill_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyBillViewHolder holder, int position) {
        BillDetails billDetails = details.get(position);
        holder.binding.cashierName.setText(billDetails.getCashier().getUserName());
        holder.binding.shopName.setText(billDetails.getShop().getShopName());
        holder.binding.shopLocation.setText(billDetails.getShop().getLocation());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(billDetails.getCreatedAt());
        String time = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1)
                + "-" + calendar.get(Calendar.DAY_OF_MONTH);
        holder.binding.requestTimeValue.setText(time);
        holder.binding.checkValue.setText(context.getString(R.string.currency,
                String.valueOf(billDetails.getCheck())));
        holder.itemView.setOnClickListener(l -> {
            if (onItemClickListener != null)
                onItemClickListener.onClick(billDetails.getId(), holder.getAdapterPosition());
        });
        holder.binding.shopName.setOnClickListener(l -> {
            if (onShopNameClickListener != null)
                onShopNameClickListener.onClick(billDetails.getId(), holder.getAdapterPosition());
        });
        setAnimation(holder.itemView);
    }

    private void setAnimation(View viewToAnimate) {
        // If the bound view wasn't previously displayed on screen, it's animated
        Animation animation;
        if (ContextUtils.getLanguage(context).equals("English")) {
            animation = AnimationUtils.loadAnimation(context, R.anim.fade_item);
        } else {
            animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        }
        viewToAnimate.startAnimation(animation);

    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public void setOnShopNameClickListener(AdapterItemListener onShopNameClickListener) {
        this.onShopNameClickListener = onShopNameClickListener;
    }

    public static class MyBillViewHolder extends RecyclerView.ViewHolder {
        BillItemBinding binding;

        public MyBillViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = BillItemBinding.bind(itemView);
        }
    }
}

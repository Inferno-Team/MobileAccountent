package com.inferno.mobile.accountent.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.databinding.RequestItemBinding;
import com.inferno.mobile.accountent.models.Shop;
import com.inferno.mobile.accountent.utils.ContextUtils;

import java.util.ArrayList;
import java.util.Calendar;

public class RequestRVAdapter extends RecyclerView.Adapter<RequestRVAdapter.RequestHolder> {
    private final Context context;
    private final ArrayList<Shop> shops;
    private AdapterItemListener listener;

    public void setShopItemListener(AdapterItemListener listener) {
        this.listener = listener;
    }

    public RequestRVAdapter(Context context, ArrayList<Shop> shops) {
        this.context = context;
        this.shops = shops;
    }

    @NonNull
    @Override
    public RequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RequestHolder(LayoutInflater.from(context)
                .inflate(R.layout.request_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RequestHolder holder, int position) {
        Shop shop = shops.get(position);
        holder.binding.shopName.setText(shop.getShopName());
        holder.binding.shopLocation.setText(shop.getLocation());
        holder.binding.requestType.setText(shop.getRequestType());

        Calendar requestTime = Calendar.getInstance();
        requestTime.setTimeInMillis(shop.getCreated_at().getTime());

        String requestString =
                requestTime.get(Calendar.HOUR_OF_DAY) + ":" +
                        (requestTime.get(Calendar.MINUTE) > 9 ? requestTime.get(Calendar.MINUTE) :
                                "0" + requestTime.get(Calendar.MINUTE))
                        + " " +
                        requestTime.get(Calendar.DAY_OF_MONTH) + "-"
                        + (requestTime.get(Calendar.MONTH) + 1) + "-"
                        + requestTime.get(Calendar.YEAR);
        holder.binding.requestTimeValue.setText(requestString);

        Calendar responseTime = Calendar.getInstance();
        responseTime.setTimeInMillis(shop.getUpdated_at().getTime());

        if (shop.getOwner() != null)
            holder.binding.ownerName.setText(shop.getOwner().getUserName());

        if (shop.isApproved()) {
            if (shop.getAdmin() != null)
                holder.binding.adminName.setText(shop.getAdmin().getUserName());
            holder.binding.getRoot().setCardBackgroundColor(context.getColor(R.color.approved));
            String approveString =
                    responseTime.get(Calendar.HOUR_OF_DAY) + ":" +
                            (responseTime.get(Calendar.MINUTE) > 9 ? responseTime.get(Calendar.MINUTE) :
                                    "0" + responseTime.get(Calendar.MINUTE))
                            + " " +
                            responseTime.get(Calendar.DAY_OF_MONTH) + "-"
                            + (responseTime.get(Calendar.MONTH) + 1) + "-"
                            + responseTime.get(Calendar.YEAR);
            holder.binding.approvalTimeTxt.setVisibility(View.VISIBLE);
            holder.binding.approvalTimeValue.setVisibility(View.VISIBLE);
            holder.binding.approvalTimeValue.setText(approveString);
            holder.binding.ownerName.setTextColor(context.getColor(R.color.gray500));
            holder.binding.requestTimeValue.setTextColor(context.getColor(R.color.gray500));
            holder.binding.approvalTimeValue.setTextColor(context.getColor(R.color.gray500));
            holder.binding.getRoot().setOnClickListener(v -> {
                Toast.makeText(context, context.getString(R.string.already_approved), Toast.LENGTH_SHORT).show();
            });
        } else {
            holder.binding.getRoot().setCardBackgroundColor(context.getColor(R.color.not_approved));
            holder.binding.getRoot().setOnClickListener(v -> {
                if (listener != null)
                    listener.onClick(shop.getId(), holder.getAdapterPosition());
            });
        }

        setAnimation(holder.itemView);
    }

    private void setAnimation(View viewToAnimate) {

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
        return shops.size();
    }

    public static class RequestHolder extends RecyclerView.ViewHolder {
        RequestItemBinding binding;

        public RequestHolder(@NonNull View itemView) {
            super(itemView);
            binding = RequestItemBinding.bind(itemView);
        }
    }
}

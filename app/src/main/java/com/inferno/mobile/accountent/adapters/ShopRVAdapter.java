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
import com.inferno.mobile.accountent.databinding.ShopeItemBinding;
import com.inferno.mobile.accountent.models.PositionType;
import com.inferno.mobile.accountent.models.Shop;
import com.inferno.mobile.accountent.models.Worker;
import com.inferno.mobile.accountent.utils.ContextUtils;

import java.util.ArrayList;
import java.util.Calendar;

public class ShopRVAdapter extends RecyclerView.Adapter<ShopRVAdapter.ShopHolder> {
    private final ArrayList<Shop> shops;

    private final Context context;
    private AdapterItemListener listener,onLongClickListener;
    private final boolean isAdd;


    public void setShopItemListener(AdapterItemListener listener) {
        this.listener = listener;
    }

    public ShopRVAdapter(ArrayList<Shop> shops, Context context, boolean isAdd) {
        this.shops = shops;
        this.context = context;
        this.isAdd = isAdd;
    }

    @NonNull
    @Override
    public ShopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShopHolder(LayoutInflater.from(context)
                .inflate(R.layout.shope_item, parent, false));
    }

    public void removeManager(int pos) {
        Shop shop = shops.get(pos);
        if (shop.getWorkers() != null && shop.getWorkers().size() > 0) {
            int managerPos = -1;

            for (int i = 0; i < shop.getWorkers().size(); i++) {
                Worker worker = shop.getWorkers().get(i);
                if (worker.getPosition() == PositionType.Manager) {
                    managerPos = i;
                    break;
                }
            }
            if (managerPos != -1) {
                shop.getWorkers().remove(managerPos);
                notifyItemChanged(pos);
            }
        }

    }


    @Override
    public void onBindViewHolder(@NonNull ShopHolder holder, int position) {
        Shop shop = shops.get(position);
        Calendar requestTime = Calendar.getInstance();
        requestTime.setTimeInMillis(shop.getCreated_at().getTime());


        String min = String.valueOf(requestTime.get(Calendar.DAY_OF_MONTH) < 10 ?
                "0" + requestTime.get(Calendar.DAY_OF_MONTH) :
                requestTime.get(Calendar.DAY_OF_MONTH));

        String requestString = requestTime.get(Calendar.HOUR_OF_DAY) + ":" +
                min + " "
                + requestTime.get(Calendar.DAY_OF_MONTH) + "-"
                + (requestTime.get(Calendar.MONTH) + 1) + "-"
                + requestTime.get(Calendar.YEAR);


        holder.binding.requestTimeValue.setText(requestString);
        holder.binding.requestType.setVisibility(View.VISIBLE);
        holder.binding.requestType.setText(shop.getRequestType());

        Calendar responseTime = Calendar.getInstance();
        responseTime.setTimeInMillis(shop.getUpdated_at().getTime());

        if (shop.isApproved()) {
            holder.binding.getRoot().setCardBackgroundColor(context.getColor(R.color.approved));
            holder.binding.getRoot().setOnClickListener(v -> {

                if (checkManager(shop, null)) {
                    if (isAdd)
                        Toast.makeText(context, context.getString(R.string.has_manager),
                                Toast.LENGTH_SHORT).show();
                    else
                        listener.onClick(shop.getId(), holder.getAdapterPosition());
                } else if (listener != null) {
                    if (isAdd)
                        listener.onClick(shop.getId(), holder.getAdapterPosition());
                }
            });
            holder.binding.requestTimeValue.setTextColor(context.getColor(R.color.white));
            holder.binding.approvalTimeValue.setTextColor(context.getColor(R.color.white));
            holder.binding.approvalTimeTxt.setVisibility(View.VISIBLE);
            holder.binding.approvalTimeValue.setVisibility(View.VISIBLE);
            min = String.valueOf(responseTime.get(Calendar.DAY_OF_MONTH) < 10 ?
                    "0" + responseTime.get(Calendar.DAY_OF_MONTH) :
                    responseTime.get(Calendar.DAY_OF_MONTH));

            String approveString = responseTime.get(Calendar.HOUR_OF_DAY) + ":" +
                    min + " " +
                    responseTime.get(Calendar.DAY_OF_MONTH) + "-"
                    + (responseTime.get(Calendar.MONTH) + 1) + "-"
                    + responseTime.get(Calendar.YEAR);
            holder.binding.approvalTimeValue.setText(approveString);
        } else {
            holder.binding.getRoot().setCardBackgroundColor(context.getColor(R.color.not_approved));
            holder.binding.getRoot().setOnClickListener(v ->
                    Toast.makeText(context, context.getString(R.string.not_approved),
                            Toast.LENGTH_LONG).show());
        }
        holder.itemView.setOnLongClickListener(l->{
            if(onLongClickListener!=null)
                onLongClickListener.onClick(shop.getId(),holder.getAdapterPosition());
            return true;
        });
        checkManager(shop, holder);
        holder.binding.shopName.setText(shop.getShopName());
        holder.binding.shopLocation.setText(shop.getLocation());
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
    private boolean checkManager(Shop shop, ShopHolder holder) {
        if (shop.getWorkers() != null && shop.getWorkers().size() > 0) {
            for (Worker worker : shop.getWorkers()) {
                if (worker.getPosition() == PositionType.Manager) {
                    if (holder != null) {
                        holder.binding.requestType.setVisibility(View.VISIBLE);
                        holder.binding.requestType.setText(worker.getWorker().getUserName());
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }


    public void setOnLongClickListener(AdapterItemListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public static class ShopHolder extends RecyclerView.ViewHolder {
        ShopeItemBinding binding;

        public ShopHolder(@NonNull View itemView) {
            super(itemView);
            binding = ShopeItemBinding.bind(itemView);
        }
    }
}

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
import com.inferno.mobile.accountent.databinding.CategoryItemBinding;
import com.inferno.mobile.accountent.models.Category;
import com.inferno.mobile.accountent.utils.ContextUtils;

import java.util.ArrayList;

public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.Holder> {
    private final ArrayList<Category> categories;
    private final Context context;
    private AdapterItemListener listener;

    public void setAdapterItemListener(AdapterItemListener listener) {
        this.listener = listener;
    }

    public CategoryRVAdapter(ArrayList<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context)
                .inflate(R.layout.category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Category category = categories.get(position);
        holder.binding.catItemName.setText(category.getName());
        String currency = context.getString(R.string.currency, String.valueOf(category.getPrice()));
        holder.binding.priceValue.setText(currency);
        holder.binding.shopName.setText(category.getCompany().getName());
        holder.binding.stockCountValue.setText(String.valueOf(category.getCount()));
        holder.binding.getRoot().setOnClickListener(l -> {
            if (listener != null)
                listener.onClick(category.getId(), holder.getAdapterPosition());
        });
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
        return categories.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        CategoryItemBinding binding;

        public Holder(@NonNull View itemView) {
            super(itemView);
            binding = CategoryItemBinding.bind(itemView);
        }
    }
}

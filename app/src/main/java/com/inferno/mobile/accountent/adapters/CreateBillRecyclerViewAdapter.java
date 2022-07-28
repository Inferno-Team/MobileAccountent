package com.inferno.mobile.accountent.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.databinding.CreateBillCategoryItemBinding;
import com.inferno.mobile.accountent.models.Category;

import java.io.Serializable;
import java.util.ArrayList;


public class CreateBillRecyclerViewAdapter extends
        RecyclerView.Adapter<CreateBillRecyclerViewAdapter.CategoryItemHolder>
        implements Serializable {
    private final ArrayList<Category> categories;
    private final ArrayList<Integer> counts;
    private RemoveItemListener<Category> removeItemListener;

    public void setRemoveItemListener(RemoveItemListener<Category> removeItemListener) {
        this.removeItemListener = removeItemListener;
    }

    public void addCategory(Category category, int count) {
        this.categories.add(category);
        this.counts.add(count);
        this.notifyItemInserted(categories.size() - 1);
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public ArrayList<Integer> getCounts() {
        return counts;
    }

    public void setCategories(ArrayList<Category> cats, ArrayList<Integer> count) {
        for (int i = 0; i < cats.size(); i++) {
            addCategory(cats.get(i), count.get(i));
        }
    }

    public CreateBillRecyclerViewAdapter(ArrayList<Category> categories,
                                         ArrayList<Integer> counts) {
        this.categories = categories;
        this.counts = counts;
    }

    public CreateBillRecyclerViewAdapter() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    @NonNull
    @Override
    public CategoryItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryItemHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.create_bill_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemHolder holder, int position) {
        Category category = categories.get(position);
        holder.binding.catItemName.setText(category.getName());
        holder.binding.priceValue.setText(
                holder.itemView.getContext().getString(R.string.currency,
                        String.valueOf(category.getPrice()))
        );
        holder.binding.stockCountValue.setText(String.valueOf(counts.get(position)));
        double totalPrice = counts.get(position) * category.getPrice();
        holder.binding.finalItemPriceValue.setText
                (holder.itemView.getContext().getString(R.string.currency, String.valueOf(totalPrice))
                );
        holder.binding.removeItemBtn.setOnClickListener(e -> {
            if (removeItemListener != null)
                removeItemListener.onRemove(category, holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryItemHolder extends RecyclerView.ViewHolder
    implements Serializable
    {
        public CreateBillCategoryItemBinding binding;

        public CategoryItemHolder(@NonNull View itemView) {
            super(itemView);
            binding = CreateBillCategoryItemBinding.bind(itemView);
        }
    }
}

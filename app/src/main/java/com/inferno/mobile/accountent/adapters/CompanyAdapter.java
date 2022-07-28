package com.inferno.mobile.accountent.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.databinding.CompanyItemBinding;
import com.inferno.mobile.accountent.models.Company;

import java.util.ArrayList;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CompanyHolder> {
    private final Context context;
    private final ArrayList<Company> companies;
    private AdapterItemListener adapterItemListener;

    public CompanyAdapter(Context context, ArrayList<Company> companies) {
        this.context = context;
        this.companies = companies;
    }

    @NonNull
    @Override
    public CompanyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CompanyHolder(LayoutInflater.from(context)
                .inflate(R.layout.company_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyHolder holder, int position) {
        Company company = companies.get(position);
        holder.binding.companyName.setText(company.getName());
        holder.binding.companyCatCount.setText(context.getString(R.string.company_cat_count, company.getCatCount())
        );
        holder.itemView.setOnClickListener(v -> {
            if (adapterItemListener != null)
                adapterItemListener.onClick(company.getId(), holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    public void setAdapterItemListener(AdapterItemListener adapterItemListener) {
        this.adapterItemListener = adapterItemListener;
    }

    public static class CompanyHolder extends RecyclerView.ViewHolder {
        CompanyItemBinding binding;

        public CompanyHolder(@NonNull View itemView) {
            super(itemView);
            binding = CompanyItemBinding.bind(itemView);
        }
    }
}

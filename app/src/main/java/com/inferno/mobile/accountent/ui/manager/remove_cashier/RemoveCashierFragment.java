package com.inferno.mobile.accountent.ui.manager.remove_cashier;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.inferno.mobile.accountent.adapters.AdminRVAdapter;
import com.inferno.mobile.accountent.databinding.RemoveCashierFragmentBinding;
import com.inferno.mobile.accountent.models.User;
import com.inferno.mobile.accountent.models.Worker;
import com.inferno.mobile.accountent.utils.ContextUtils;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RemoveCashierFragment extends Fragment {
    private RemoveCashierFragmentBinding binding;
    private RemoveCashierViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = RemoveCashierFragmentBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(RemoveCashierViewModel.class);
        String token = ContextUtils.getToken(requireContext());
        binding.progressBar.startAnimation();
        viewModel.getAllCashiers(token).observe(requireActivity(),this::onDateArrival);
        return binding.getRoot();
    }

    private void onDateArrival(ArrayList<Worker>cashiers){
        binding.progressBar.stopAnimation();
        new Handler().postDelayed(()->{
            binding.cashierList.setVisibility(View.VISIBLE);
            ArrayList<User>users = convert2user(cashiers);
            AdminRVAdapter adapter = new AdminRVAdapter(requireContext(),users);
            adapter.setRemoveItemListener((user,pos)->{
                users.remove(user);
                adapter.notifyItemRemoved(pos);

            });
            binding.cashierList.setAdapter(adapter);
        },1000);

    }

    private ArrayList<User>convert2user(ArrayList<Worker>workers){
        ArrayList<User>users = new ArrayList<>();
        for(Worker worker:workers)
            users.add(worker.getWorker());
        return users;
    }

}

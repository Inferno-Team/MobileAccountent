package com.inferno.mobile.accountent.ui.customer.show_owner_other_shops;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.inferno.mobile.accountent.adapters.ShowShopsAdapter;
import com.inferno.mobile.accountent.databinding.OtherShopsFragmentBinding;
import com.inferno.mobile.accountent.models.Shop;
import com.inferno.mobile.accountent.models.User;
import com.inferno.mobile.accountent.utils.ContextUtils;
import com.inferno.mobile.billprogressbarlib.BillProgressBar;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OtherShopsFragment extends Fragment {
    private OtherShopsFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = OtherShopsFragmentBinding.inflate(inflater, container, false);
        OtherViewModel viewModel = new ViewModelProvider(requireActivity())
                .get(OtherViewModel.class);
        User owner = (User) requireArguments().getSerializable("owner");
        binding.ownerName.setText(owner.getUserName());
        viewModel.getOtherShops(ContextUtils.getToken(requireContext()), owner.getId())
                .observe(requireActivity(), this::onShops);
        return binding.getRoot();
    }

    private void onShops(ArrayList<Shop> shops) {
        ShowShopsAdapter showShopsAdapter = new ShowShopsAdapter(shops, requireContext());
        binding.progressBar.stopAnimation();
        new Handler().postDelayed(() -> {
            binding.requests.setAdapter(showShopsAdapter);
        }, BillProgressBar.FAST);

    }
}

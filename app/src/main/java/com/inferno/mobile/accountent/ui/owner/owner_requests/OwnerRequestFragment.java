package com.inferno.mobile.accountent.ui.owner.owner_requests;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.adapters.AdapterItemListener;
import com.inferno.mobile.accountent.adapters.ShopRVAdapter;
import com.inferno.mobile.accountent.databinding.OwnerRequestsBinding;
import com.inferno.mobile.accountent.models.Shop;
import com.inferno.mobile.accountent.utils.ContextUtils;
import com.inferno.mobile.billprogressbarlib.BillProgressBar;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OwnerRequestFragment extends Fragment {

    private OwnerRequestsBinding binding;
    private OwnerRequestViewModel viewModel;
    private NavController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = OwnerRequestsBinding.inflate(inflater, container, false);
        binding.progressBar.startAnimation();
        controller = Navigation.findNavController(
                container.getRootView().findViewById(R.id.nav_host_fragment_content_main));
        viewModel = new ViewModelProvider(requireActivity()).get(OwnerRequestViewModel.class);
        viewModel.getOwnerShopRequests(ContextUtils.getToken(requireContext()))
                .observe(requireActivity(), this::onRequests);
        return binding.getRoot();
    }

    private void onRequests(ArrayList<Shop> shops) {
        binding.ownerRequests.setVisibility(View.VISIBLE);
        binding.progressBar.stopAnimation();
        new Handler().postDelayed(() -> {
            ShopRVAdapter adapter = new ShopRVAdapter(shops, requireContext(), true);
            adapter.setShopItemListener((shopId, pos) -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("shop", shops.get(pos));
                controller.navigate(R.id.action_ownerRequestFragment_to_addShopRequestFragment, bundle);
            });
            adapter.setOnLongClickListener((id, pos) -> {
                // display a dialog to be sure to remove this request [add|edit]
                new AlertDialog.Builder(requireContext())
                        .setTitle("remove request") // it will be from R.strings
                        .setMessage("do you really want to remove this request ? ") // again this will be from R.strings
                        .setPositiveButton(getString(R.string.yes), (dialog, which) -> {
                            // send remove request to server
                            String token = ContextUtils.getToken(requireContext());
                            viewModel.removeRequest(token, id)
                                    .observe(requireActivity(), response -> {
                                        //remove this request from view at [pos]
                                        shops.remove(pos);
                                        adapter.notifyItemRemoved(pos);
                                        dialog.dismiss();
                                    });
                        })
                        .setNegativeButton(getString(R.string.no), (dialog, which) -> dialog.dismiss())
                        .show();
            });

            binding.ownerRequests.setAdapter(adapter);
        }, BillProgressBar.FAST);

    }

}

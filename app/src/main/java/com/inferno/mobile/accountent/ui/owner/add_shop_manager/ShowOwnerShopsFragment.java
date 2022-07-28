package com.inferno.mobile.accountent.ui.owner.add_shop_manager;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.adapters.ShopRVAdapter;
import com.inferno.mobile.accountent.databinding.ShowOwnerShopsBinding;
import com.inferno.mobile.accountent.models.RemoveAdminResponse;
import com.inferno.mobile.accountent.models.Shop;
import com.inferno.mobile.accountent.utils.ContextUtils;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ShowOwnerShopsFragment extends Fragment {


    private ShowOwnerShopsBinding binding;
    private AddManagerViewModel viewModel;
    private NavController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = ShowOwnerShopsBinding.inflate(inflater, container, false);
        binding.ownerListTxt.setVisibility(View.GONE);
        binding.chooseMessage.setVisibility(View.GONE);
        binding.shopsList.setVisibility(View.GONE);
        binding.progressBar.startAnimation();
        controller = Navigation.findNavController(
                container.getRootView().findViewById(R.id.nav_host_fragment_content_main));
        viewModel = new ViewModelProvider(requireActivity()).get(AddManagerViewModel.class);
        viewModel.showOwnerShops("Bearer " + ContextUtils.getToken(requireContext()))
                .observe(requireActivity(), this::onShopResponse);
        return binding.getRoot();
    }

    private void onShopResponse(ArrayList<Shop> shops) {

        binding.progressBar.stopAnimation();
        new Handler().postDelayed(() -> {
            binding.ownerListTxt.setVisibility(View.VISIBLE);
            binding.chooseMessage.setVisibility(View.VISIBLE);
            binding.shopsList.setVisibility(View.VISIBLE);
            boolean isAdd = requireArguments().getBoolean("add");
            if (shops == null) return;
            ShopRVAdapter adapter = new ShopRVAdapter(shops, requireContext(), isAdd);
            adapter.setShopItemListener((id, pos) -> {
                Bundle bundle = new Bundle();
                bundle.putInt("shopId", id);

                if (isAdd)
                    controller.navigate(R.id.action_showOwnerShopsFragment_to_addShopManager, bundle);
                else {
                    new AlertDialog.Builder(requireActivity())
                            .setMessage(getString(R.string.owner_remove_manager_title))
                            .setTitle(getString(R.string.owner_remove_manager_msg))
                            .setPositiveButton("Yes", (dialog, btn) -> {
                                String token = ContextUtils.getToken(requireContext());
                                viewModel.removeManager(token, id)
                                        .observe(requireActivity(), (response) -> {
                                            Toast.makeText(requireContext(),
                                                    response.getMessage(),
                                                    Toast.LENGTH_SHORT).show();
                                            if (response.getCode() == 200) {
                                                adapter.removeManager(pos);
                                            }

                                        });
                                dialog.dismiss();
                            })
                            .setNegativeButton("No", (dialog, btn) -> dialog.dismiss())
                            .show();
                }
            });
            binding.shopsList.setAdapter(adapter);
        }, 1000);

    }


}

package com.inferno.mobile.accountent.ui.owner.add_shop_request;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.databinding.ShopAddRequestOwnerBinding;
import com.inferno.mobile.accountent.models.AddShopRequestResponse;
import com.inferno.mobile.accountent.models.Shop;
import com.inferno.mobile.accountent.utils.ContextUtils;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddShopRequestFragment extends Fragment {

    private ShopAddRequestOwnerBinding binding;
    private RequestViewModel viewModel;
    private NavController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        controller = Navigation.findNavController(
                container.getRootView().findViewById(R.id.nav_host_fragment_content_main));
        Shop shop = (Shop) requireArguments().getSerializable("shop");

        binding = ShopAddRequestOwnerBinding.inflate(inflater, container, false);
        if (shop != null) {
            binding.shopNameField.setText(shop.getShopName());
            binding.locationField.setText(shop.getLocation());
            binding.text.setText(getString(R.string.edit_shop_request));
        }
        viewModel = new ViewModelProvider(requireActivity()).get(RequestViewModel.class);
        binding.addShopRequest.setOnClickListener(v -> {
            String shopName = binding.shopNameField.getEditableText().toString();
            String location = binding.locationField.getEditableText().toString();
            String token = ContextUtils.getToken(requireContext());
            if (shop != null) {
                viewModel.editRequest(token, shopName, location, shop.getShopeId())
                        .observe(requireActivity(), this::onRequestResponse);
            } else viewModel.addRequest(token, shopName, location).observe(requireActivity(),
                    this::onRequestResponse);

        });
        return binding.getRoot();
    }

    private void onRequestResponse(AddShopRequestResponse response) {
        if (response == null) return;
        Toast.makeText(requireContext(), response.getMsg(), Toast.LENGTH_LONG).show();
        if (response.getCode() == 200)
            controller.navigateUp();
    }
}

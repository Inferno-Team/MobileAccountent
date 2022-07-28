package com.inferno.mobile.accountent.ui.owner.add_shop_manager.add_manager;

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
import com.inferno.mobile.accountent.databinding.AddShopManagerBinding;
import com.inferno.mobile.accountent.models.AddManagerResponse;
import com.inferno.mobile.accountent.ui.owner.add_shop_manager.AddManagerViewModel;
import com.inferno.mobile.accountent.utils.ContextUtils;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddShopManager extends Fragment {
    private AddShopManagerBinding binding;
    private AddManagerViewModel viewModel;
    private NavController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AddShopManagerBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(AddManagerViewModel.class);
        controller = Navigation.findNavController(
                container.getRootView().findViewById(R.id.nav_host_fragment_content_main));
        int shopId = requireArguments().getInt("shopId");
        binding.addShopManagerButton.setOnClickListener(v -> {
            String token = ContextUtils.getToken(requireContext());
            String managerName = binding.usernameField.getEditableText().toString();
            String password = binding.passwordField.getEditableText().toString();
            String email = binding.emailField.getEditableText().toString();
            String phone = binding.phoneField.getEditableText().toString();
            viewModel.addManager(token, managerName, email, password, phone, shopId)
                    .observe(requireActivity(), this::onManagerAdded);
        });
        return binding.getRoot();
    }

    private void onManagerAdded(AddManagerResponse response) {
        Toast.makeText(requireContext(), response.getMessage(), Toast.LENGTH_LONG).show();
        if (response.getCode() == 200)
            controller.navigateUp();
    }
}

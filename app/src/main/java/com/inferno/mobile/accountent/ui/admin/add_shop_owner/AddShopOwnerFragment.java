package com.inferno.mobile.accountent.ui.admin.add_shop_owner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.databinding.AddShopOwnerFragmentBinding;
import com.inferno.mobile.accountent.models.AddAdminResponse;
import com.inferno.mobile.accountent.utils.ContextUtils;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddShopOwnerFragment extends Fragment {
    private AddShopOwnerFragmentBinding binding;
    private AddOwnerViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AddShopOwnerFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(AddOwnerViewModel.class);
        binding.addAdminButton.setOnClickListener(v -> {
            String token = ContextUtils.getToken(requireContext());
            String userName = binding.usernameField.getEditableText().toString();
            String email = binding.emailField.getEditableText().toString();
            String password = binding.passwordField.getEditableText().toString();
            String phone = binding.phoneField.getEditableText().toString();
            viewModel.addOwner(token, userName, password, email, phone)
                    .observe(requireActivity(), this::onAddOwner);
        });
        return binding.getRoot();
    }

    private void onAddOwner(AddAdminResponse response) {
        if (response == null) return;
        Toast.makeText(requireContext(), response.getMessage(),
                Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main)
                .navigateUp();
    }
}

package com.inferno.mobile.accountent.ui.admin.add_new_admin;

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
import com.inferno.mobile.accountent.databinding.AddAdminFragmentBinding;
import com.inferno.mobile.accountent.models.AddAdminResponse;
import com.inferno.mobile.accountent.utils.ContextUtils;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddAdminFragment extends Fragment {
    private AddAdminFragmentBinding binding;
    private NavController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AddAdminFragmentBinding.inflate(inflater, container, false);
        AddAdminViewModel viewModel = new ViewModelProvider(requireActivity())
                .get(AddAdminViewModel.class);
        controller = Navigation.findNavController(
                container.getRootView().findViewById(R.id.nav_host_fragment_content_main));
        binding.addAdminButton.setOnClickListener(v -> {
            String username = binding.usernameField.getEditableText().toString();
            String password = binding.passwordField.getEditableText().toString();
            String email = binding.emailField.getEditableText().toString();
            String phone = binding.phoneField.getEditableText().toString();
            String token = ContextUtils.getToken(requireContext());
            viewModel.addNewAdmin(token,username, email, password, phone)
                    .observe(requireActivity(), this::addNewAdminResponse);
        });
        return binding.getRoot();
    }

    private void addNewAdminResponse(AddAdminResponse response) {
        if (response == null) return;
        Toast.makeText(requireContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
        if (response.getStatusCode() != 200)
            return;
        controller.navigateUp();

    }
}

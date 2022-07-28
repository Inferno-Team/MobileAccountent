package com.inferno.mobile.accountent.ui.manager.add_cashier;

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
import com.inferno.mobile.accountent.databinding.AddCashierFragmentBinding;
import com.inferno.mobile.accountent.models.AddCashierResponse;
import com.inferno.mobile.accountent.utils.ContextUtils;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddCashierFragment extends Fragment {
    private AddCashierFragmentBinding binding;
    private AddCashierViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AddCashierFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(AddCashierViewModel.class);

        binding.addCashierButton.setOnClickListener(v -> {
            String token = ContextUtils.getToken(requireContext());
            String userName = binding.usernameField.getEditableText().toString();
            String email = binding.emailField.getEditableText().toString();
            String password = binding.passwordField.getEditableText().toString();
            String phone = binding.phoneField.getEditableText().toString();
            viewModel.addCashier(token, userName, email, password, phone)
                    .observe(requireActivity(), this::onAddCashier);
        });

        return binding.getRoot();
    }

    private void onAddCashier(AddCashierResponse res) {
        if (res == null) return;
        Toast.makeText(requireContext(), res.getMessage(), Toast.LENGTH_SHORT).show();
        Navigation.findNavController(
                binding.getRoot().getRootView().
                        findViewById(R.id.nav_host_fragment_content_main)).navigateUp();
    }
}

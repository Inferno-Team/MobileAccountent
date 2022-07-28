package com.inferno.mobile.accountent.ui.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.inferno.mobile.accountent.activities.SplashActivity;
import com.inferno.mobile.accountent.databinding.SignUpFragementBinding;
import com.inferno.mobile.accountent.models.LoginResponse;
import com.inferno.mobile.accountent.utils.ContextUtils;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignUpFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        SignUpFragementBinding binding = SignUpFragementBinding.
                inflate(inflater, container, false);
        SignUpViewModel viewModel = new ViewModelProvider(requireActivity())
                .get(SignUpViewModel.class);
        binding.signUpButton.setOnClickListener(v -> {
            String name = binding.usernameField.getEditableText().toString();
            String email = binding.emailField.getEditableText().toString();
            String phone = binding.phoneField.getEditableText().toString();
            String password = binding.passwordField.getEditableText().toString();
            viewModel.signUp(email, password, name, phone)
                    .observe(requireActivity(), this::onSignUpResponse);
        });

        return binding.getRoot();
    }

    private void onSignUpResponse(LoginResponse response) {
        Toast.makeText(requireContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
        if (response.getMessage().equals("good")) {
            ContextUtils.saveUser(requireContext(), response.getToken(), response.getType());
            Intent intent = new Intent(requireActivity(), SplashActivity.class);
            requireActivity().startActivity(intent);
            requireActivity().finish();
        }
    }
}

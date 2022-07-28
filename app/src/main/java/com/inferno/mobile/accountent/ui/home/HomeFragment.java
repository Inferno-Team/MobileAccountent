package com.inferno.mobile.accountent.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.databinding.FragmentHomeBinding;
import com.inferno.mobile.accountent.models.LoginResponse;
import com.inferno.mobile.accountent.utils.ContextUtils;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class HomeFragment extends Fragment {


    private HomeViewModel viewModel;
    private FragmentHomeBinding
            binding;
    private NavController controller;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        controller = Navigation.findNavController(
                container.getRootView().findViewById(R.id.nav_host_fragment_content_main));
        binding.loginButton.setOnClickListener(v -> {
            String email = binding.email.getEditableText().toString();
            String password = binding.password.getEditableText().toString();
            viewModel.login(email, password).observe(getViewLifecycleOwner(), this::loginObserver);
        });
        if (ContextUtils.isUserExists(requireContext())) {
            switch (ContextUtils.getUserType(requireContext())) {
                case Admin:
                    controller.navigate(R.id.action_nav_home_to_adminDashboardFragment);
                    break;
                case Owner:
                    controller.navigate(R.id.action_nav_home_to_ownerDashboardFragment);
                    break;
                case Manager:
                    controller.navigate(R.id.action_nav_home_to_managerDashboardFragment);
                    break;
                case Cashier:
                    controller.navigate(R.id.action_nav_home_to_cashierDashboardFragment);
                    break;
                case Customer:
                    controller.navigate(R.id.action_nav_home_to_customerDashboardFragment);
                    break;
            }
        }
        binding.signUpButton.setOnClickListener(v -> {
            controller.navigate(R.id.action_nav_home_to_signUpFragment);
        });
        return binding.getRoot();
    }

    private void loginObserver(LoginResponse response) {
        if (response == null) return;
        Log.d("LogInResponse", response.getMessage());
        Toast.makeText(requireContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
        if (response.getMessage().equals("good")) {
            ContextUtils.saveUser(requireContext(), response.getToken(), response.getType());
            requireActivity().recreate();
        }
    }


}
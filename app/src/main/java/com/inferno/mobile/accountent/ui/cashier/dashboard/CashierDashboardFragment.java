package com.inferno.mobile.accountent.ui.cashier.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.databinding.CashierDashboardBinding;
import com.inferno.mobile.accountent.models.User;
import com.inferno.mobile.accountent.utils.ContextUtils;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CashierDashboardFragment extends Fragment {
    private CashierDashboardBinding binding;
    private CashierViewModel viewModel;
    private NavController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = CashierDashboardBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(CashierViewModel.class);
        viewModel.getUser(ContextUtils.getToken(requireContext()))
                .observe(requireActivity(), this::onUser);
        controller = Navigation.findNavController(
                container.getRootView().findViewById(R.id.nav_host_fragment_content_main));
        binding.child1.setOnClickListener(v -> {
            controller.navigate(R.id.action_cashierDashboardFragment_to_showCategoriesFragment2);
        });
        binding.child2.setOnClickListener(v -> {
            controller.navigate(R.id.action_cashierDashboardFragment_to_createBillFragment);
        });
        setAnimation(ContextUtils.getLanguage(requireContext()));

        return binding.getRoot();
    }

    private void onUser(User user) {
        if (user == null) return;
        String name = getString(R.string.hello_cashier, user.getUserName());
        binding.userName.setText(name);
    }

    private void setAnimation(String lang) {
        binding.rootImage.animate().scaleX(2F).scaleY(2F)
                .setDuration(500);
        binding.userName.animate()
                .translationY((float) (binding.userName.getHeight() / 2))
                .setDuration(1000);
        int delay = 200;
        int duration = 1000;
        float pos = 0F;

        if (lang.equals("Arabic")) {

            binding.bottomCir.setTranslationX(200);
            binding.topCir.setTranslationX(-200);

            binding.child1.setTranslationX(-200);
            binding.child2.setTranslationX(200);
        }

        binding.bottomCir.animate().translationX(pos).setDuration(duration);
        binding.topCir.animate().translationX(pos).setDuration(duration);

        binding.child1.animate().translationX(pos).setDuration(duration)
                .setStartDelay(delay);
        binding.child2.animate().translationX(pos).setDuration((long) (duration*1.25))
                .setStartDelay(delay);

        binding.cardBackground.animate().alpha(1F).setDuration(duration*2)
                .setStartDelay(delay);


    }
}

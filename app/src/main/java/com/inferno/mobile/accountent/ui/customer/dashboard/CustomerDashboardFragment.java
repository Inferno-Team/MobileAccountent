package com.inferno.mobile.accountent.ui.customer.dashboard;

import android.os.Bundle;
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
import com.inferno.mobile.accountent.databinding.CustomerDashboardFragmentBinding;
import com.inferno.mobile.accountent.models.User;
import com.inferno.mobile.accountent.ui.customer.link_bill.LinkBillFragment;
import com.inferno.mobile.accountent.utils.ContextUtils;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CustomerDashboardFragment extends Fragment {
    private CustomerDashboardFragmentBinding binding;
    private CustomerViewModel viewModel;
    private NavController controller;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = CustomerDashboardFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(CustomerViewModel.class);
        controller = Navigation.findNavController(
                container.getRootView().findViewById(R.id.nav_host_fragment_content_main));

        String token = ContextUtils.getToken(requireContext());
        viewModel.getUser(token).observe(requireActivity(), this::onUser);


        binding.child1.setOnClickListener(v -> {
            controller.navigate(R.id.action_customerDashboardFragment_to_myBillsFragment);
        });
        binding.child2.setOnClickListener(v -> {
            LinkBillFragment.IS_FIRST = true;
            controller.navigate(R.id.action_customerDashboardFragment_to_linkBillFragment);
        });
        setAnimation(ContextUtils.getLanguage(requireContext()));
        return binding.getRoot();
    }

    private void onUser(User user) {
        if (user == null) return;
        String name = getString(R.string.hello_customer, user.getUserName());
        binding.userName.setText(name);
        binding.userName.animate()
                .translationY((float) (binding.userName.getHeight() / 2))
                .setDuration(1000);
    }

    private void setAnimation(String lang) {
        binding.rootImage.animate().scaleX(2F).scaleY(2F)
                .setDuration(500);

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
        binding.child2.animate().translationX(pos).setDuration(duration)
                .setStartDelay(delay);

        binding.cardBackground.animate().alpha(1F).setDuration(duration)
                .setStartDelay(delay);


    }

}

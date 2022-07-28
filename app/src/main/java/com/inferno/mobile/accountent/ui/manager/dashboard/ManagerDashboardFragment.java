package com.inferno.mobile.accountent.ui.manager.dashboard;

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
import com.inferno.mobile.accountent.databinding.ManagerDashboardBinding;
import com.inferno.mobile.accountent.models.Shop;
import com.inferno.mobile.accountent.models.User;
import com.inferno.mobile.accountent.utils.ContextUtils;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ManagerDashboardFragment extends Fragment {
    private ManagerDashboardViewModel viewModel;
    private ManagerDashboardBinding binding;
    private NavController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ManagerDashboardBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(ManagerDashboardViewModel.class);
        viewModel.getUser(ContextUtils.getToken(requireContext()))
                .observe(requireActivity(),this::onUser);
        controller = Navigation.findNavController(
                container.getRootView().findViewById(R.id.nav_host_fragment_content_main));
        binding.child1.setOnClickListener(v -> {
            controller.navigate(R.id.action_managerDashboardFragment_to_addCashierFragment);
        });
        binding.child2.setOnClickListener(v -> {
            controller.navigate(R.id.action_managerDashboardFragment_to_removeCashierFragment);
        });
        binding.child3.setOnClickListener(v -> {
            controller.navigate(R.id.action_managerDashboardFragment_to_showCompanyFragment);
        });


        binding.child4.setOnClickListener(v->{
            Bundle bundle = new Bundle();
            bundle.putInt("comp_id", -1);
            controller.navigate(R.id.action_managerDashboardFragment_to_showCategoriesFragment,bundle);
        });
        setAnimation(ContextUtils.getLanguage(requireContext()));
        return binding.getRoot();
    }

    private void onUser(User user) {
        if (user == null)return;
        String name = getString(R.string.hello_manager,user.getUserName());
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
            binding.child3.setTranslationX(-200);
            binding.child4.setTranslationX(200);
        }

        binding.bottomCir.animate().translationX(pos).setDuration(duration);
        binding.topCir.animate().translationX(pos).setDuration(duration);

        binding.child1.animate().translationX(pos).setDuration(duration)
                .setStartDelay(delay);
        binding.child2.animate().translationX(pos).setDuration((long) (duration*1.25))
                .setStartDelay(delay);
        binding.child3.animate().translationX(pos).setDuration((long) (duration*1.5))
                .setStartDelay(delay);
        binding.child4.animate().translationX(pos).setDuration((long) (duration*1.75))
                .setStartDelay(delay);
        binding.cardBackground.animate().alpha(1F).setDuration(duration*2)
                .setStartDelay(delay);


    }

}

package com.inferno.mobile.accountent.ui.owner.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.databinding.OwnerDashBoardBinding;
import com.inferno.mobile.accountent.utils.ContextUtils;

public class OwnerDashboardFragment extends Fragment {
    private OwnerDashBoardBinding binding;
    private NavController controller;
    private DashboardViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = OwnerDashBoardBinding.inflate(inflater, container, false);
        controller = Navigation.findNavController(
                container.getRootView().findViewById(R.id.nav_host_fragment_content_main));
        viewModel = new ViewModelProvider(requireActivity())
                .get(DashboardViewModel.class);
        viewModel.getUser(ContextUtils.getToken(requireContext()))
                .observe(requireActivity(), user -> {
                    if (user == null) return;
                    //user_name
                    String _name = getString(R.string.hello_owner, user.getUserName());
                    binding.userName.setText(_name);
                    binding.userName.animate()
                            .translationY((float) (binding.userName.getHeight() / 2))
                            .setDuration(1000);
                });
        Bundle bundle = new Bundle();
        binding.child2.setOnClickListener(v -> {
            controller.navigate(R.id.action_ownerDashboardFragment_to_addShopRequestFragment);
        });
        binding.child1.setOnClickListener(v -> {
            bundle.putBoolean("add", true);
            controller.navigate(R.id.action_ownerDashboardFragment_to_addShopManagerFragment, bundle);
        });
        binding.child3.setOnClickListener(v -> {
            bundle.putBoolean("add", false);
            controller.navigate(R.id.action_ownerDashboardFragment_to_addShopManagerFragment, bundle);
        });

        binding.child4.setOnClickListener(v -> {
            controller.navigate(R.id.action_ownerDashboardFragment_to_ownerRequestFragment);
        });

        setAnimation(ContextUtils.getLanguage(requireContext()));


        return binding.getRoot();
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

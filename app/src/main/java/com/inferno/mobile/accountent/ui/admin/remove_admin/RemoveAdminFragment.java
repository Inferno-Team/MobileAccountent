package com.inferno.mobile.accountent.ui.admin.remove_admin;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.inferno.mobile.accountent.adapters.AdminRVAdapter;
import com.inferno.mobile.accountent.databinding.RemoveFragmentBinding;
import com.inferno.mobile.accountent.models.RemoveAdminResponse;
import com.inferno.mobile.accountent.models.User;
import com.inferno.mobile.accountent.models.UserType;
import com.inferno.mobile.accountent.utils.ContextUtils;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RemoveAdminFragment extends Fragment {

    private RemoveFragmentBinding binding;
    private RemoveViewModel viewModel;
    private ArrayList<User> users;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.users = (ArrayList<User>) savedInstanceState.getSerializable("users");
        }
        binding = RemoveFragmentBinding.inflate(inflater, container, false);
        binding.progressBar.startAnimation();
        viewModel = new ViewModelProvider(requireActivity()).get(RemoveViewModel.class);
        if (users == null)
            viewModel.getAllUsers(ContextUtils.getToken(requireContext()))
                    .observe(requireActivity(), _users -> {
                        this.users = _users;
                        getAllAdmin();
                    });
        else getAllAdmin();
        return binding.getRoot();
    }

    private void getAllAdmin() {
        binding.progressBar.stopAnimation();

        new Handler().postDelayed(() -> {
            binding.adminRv.setVisibility(View.VISIBLE);
            AdminRVAdapter adapter = new AdminRVAdapter(requireContext(), users);
            adapter.setRemoveItemListener((user, pos) -> {
                viewModel.removeAdmin(
                        ContextUtils.getToken(requireContext()),
                        user.getId()
                ).observe(requireActivity(), removeAdminResponse -> {
                    if (removeAdminResponse == null) {
                        Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (removeAdminResponse.getCode() == 200) {
                        users.remove(user);
                        adapter.notifyItemRemoved(pos);
                    }
                    Toast.makeText(requireContext(),
                            removeAdminResponse.getMessage(), Toast.LENGTH_SHORT).show();
                });
            });
            binding.adminRv.setAdapter(adapter);
        }, 1000);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("users", users);
    }
}

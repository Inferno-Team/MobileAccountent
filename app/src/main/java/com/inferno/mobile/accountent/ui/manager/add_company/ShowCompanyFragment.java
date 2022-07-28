package com.inferno.mobile.accountent.ui.manager.add_company;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.adapters.CompanyAdapter;
import com.inferno.mobile.accountent.databinding.AddCompanyDialogBinding;
import com.inferno.mobile.accountent.databinding.ShowComaniesFragmentBinding;
import com.inferno.mobile.accountent.models.Bill;
import com.inferno.mobile.accountent.models.Company;
import com.inferno.mobile.accountent.utils.ContextUtils;
import com.inferno.mobile.billprogressbarlib.BillProgressBar;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ShowCompanyFragment extends Fragment {
    private ShowComaniesFragmentBinding binding;
    private CompanyViewModel viewModel;
    private NavController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = ShowComaniesFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity())
                .get(CompanyViewModel.class);
        controller = Navigation.findNavController(
                container.getRootView().findViewById(R.id.nav_host_fragment_content_main));
        viewModel.getAllCompanies(ContextUtils.getToken(requireContext()))
                .observe(requireActivity(), this::onCompanies);
        binding.progressBar.startAnimation();

        return binding.getRoot();
    }


    private void onCompanies(ArrayList<Company> companies) {
        binding.progressBar.stopAnimation();
        new Handler().postDelayed(() -> {
            binding.requests.setVisibility(View.VISIBLE);
            binding.addCompany.setVisibility(View.VISIBLE);
            CompanyAdapter adapter = new CompanyAdapter(requireContext(), companies);
            binding.requests.setAdapter(adapter);
            binding.addCompany.setOnClickListener(v -> {
                BottomSheetDialog dialog = new BottomSheetDialog(requireActivity());
                AddCompanyDialogBinding dialogBinding =
                        AddCompanyDialogBinding.inflate(getLayoutInflater());
                dialog.setContentView(dialogBinding.getRoot());
                dialog.show();
                dialogBinding.addItemButton.setOnClickListener(vv -> {
                    String token = ContextUtils.getToken(requireContext());
                    String name = dialogBinding.countField.getEditableText().toString();
                    viewModel.addCompany(token, name)
                            .observe(requireActivity(), response -> {
                                if (response.getCode() == 200) {
                                    companies.add(response.getCompany());
                                    adapter.notifyItemInserted(companies.size());
                                    dialog.dismiss();
                                }
                            });
                });


            });
            adapter.setAdapterItemListener((id, pos) -> {
                Bundle bundle = new Bundle();
                bundle.putInt("comp_id", id);
                controller.navigate(R.id.action_showCompanyFragment_to_showCategoriesFragment, bundle);
            });


        }, BillProgressBar.SLOW);
    }
}

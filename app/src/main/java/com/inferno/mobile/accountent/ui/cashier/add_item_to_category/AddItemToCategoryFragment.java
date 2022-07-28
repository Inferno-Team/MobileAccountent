package com.inferno.mobile.accountent.ui.cashier.add_item_to_category;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.databinding.AddItemToCategoryBinding;
import com.inferno.mobile.accountent.models.AddItemResponse;
import com.inferno.mobile.accountent.utils.ContextUtils;

import java.util.Calendar;
import java.util.Date;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddItemToCategoryFragment extends Fragment {
    private AddCategoryItemViewModel viewModel;
    private AddItemToCategoryBinding binding;
    private Pair<Long, Long> dates = new Pair<>(0L, 0L);
    MaterialDatePicker<Pair<Long, Long>> picker;
    private NavController controller;
    private String barcode = "";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller.getCurrentBackStackEntry().getSavedStateHandle()
                .getLiveData("barcode").observe(getViewLifecycleOwner(),
                result -> {
                    if (result != null) {
                        barcode = result.toString();
                        binding.barcodeField.setText(barcode);
                    }
                });
        if (dates.first != 0L) {
            setDate();
        }

        picker = MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText(getString(R.string.choose_expiration_date))
                .setSelection(new Pair<>(
                        MaterialDatePicker.thisMonthInUtcMilliseconds(),
                        MaterialDatePicker.todayInUtcMilliseconds()
                )).build();
        picker.addOnPositiveButtonClickListener(selection -> {
            dates = selection;
            setDate();
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AddItemToCategoryBinding.inflate(inflater, container, false);
        controller = Navigation.findNavController(
                container.getRootView().findViewById(R.id.nav_host_fragment_content_main));
        viewModel = new ViewModelProvider(requireActivity()).get(AddCategoryItemViewModel.class);
        binding.expireDate.setOnClickListener(v -> {
            picker.show(getParentFragmentManager(), "AddItemFragment");
        });
        binding.barcodeOpener.setOnClickListener(v -> {
//            controller.navigate(R.id.action_addItemToCategoryFragment_to_itemScannerFragment);
        });
        binding.addItemButton.setOnClickListener(v -> {
            String token = ContextUtils.getToken(requireContext());
            int catId = requireArguments().getInt("catId");
            String barcode = binding.barcodeField.getEditableText().toString();

            viewModel.addItem(token, catId, barcode, dates.first, dates.second)
                    .observe(requireActivity(), this::onItemAdded);
        });
        return binding.getRoot();
    }

    private void onItemAdded(AddItemResponse response) {
        if (response == null)
            return;
        Toast.makeText(requireContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
        binding.barcodeField.setText("");
    }


    private void setDate() {
        Calendar first = Calendar.getInstance();
        first.setTimeInMillis(dates.first);
        Calendar second = Calendar.getInstance();
        second.setTimeInMillis(dates.second);
        String firstDate = first.get(Calendar.DAY_OF_MONTH) + "-" +
                (first.get(Calendar.MONTH) + 1) + "-" +
                first.get(Calendar.YEAR);

        binding.firstDate.setText(firstDate);
        binding.firstDate.setVisibility(View.VISIBLE);

        String secondDate = second.get(Calendar.DAY_OF_MONTH) + "-" +
                (second.get(Calendar.MONTH) + 1) + "-" +
                second.get(Calendar.YEAR);
        binding.secondDate.setText(secondDate);
        binding.secondDate.setVisibility(View.VISIBLE);

    }

}

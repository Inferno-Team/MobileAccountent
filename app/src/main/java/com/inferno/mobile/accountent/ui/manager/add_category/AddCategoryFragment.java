package com.inferno.mobile.accountent.ui.manager.add_category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.databinding.AddCategoryFragmentBinding;
import com.inferno.mobile.accountent.models.AddCategoryResponse;
import com.inferno.mobile.accountent.models.Company;
import com.inferno.mobile.accountent.utils.ContextUtils;

import java.util.ArrayList;
import java.util.Calendar;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddCategoryFragment extends Fragment {
    private AddCategoryFragmentBinding binding;
    private AddCategoryViewModel viewModel;
    private Company selectedCompany = null;
    private ArrayList<Company> companies;

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
        companies = new ArrayList<>();
        binding = AddCategoryFragmentBinding.inflate(inflater, container, false);
        controller = Navigation.findNavController(
                container.getRootView().findViewById(R.id.nav_host_fragment_content_main));
        binding.expireDate.setOnClickListener(v -> {
            picker.show(getParentFragmentManager(), "AddItemFragment");
        });
        binding.barcodeOpener.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putBoolean("is_link", false);
            controller.navigate(R.id.action_addCategoryFragment_to_itemScannerFragment, bundle);
        });
        viewModel = new ViewModelProvider(requireActivity()).get(AddCategoryViewModel.class);
        viewModel.getAllCompanies(ContextUtils.getToken(requireContext()))
                .observe(requireActivity(), companies -> {
                    this.companies = companies;
                    ArrayList<String> list = new ArrayList<>();
                    for (Company company : companies)
                        list.add(company.getName());

                    ArrayAdapter<String> adapter =
                            new ArrayAdapter<>(
                                    requireActivity(),
                                    R.layout.dropdown_menu_popup_item, list);
                    binding.companyName.setAdapter(adapter);
                });

        binding.companyName.setOnItemClickListener((parent, view, position, id) -> {
            selectedCompany = companies.get(position);
        });
        binding.addCategory.setOnClickListener(v -> {
            String priceString = binding.priceField.getEditableText().toString();
            String stockCountString = binding.stockCountValue.getEditableText().toString();
            String categoryName = binding.catItemName.getEditableText().toString();

            if (selectedCompany == null
                    || priceString.equals("")
                    || stockCountString.equals("")
                    || barcode.equals("")
                    || dates == null
                    || dates.first == 0L
                    || dates.second == 0L
                    || categoryName.equals("")
            ) {
                Toast.makeText(requireContext(), getString(R.string.no_selected_company),
                        Toast.LENGTH_SHORT).show();
                return;
            }


            String token = ContextUtils.getToken(requireContext());


            double price = Double.parseDouble(priceString);
            int companyId = selectedCompany.getId();
            int stockCount = Integer.parseInt(stockCountString);
            String languageName = ContextUtils.getLanguage(requireContext());
            String lang = languageName.equals("English") ? "en" : "ar";
            viewModel.addCategory(token, companyId, categoryName, price,
                    stockCount, dates.first, dates.second, barcode, lang)
                    .observe(requireActivity(), this::onAddCategory);
        });
        return binding.getRoot();
    }

    private void onAddCategory(AddCategoryResponse response) {
        if (response == null) return;
        Toast.makeText(requireContext(), response.getMsg(), Toast.LENGTH_SHORT).show();
        controller.navigateUp();

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

package com.inferno.mobile.accountent.ui.cashier.create_bill;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.adapters.CreateBillRecyclerViewAdapter;
import com.inferno.mobile.accountent.databinding.CreateBillFragmentBinding;
import com.inferno.mobile.accountent.models.Category;
import com.inferno.mobile.accountent.models.CategoryItemResponse;
import com.inferno.mobile.accountent.models.MessageResponse;
import com.inferno.mobile.accountent.models.ReceiptItem;
import com.inferno.mobile.accountent.utils.ContextUtils;

import java.io.Serializable;
import java.util.ArrayList;

public class CreateBillFragment extends Fragment {
    private CreateBillFragmentBinding binding;
    private NavController controller;
    private CreateBillViewModel viewModel;
    CreateBillRecyclerViewAdapter adapter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String token = ContextUtils.getToken(requireContext());
        System.out.println(token);
        if (savedInstanceState != null) {
            ArrayList<Category> categories = (ArrayList<Category>)
                    savedInstanceState.getSerializable("cats");
            ArrayList<Integer> counts = (ArrayList<Integer>)
                    savedInstanceState.getSerializable("counts");
            if (adapter == null) {
                adapter = new CreateBillRecyclerViewAdapter(categories, counts);
            } else {
                adapter.setCategories(categories, counts);
            }
            binding.selectedItems.setAdapter(adapter);
        }
        adapter.setRemoveItemListener((category, index) -> {

            String categoryName = category.getBarcode();
            int count = adapter.getCounts().get(index);
            viewModel.removeItemFromBill(token, categoryName, count)
                    .observe(requireActivity(), msg -> {
                        if (msg.getCode() == 200) {
                            adapter.getCounts().remove(index);
                            adapter.getCategories().remove(index);
                            adapter.notifyItemRemoved(index);
                        } else Toast.makeText(requireContext(), msg.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    });
        });
        SavedStateHandle savedStateHandle =
                controller.getCurrentBackStackEntry().getSavedStateHandle();
        savedStateHandle.getLiveData("barcode").observe(getViewLifecycleOwner(),
                result -> {
                    if (result != null) {
                        String barcode = result.toString();
                        binding.barcodeField.setText(barcode);
                    }
                });
        savedStateHandle.getLiveData("adapter").observe(getViewLifecycleOwner(),
                (ad) -> {
                    if (ad != null) {
                        CreateBillRecyclerViewAdapter _ad = (CreateBillRecyclerViewAdapter) ad;
                        adapter.setCategories(_ad.getCategories(), _ad.getCounts());
                    }
                });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (adapter == null || adapter.getCategories() == null || adapter.getCounts() == null)
            return;
        outState.putSerializable("cats", adapter.getCategories());
        outState.putSerializable("counts", adapter.getCounts());
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = CreateBillFragmentBinding.inflate(inflater, container, false);
        binding.barcodeOpener.setOnClickListener(e -> {
            Bundle instance = new Bundle();
            instance.putSerializable("adapter", adapter);
            controller.navigate(R.id.action_createBillFragment_to_itemScannerFragment, instance);
        });
        viewModel = new ViewModelProvider(requireActivity()).get(CreateBillViewModel.class);
        controller = Navigation.findNavController(
                container.getRootView().findViewById(R.id.nav_host_fragment_content_main));
        adapter = new CreateBillRecyclerViewAdapter();
        binding.selectedItems.setAdapter(adapter);
        String token = ContextUtils.getToken(requireContext());
        binding.addItemButton.setOnLongClickListener(e -> {
            String barcode = binding.barcodeField.getEditableText().toString();
            if (!barcode.equals("")) {
                viewModel.getItem(token, barcode).observe(requireActivity(), this::onItem);
            } else
                Toast.makeText(requireContext(),
                        "the barcode is empty please scan one and try again later.",
                        Toast.LENGTH_SHORT).show();

            return true;
        });
        binding.addItemButton.setOnClickListener(e -> {
            String barcode = binding.barcodeField.getEditableText().toString();
            if (!barcode.equals("")) {
                viewModel.getItem(token, barcode).observe(requireActivity(), this::onItemOnce);
            } else
                Toast.makeText(requireContext(),
                        "the barcode is empty please scan one and try again later.",
                        Toast.LENGTH_SHORT).show();

        });

        binding.checkOut.setOnClickListener(e -> {
            // generate bill and send request to database in this data
            ArrayList<ReceiptItem> items = new ArrayList<>();
            for (int i = 0; i < adapter.getCategories().size(); i++) {
                ReceiptItem item = new ReceiptItem(adapter.getCategories().get(i).getName(),
                        adapter.getCategories().get(i).getPrice(), adapter.getCounts().get(i),
                        adapter.getCategories().get(i).getBarcode());
                items.add(item);
            }
            Bundle bundle = new Bundle();
            bundle.putSerializable("items", items);
            bundle.putBoolean("show",false);
            controller.navigate(R.id.action_createBillFragment_to_receiptFragment, bundle);
        });
        return binding.getRoot();
    }

    private void onItemOnce(CategoryItemResponse response) {
        if (response == null)
            return;
        if (response.getCode() == 200) {

            int count = 1;
            String token = ContextUtils.getToken(requireContext());
            String barcode = binding.barcodeField.getEditableText().toString();
            viewModel.addItemToBill(token, barcode, count)
                    .observe(requireActivity(),
                            mr -> this.onAddItem(mr, count, response.getCategory()));
        }
        if (response.getCode() == 300) {
            Toast.makeText(requireContext(), response.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    private void onItem(CategoryItemResponse response) {
        if (response == null)
            return;
        if (response.getCode() == 200) {

            BottomSheetDialog sheet = new BottomSheetDialog(requireContext());
            sheet.setContentView(R.layout.alert_dialog_category_item);
            final TextView countText = sheet.findViewById(R.id.count_field);
            ImageButton btn = sheet.findViewById(R.id.add_item_button);
            btn.setOnClickListener(e -> {
                int count = Integer.parseInt(
                        countText.getEditableText().toString()
                );
                String token = ContextUtils.getToken(requireContext());
                String barcode = binding.barcodeField.getEditableText().toString();
                viewModel.addItemToBill(token, barcode, count)
                        .observe(requireActivity(),
                                mr -> this.onAddItem(mr, count, response.getCategory()));
                sheet.dismiss();
            });
            sheet.show();
        }
        if (response.getCode() == 300) {
            Toast.makeText(requireContext(), response.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    private void onAddItem(MessageResponse response, int count, Category category) {
        if (response.getCode() == 200) {
            adapter.addCategory(category, count);
            binding.barcodeField.setText("");
        } else Toast.makeText(requireContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
    }
}

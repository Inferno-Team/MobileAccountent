package com.inferno.mobile.accountent.ui.customer.link_bill;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.databinding.LinkBillFragmentBinding;
import com.inferno.mobile.accountent.models.BillItem;
import com.inferno.mobile.accountent.models.Category;
import com.inferno.mobile.accountent.models.LinkBillResponse;
import com.inferno.mobile.accountent.models.MessageResponse;
import com.inferno.mobile.accountent.models.ReceiptItem;
import com.inferno.mobile.accountent.utils.ContextUtils;
import com.inferno.mobile.billprogressbarlib.BillProgressBar;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.lifecycle.HiltViewModel;

@AndroidEntryPoint
public class LinkBillFragment extends Fragment {
    private NavController controller;
    private LinkBillViewModel viewModel;
    public static boolean IS_FIRST = true;
    private LinkBillFragmentBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SavedStateHandle savedStateHandle =
                controller.getCurrentBackStackEntry().getSavedStateHandle();
        savedStateHandle.getLiveData("barcode").observe(getViewLifecycleOwner(), this::onQRCode);
    }


    private void onQRCode(Object o) {
        if (o instanceof String) {
            int billId = Integer.parseInt(o.toString());
            String token = ContextUtils.getToken(requireContext());
            binding.progressBar.startAnimation();
            viewModel.linkBill(token, billId).observe(requireActivity(), this::onBillLinked);

        }
    }

    private void onBillLinked(LinkBillResponse response) {
        binding.progressBar.stopAnimation();
        new Handler().postDelayed(() -> {
            Toast.makeText(requireContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
            if (response.getCode() == 200) {
                Bundle bundle = new Bundle();
                ArrayList<ReceiptItem> items = new ArrayList<>();
                for (int i = 0; i < response.getBill().getItems().size(); i++) {
                    BillItem billItem = response.getBill().getItems().get(i);
                    ReceiptItem item = new ReceiptItem(billItem.getCategory().getName(),
                            billItem.getCategory().getPrice(), billItem.getCount()
                            , billItem.getCategory().getBarcode());
                    items.add(item);
                }
                bundle.putSerializable("items", items);
                bundle.putBoolean("show", true);
                controller.navigate(R.id.action_linkBillFragment_to_receiptFragment, bundle);
            }else{
                controller.navigateUp();
            }
        }, BillProgressBar.NORMAL);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = LinkBillFragmentBinding.inflate(inflater, container, false);
        controller = Navigation.findNavController(
                container.getRootView().findViewById(R.id.nav_host_fragment_content_main));
        viewModel = new ViewModelProvider(requireActivity()).get(LinkBillViewModel.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean("is_link", true);
        if (IS_FIRST)
            controller.navigate(R.id.action_linkBillFragment_to_itemScannerFragment, bundle);
        IS_FIRST = false;
        return binding.getRoot();
    }
}

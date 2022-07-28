package com.inferno.mobile.accountent.ui.customer.my_bills;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.adapters.MyBillsAdapter;
import com.inferno.mobile.accountent.databinding.MyBillsFragmentBinding;
import com.inferno.mobile.accountent.models.BillDetails;
import com.inferno.mobile.accountent.models.BillItem;
import com.inferno.mobile.accountent.models.ReceiptItem;
import com.inferno.mobile.accountent.utils.ContextUtils;
import com.inferno.mobile.billprogressbarlib.BillProgressBar;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MyBillsFragment extends Fragment {
    private MyBillsFragmentBinding binding;
    private NavController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MyBillsFragmentBinding.inflate(inflater, container, false);
        MyBillViewModel viewModel = new ViewModelProvider(requireActivity())
                .get(MyBillViewModel.class);
        controller = Navigation.findNavController(
                container.getRootView().findViewById(R.id.nav_host_fragment_content_main));
        viewModel.getMyBills(ContextUtils.getToken(requireContext()))
                .observe(requireActivity(), this::onBillDetails);
        binding.progressBar.startAnimation();
        return binding.getRoot();
    }

    private void onBillDetails(ArrayList<BillDetails> details) {

        binding.progressBar.stopAnimation();
        new Handler().postDelayed(() -> {
            MyBillsAdapter adapter = new MyBillsAdapter(requireContext(), details);
            adapter.setOnItemClickListener((id, pos) -> {
                BillDetails bill = details.get(pos);
                ArrayList<ReceiptItem> items = new ArrayList<>();
                for(BillItem item : bill.getItems()){
                    ReceiptItem receiptItem = new ReceiptItem(
                            item.getCategory().getName(),
                            item.getCategory().getPrice(),
                            item.getCount(),
                            item.getCategory().getBarcode()
                    );
                    items.add(receiptItem);
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("items",items);
                bundle.putBoolean("show", true);
                controller.navigate(R.id.action_myBillsFragment_to_receiptFragment,bundle);
            });
            adapter.setOnShopNameClickListener((id, pos) -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("owner",details.get(pos).getShop().getOwner());
                controller.navigate(R.id.action_myBillsFragment_to_otherShopsFragment,bundle);

            });
            binding.requests.setAdapter(adapter);
        }, BillProgressBar.FAST);

    }
}

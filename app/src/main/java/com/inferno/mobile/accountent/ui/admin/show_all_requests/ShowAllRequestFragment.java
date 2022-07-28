package com.inferno.mobile.accountent.ui.admin.show_all_requests;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.adapters.RequestRVAdapter;
import com.inferno.mobile.accountent.adapters.ShopRVAdapter;
import com.inferno.mobile.accountent.databinding.ShowAllRequestsBinding;
import com.inferno.mobile.accountent.models.Response2Request;
import com.inferno.mobile.accountent.models.Shop;
import com.inferno.mobile.accountent.models.ShopRequestResponse;
import com.inferno.mobile.accountent.utils.ContextUtils;
import com.inferno.mobile.billprogressbarlib.BillProgressBar;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ShowAllRequestFragment extends Fragment {
    private ShowAllRequestsBinding binding;
    private RequestsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ShowAllRequestsBinding.inflate(inflater, container, false);
        binding.progressBar.startAnimation();
        viewModel = new ViewModelProvider(requireActivity()).get(RequestsViewModel.class);
        viewModel.showAllRequests(ContextUtils.getToken(requireContext()))
                .observe(requireActivity(), this::onRequestResponse);
        return binding.getRoot();
    }

    private void onRequestResponse(ShopRequestResponse response) {
        binding.progressBar.stopAnimation();
        new Handler().postDelayed(() -> {
            if (response == null) return;
            binding.requests.setVisibility(View.VISIBLE);
            RequestRVAdapter adapter = new RequestRVAdapter(requireContext(), response.getShops());
            adapter.setShopItemListener((id, pos) ->
                    new AlertDialog.Builder(requireContext())
                            .setPositiveButton(getString(R.string.yes), (dialog1, which) -> {
                                viewModel.response2request(ContextUtils.getToken(requireContext()), id)
                                        .observe(requireActivity(), res -> {

                                            response.getShops().set(pos, res.getShop());
                                            adapter.notifyItemChanged(pos);
                                            Toast.makeText(requireContext(), res.getMessage(),
                                                    Toast.LENGTH_SHORT).show();
                                        });
                                dialog1.dismiss();
                            })
                            .setNegativeButton(getString(R.string.no), ((dialog1, which) -> dialog1.dismiss()))
                            .setTitle(getString(R.string.dialog_title))
                            .setMessage(getString(R.string.dialog_msg))
                            .show());
            binding.requests.setAdapter(adapter);
        }, BillProgressBar.SLOW);

    }
}

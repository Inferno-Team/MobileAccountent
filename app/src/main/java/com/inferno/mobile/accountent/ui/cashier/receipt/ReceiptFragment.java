package com.inferno.mobile.accountent.ui.cashier.receipt;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.adapters.ReceiptAdapter;
import com.inferno.mobile.accountent.databinding.ReceiptFragmentBinding;
import com.inferno.mobile.accountent.models.MessageResponse;
import com.inferno.mobile.accountent.models.ReceiptItem;
import com.inferno.mobile.accountent.utils.ContextUtils;

import java.util.ArrayList;

public class ReceiptFragment extends Fragment {
    private ReceiptFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ReceiptFragmentBinding.inflate(inflater, container, false);
        ReceiptViewModel viewModel = new ViewModelProvider(requireActivity()).get(ReceiptViewModel.class);
        ArrayList<ReceiptItem> items = (ArrayList<ReceiptItem>)
                requireArguments().getSerializable("items");
        ArrayList<String> barcodes = new ArrayList<>();
        ArrayList<Integer> counts = new ArrayList<>();
        double check = 0;
        for (ReceiptItem item : items) {
            barcodes.add(item.getBarcode());
            counts.add(item.getCount());
            check += item.getFullPrice();
        }
        binding.totalCost.setText(getString(R.string.currency, String.valueOf(check)));
        ReceiptAdapter adapter = new ReceiptAdapter(items, requireContext());
        binding.checkOutItem.itemName.setText(R.string.category_name);
        binding.checkOutItem.itemPrice.setText(R.string.price);
        binding.checkOutItem.itemCount.setText(R.string.purchase_count);
        binding.checkOutItem.fullPrice.setText(R.string.full_price);
        binding.receiptItems.setAdapter(adapter);
        if (!requireArguments().getBoolean("show")) {
            String token = ContextUtils.getToken(requireContext());
            viewModel.createBill(token, barcodes, counts, check)
                    .observe(requireActivity(), this::onBillCreated);
        } else {
            binding.qrCode.setVisibility(View.GONE);
        }


        return binding.getRoot();
    }

    private void onBillCreated(MessageResponse response) {
        if (response.getCode() == 200) {
            createQR(response.getData());
        }
        if (!response.getMessage().equals(""))
            Toast.makeText(requireContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void createQR(String content) {
        QRCodeWriter codeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = codeWriter.encode(content,
                    BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            binding.qrCode.setVisibility(View.VISIBLE);
            binding.qrCode.setImageBitmap(bmp);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}

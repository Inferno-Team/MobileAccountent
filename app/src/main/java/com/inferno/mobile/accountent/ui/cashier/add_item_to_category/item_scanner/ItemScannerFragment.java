package com.inferno.mobile.accountent.ui.cashier.add_item_to_category.item_scanner;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.budiyev.android.codescanner.CodeScanner;
import com.inferno.mobile.accountent.activities.MainActivity;
import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.databinding.ItemScannerBinding;
import com.inferno.mobile.accountent.ui.customer.link_bill.LinkBillFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ItemScannerFragment extends Fragment {
    private ItemScannerBinding binding;
    private final int REQUEST_CODE = 101;
    private NavController controller;

    private CodeScanner scanner;

    @Override
    public void onResume() {
        super.onResume();
        if (scanner != null) {
            scanner.stopPreview();
            scanner.startPreview();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (scanner != null) {
            scanner.stopPreview();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ItemScannerBinding.inflate(inflater, container, false);
        controller = Navigation.findNavController(
                container.getRootView().findViewById(R.id.nav_host_fragment_content_main));

        if (requireActivity().checkSelfPermission(Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
        } else camera();

        return binding.getRoot();
    }

    private void camera() {
        scanner = new CodeScanner(requireContext(), binding.scannerView);
        scanner.setCamera(CodeScanner.CAMERA_BACK);
        scanner.setFormats(CodeScanner.ALL_FORMATS);
        scanner.setFlashEnabled(false);
        scanner.setAutoFocusEnabled(true);

        scanner.setDecodeCallback(result -> {
            requireActivity()
                    .runOnUiThread(() -> {

                        MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.beep);
                        mp.start();

                        NavBackStackEntry entry = controller.getPreviousBackStackEntry();
                        entry.getSavedStateHandle().set("barcode", result.getText());
                        if (requireArguments().getSerializable("adapter") != null)
                            entry.getSavedStateHandle().set("adapter", requireArguments().
                                    getSerializable("adapter"));
                        controller.navigateUp();
                    });
        });
        scanner.startPreview();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (permissions[0].equals(Manifest.permission.CAMERA))
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    camera();
                } else {
                    Toast.makeText(requireContext(), "this permission is required.",
                            Toast.LENGTH_SHORT).show();
                    requireActivity().finishAffinity();
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity)requireActivity()).binding.settings.setVisibility(View.GONE);
        boolean isLink =requireArguments().getBoolean("is_link");
        requireActivity().getOnBackPressedDispatcher()
                .addCallback(requireActivity(), new OnBackPressedCallback(isLink) {
                    @Override
                    public void handleOnBackPressed() {
                        if(isLink){
                            controller.navigateUp();
                            controller.navigateUp();
                        }
                    }
                });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ((MainActivity)requireActivity()).binding.settings.setVisibility(View.VISIBLE);
    }
}

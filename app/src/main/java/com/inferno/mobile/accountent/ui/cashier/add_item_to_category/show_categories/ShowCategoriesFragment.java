package com.inferno.mobile.accountent.ui.cashier.add_item_to_category.show_categories;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.inferno.mobile.accountent.R;
import com.inferno.mobile.accountent.adapters.CategoryRVAdapter;
import com.inferno.mobile.accountent.databinding.ShowCategoriesFragmentBinding;
import com.inferno.mobile.accountent.models.Category;
import com.inferno.mobile.accountent.utils.ContextUtils;
import com.inferno.mobile.billprogressbarlib.BillProgressBar;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ShowCategoriesFragment extends Fragment {
    private ShowCategoryViewModel viewModel;
    private ShowCategoriesFragmentBinding binding;
    private NavController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ShowCategoriesFragmentBinding.inflate(inflater, container, false);
        controller = Navigation.findNavController(
                container.getRootView().findViewById(R.id.nav_host_fragment_content_main));
        viewModel = new ViewModelProvider(requireActivity()).get(ShowCategoryViewModel.class);
        binding.progressBar.startAnimation();

        viewModel.getAllCategories(ContextUtils.getToken(requireContext()))
                .observe(requireActivity(), this::onCategories);
        binding.addCategory.setOnClickListener(l ->
                controller.navigate(R.id.action_showCategoriesFragment2_to_addCategoryFragment));
        return binding.getRoot();
    }

    private void onCategories(ArrayList<Category> categories) {
        binding.progressBar.stopAnimation();
        new Handler().postDelayed(() -> {
            binding.addCategory.setVisibility(View.VISIBLE);
            if (categories.size() == 0) {
                binding.catEmptyText.setVisibility(View.VISIBLE);
                binding.catEmpty.setVisibility(View.VISIBLE);
                binding.showCatsRv.setVisibility(View.GONE);

                Matrix matrix = new Matrix();
                matrix.postRotate(180);
                Bitmap empty = convertDrawable2Bitmap();
                if (empty == null) return;
                empty = Bitmap.createBitmap(empty, 0, 0, empty.getWidth(),
                        empty.getHeight(), matrix, true);
                binding.catEmpty.setImageBitmap(empty);

            } else {
                binding.catEmptyText.setVisibility(View.GONE);
                binding.catEmpty.setVisibility(View.GONE);
                binding.showCatsRv.setVisibility(View.VISIBLE);
                CategoryRVAdapter adapter = new CategoryRVAdapter(categories, requireContext());
                binding.showCatsRv.setAdapter(adapter);
            }

        }, BillProgressBar.FAST);
    }

    private Bitmap convertDrawable2Bitmap() {
        Drawable drawable = AppCompatResources.getDrawable(requireContext(),
                R.drawable.ic_shoppingtrolleyblack);
        if (drawable == null) return null;

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}

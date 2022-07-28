package com.inferno.mobile.accountent.ui.manager.show_categories;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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
    private ShowCategoriesFragmentBinding binding;
    private ShowCategoryViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ShowCategoriesFragmentBinding.inflate(inflater, container, false);
        binding.progressBar.startAnimation();
        viewModel = new ViewModelProvider(requireActivity()).get(ShowCategoryViewModel.class);
        int compId = requireArguments().getInt("comp_id");
        if (compId != -1) {
            viewModel.getCompanyCategories(ContextUtils.getToken(requireContext()), compId)
                    .observe(requireActivity(), this::onCategory);
        } else
            viewModel.getCategories(ContextUtils.getToken(requireContext()))
                    .observe(requireActivity(), this::onCategory);
        return binding.getRoot();
    }

    private void onCategory(ArrayList<Category> cats) {
        binding.progressBar.stopAnimation();
        new Handler().postDelayed(() -> {

            if (cats == null || cats.size() == 0) {
                Matrix matrix = new Matrix();
                matrix.postRotate(180);
                Bitmap empty = convertDrawable2Bitmap();
                if (empty == null) return;
                empty = Bitmap.createBitmap(empty, 0, 0, empty.getWidth(),
                        empty.getHeight(), matrix, true);
                binding.catEmpty.setVisibility(View.VISIBLE);
                binding.catEmptyText.setVisibility(View.VISIBLE);
                binding.catEmpty.setImageBitmap(empty);
//                binding.catEmpty.setColorFilter(ContextCompat.
//                        getColor(requireContext(), R.color.gray_200));
                return;
            }
            binding.showCatsRv.setVisibility(View.VISIBLE);
            CategoryRVAdapter adapter = new CategoryRVAdapter(cats, requireContext());
            binding.showCatsRv.setAdapter(adapter);
        }, BillProgressBar.NORMAL);

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

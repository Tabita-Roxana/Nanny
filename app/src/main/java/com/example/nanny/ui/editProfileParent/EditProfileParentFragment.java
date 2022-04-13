package com.example.nanny.ui.editProfileParent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.nanny.databinding.FragmentEditprofileparentBinding;

public class EditProfileParentFragment extends Fragment {

    private FragmentEditprofileparentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EditProfileParentViewModel editProfileParentViewModel =
                new ViewModelProvider(this).get(EditProfileParentViewModel.class);

        binding = FragmentEditprofileparentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textEditProfileParent;
        editProfileParentViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
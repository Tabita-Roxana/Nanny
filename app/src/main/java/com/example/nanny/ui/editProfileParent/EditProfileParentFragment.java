package com.example.nanny.ui.editProfileParent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.nanny.databinding.FragmentEditprofileparentBinding;
import com.example.nanny.model.UserDetails;

public class EditProfileParentFragment extends Fragment {

    private FragmentEditprofileparentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EditProfileParentViewModel editProfileParentViewModel =
                new ViewModelProvider(this).get(EditProfileParentViewModel.class);

        binding = FragmentEditprofileparentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textEditProfileParent;
        ImageView imageView  =  binding.editParentProfilePict;
        TextView description = binding.etDescription;
        TextView name = binding.etName;
        TextView address = binding.etAddress;
        TextView age = binding.etAge;
        TextView city = binding.etCity;
//        add children
        Button button = binding.btEdit;

        editProfileParentViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        editProfileParentViewModel.getCityText().observe(getViewLifecycleOwner(), city::setText);
        editProfileParentViewModel.getAddressText().observe(getViewLifecycleOwner(), address::setText);
        editProfileParentViewModel.getDescriptionText().observe(getViewLifecycleOwner(), description::setText);
        editProfileParentViewModel.getNameText().observe(getViewLifecycleOwner(), name::setText);
        editProfileParentViewModel.getAgeText().observe(getViewLifecycleOwner(), age::setText);


        button.setOnClickListener(view -> editProfileParentViewModel.editProfile(new UserDetails(name.getText().toString(), city.getText().toString(),
                description.getText().toString(),age.getText().toString(), address.getText().toString())));


        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
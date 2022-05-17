package com.example.nanny.ui.edit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.nanny.databinding.FragmentEditBinding;
import com.example.nanny.model.UserDetails;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class EditFragment extends Fragment {

    private FragmentEditBinding binding;
    FirebaseUser currentUser;
    StorageReference storageReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EditViewModel editViewModel =
                new ViewModelProvider(this).get(EditViewModel.class);

        binding = FragmentEditBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textEdit;
        ImageView profileImage  =  binding.editProfilePict;
        TextView description = binding.etDescription;
        TextView name = binding.etName;
        TextView address = binding.etAddress;
        TextView age = binding.etAge;
        TextView city = binding.etCity;

        Button button = binding.btEdit;

        editViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        editViewModel.getCityText().observe(getViewLifecycleOwner(), city::setText);
        editViewModel.getAddressText().observe(getViewLifecycleOwner(), address::setText);
        editViewModel.getDescriptionText().observe(getViewLifecycleOwner(), description::setText);
        editViewModel.getNameText().observe(getViewLifecycleOwner(), name::setText);
        editViewModel.getAgeText().observe(getViewLifecycleOwner(), age::setText);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference profileRef = storageReference.child("users/"+currentUser.getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImage);
            }
        });


        button.setOnClickListener(view -> {
            editViewModel.editProfile(new UserDetails(name.getText().toString(), city.getText().toString(),
                    description.getText().toString(), age.getText().toString(), address.getText().toString()));

        });

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getData() != null) {
                Uri imageUri = result.getData().getData();
                uploadImageToFirebase(imageUri);

            }
        });
        profileImage.setOnClickListener(view -> {
            Intent openGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activityResultLauncher.launch(openGallery);
            Toast.makeText(getActivity(), "Information saved", Toast.LENGTH_SHORT).show();
        });

        return root;
    }

    public void uploadImageToFirebase(Uri imageUri) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference fileRef = storageReference.child("users/"+currentUser.getUid()+"/profile.jpg");
        ProgressDialog pd = new ProgressDialog(this.getContext());
        pd.setMessage("Loading");
        pd.show();

        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pd.dismiss();
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(binding.editProfilePict);
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(getContext(), "Try again", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progressPercent = (100.00*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                pd.setMessage("Percentage: " +(int) progressPercent+ "");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
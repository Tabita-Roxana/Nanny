package com.example.nanny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nanny.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button login;
    private Button regbtn;

    private FirebaseAuth auth;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        regbtn = findViewById(R.id.regbtn);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        login.setOnClickListener(view -> {
            String txt_email = email.getText().toString();
            String txt_pass = password.getText().toString();
            loginUser(txt_email, txt_pass);

        });

        regbtn.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, Register.class)));
    }
    private void loginUser(String txt_email, String txt_pass) {
        auth.signInWithEmailAndPassword(txt_email, txt_pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                DatabaseReference ref = database.getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        System.out.println(user.getType());
                        if(user.getType().equals("nanny"))
                        {
                            Toast.makeText(LoginActivity.this, "Login ok", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, NannyActivity.class));
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this, "Login ok", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, ParentActivity.class));
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });
            }
        });
    }
}
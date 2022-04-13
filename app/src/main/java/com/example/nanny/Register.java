package com.example.nanny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.nanny.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button register;
    private Button login;
    private RadioButton rbtnNanny;
    private RadioButton rbtnParent;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email= findViewById(R.id.email);
        password = findViewById(R.id.password);
        register =  findViewById(R.id.register);
        login = findViewById(R.id.loginbtn);

        rbtnNanny = findViewById(R.id.radioButtonRegisterNanny);
        rbtnParent = findViewById(R.id.radioButtonRegisterParent);

        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(view -> {
            String txt_email = email.getText().toString();
            String txt_password = password.getText().toString();

            if(TextUtils.isEmpty(txt_email )|| TextUtils.isEmpty(txt_password)){
                Toast.makeText(Register.this, "Empty credentials", Toast.LENGTH_SHORT).show();
            } else if (txt_password.length()<6){
                Toast.makeText(Register.this, "Password too short", Toast.LENGTH_SHORT).show();
            } else {
                registerUser(txt_email,txt_password);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, LoginActivity.class));
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(auth.getCurrentUser()!=null){
//            ///handle the login
//        }
//    }

    private void registerUser(String txt_email, String txt_password) {

        auth.createUserWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(Register.this, task -> {
            if (task.isSuccessful() && rbtnNanny.isChecked()){

                    User user = new User(txt_email, txt_password, "nanny");
                    FirebaseDatabase.getInstance().getReference("users").child("nanny")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(task1 -> {
                        Log.i("success","createUserWithEmail:success");
                        Toast.makeText(Register.this, "Registering nanny successful!", Toast.LENGTH_SHORT).show();
                    });
                    startActivity(new Intent(Register.this,NannyActivity.class));
                    finish();

            }else if (task.isSuccessful() && rbtnParent.isChecked()){

                    User user = new User(txt_email, txt_password, "parent");
                    FirebaseDatabase.getInstance().getReference("users").child("parent")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(task12 -> {
                                Log.i("success","createUserWithEmail:success");
                                Toast.makeText(Register.this, "Registering user successful!", Toast.LENGTH_SHORT).show();
                            });
                    startActivity(new Intent(Register.this,ParentActivity.class));
                    finish();

            } else {
                Log.i("not","createUserWithEmail:not");
                Toast.makeText(Register.this, "Registration failed!", Toast.LENGTH_SHORT).show();

            }
        });

//        if(rbtnNanny.isChecked()){
//            startActivity(new Intent(Register.this,NannyActivity.class));
//        }
//        else{
//            startActivity(new Intent(Register.this,ParentActivity.class));
//        }


    }
}
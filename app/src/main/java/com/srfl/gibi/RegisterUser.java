package com.srfl.gibi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterUser extends AppCompatActivity {
    EditText mEmailEt, mPasswordEt;
    Button registerButton;
    ProgressDialog progressDialog;

    private FirebaseAuth mAuth1;
    private FirebaseAuth mAuth2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        // Action Bar and It
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Register User");

        // Enable Back Button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //init
        mEmailEt = findViewById(R.id.et_email);
        mPasswordEt = findViewById(R.id.et_password);
        registerButton = findViewById(R.id.b_register);

        mAuth1 = FirebaseAuth.getInstance();
        FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                .setApiKey("AIzaSyDT_pv0n-VPmsfSR7njhwR6c6u5zXAM7fw")
                .setApplicationId("gibi-1a821").build();

        try { FirebaseApp myApp = FirebaseApp.initializeApp(getApplicationContext(), firebaseOptions, "AnyAppName");
            mAuth2 = FirebaseAuth.getInstance(myApp);
        } catch (IllegalStateException e){
            mAuth2 = FirebaseAuth.getInstance(FirebaseApp.getInstance("AnyAppName"));
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering");

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String email = mEmailEt.getText().toString().trim();
                    String password = mPasswordEt.getText().toString().trim();

                    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        mEmailEt.setError("Invalid Email");
                        mEmailEt.setFocusable(true);
                    }
                    else if(password.length() < 6){
                        mPasswordEt.setError("Password Length must be at least 6 Characters.");
                        mPasswordEt.setFocusable(true);
                    }

                 registerStudent(email, password);

                }
        });
    }
    private void registerStudent(String email, String password){
progressDialog.show();
mAuth2.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    FirebaseUser user = mAuth2.getCurrentUser();
                    String email = user.getEmail();
                    String uuid = user.getUid();

                    HashMap<Object, String> hashMap = new HashMap<>();
                    hashMap.put("email", email);
                    hashMap.put("uuid", uuid);
                    hashMap.put("name", "");
                    hashMap.put("id", "");
                    hashMap.put("image", "");

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference("Users");
                    reference.child(uuid).setValue(hashMap);

                    Toast.makeText(RegisterUser.this, "Registered... \n+user.getEmail(", Toast.LENGTH_SHORT).show();
                    mAuth2.signOut();
                    startActivity(new Intent(RegisterUser.this, DashboardActivity.class));
                    finish();
                } else {
                    Toast.makeText(RegisterUser.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
     progressDialog.dismiss();
        Toast.makeText(RegisterUser.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

    }
});
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
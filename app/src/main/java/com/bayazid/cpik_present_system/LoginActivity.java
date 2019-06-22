package com.bayazid.cpik_present_system;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
private Button LogInBtn;
private TextView Registration;
    private FirebaseAuth mAuth;
    public static final String TAG = "Login Activity ";
    private EditText Email,Password;
    private String passEmail,passPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        LogInBtn =findViewById(R.id.email_sign_in_button);
        Registration=findViewById(R.id.link_register);
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);


        //login btn action
        LogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tryLogIn();
               // startActivity(new Intent(LoginActivity.this,Teacher_Class_type.class));
                //srop current activity
               // LoginActivity.this.finish();
            }
        });
    }

    private void tryLogIn() {

        passEmail=Email.getText().toString();
        passPassword=Password.getText().toString();
        mAuth.signInWithEmailAndPassword(passEmail, passPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this,Teacher_Class_type.class));


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();


                        }

                        // ...
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
       // updateUI(currentUser);
    }
}

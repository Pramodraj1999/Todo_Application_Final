package com.tbcmad.todoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText txtName, txtPassword;
    Button btnLogin, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtName = findViewById(R.id.tv_activity_user_name);
        txtPassword= findViewById(R.id.tv_activity_user_password);
        btnCancel = findViewById(R.id.cancel_activity_btn_cancel);
        btnLogin = findViewById(R.id.login_activity_btn_login);


        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if(txtName.getText().length()==0) {
                    txtName.requestFocus();
                    txtName.setError("Username is required");
                    return;

                }
                if (txtPassword.getText().length()==0) {
                    txtPassword.requestFocus();
                    txtPassword.setError("Password is required");
                    return;
                }

                if(txtName.getText().toString().equals("admin") &&
                        txtPassword.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_SHORT).show();

                    SharedPreferences preference = getApplicationContext().getSharedPreferences("todo_pref",  0);
                    SharedPreferences.Editor editor = preference.edit();
                    editor.putBoolean("authentication",true);
                    editor.commit();
                    Intent intent= new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else
                {
                    Toast.makeText(getApplicationContext(),
                            "Wrong username / password...",Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertCancel();
            }
        });


    }

    void AlertCancel(){
        final androidx.appcompat.app.AlertDialog dialog = new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage(getString(R.string.alert_dialog_message_cancel))
                .setPositiveButton("No", null)
                .setNegativeButton("Yes", null)
                .show();

        Button positiveButtom = dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE);
        positiveButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button negativeButtom = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativeButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

    }
}
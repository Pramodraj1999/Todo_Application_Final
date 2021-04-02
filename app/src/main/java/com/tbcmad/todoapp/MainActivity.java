package com.tbcmad.todoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    Fragment fragment;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        fragment = new ListTodoFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.list_activity_container, fragment)
                .commit();

        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_delete_all:
                Toast.makeText(getApplicationContext(),"Delete all", Toast.LENGTH_LONG).show();
                break;
                case R.id.mnu_delete_cpmpleted:
                    Toast.makeText(getApplicationContext(),"Delete Completed", Toast.LENGTH_LONG).show();
                    break;
            case R.id.mnu_logout:
                SharedPreferences preferences = getApplicationContext().getSharedPreferences("todo_pref", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                Intent intent= new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.mnu_exit:
                final AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.app_name))
                        .setMessage(getString(R.string.alert_dialog_message_exit))
                        .setPositiveButton("No", null)
                        .setNegativeButton("Yes", null)
                        .show();

                Button positiveButtom = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
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
                break;



        }
        return super.onOptionsItemSelected(item);
    }
}
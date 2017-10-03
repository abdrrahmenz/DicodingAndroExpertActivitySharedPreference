package com.expert.andro.mysharedpref;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvName, tvAge, tvEmail, tvPhoneNo, tvIsLove;
    private Button btnSave;
    private UserPreference mUserPreference;

    private final String TEXT_EMPTY = "Tidak ada";

    private boolean isPreferenceEmpty = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = (TextView) findViewById(R.id.tv_name);
        tvAge = (TextView) findViewById(R.id.tv_age);
        tvEmail = (TextView) findViewById(R.id.tv_email);
        tvPhoneNo = (TextView) findViewById(R.id.tv_phone);
        tvIsLove = (TextView) findViewById(R.id.tv_is_love_mu);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);

        mUserPreference = new UserPreference(this);

        getSupportActionBar().setTitle("My User Preference");

        showExistingPreference();
    }

    private void showExistingPreference() {
        if (!TextUtils.isEmpty(mUserPreference.getName())){
            tvName.setText(mUserPreference.getName());
            tvAge.setText(String.valueOf(mUserPreference.getAge()));
            tvEmail.setText(mUserPreference.getEmail());
            tvPhoneNo.setText(mUserPreference.getPhoneNumber());
            tvIsLove.setText(mUserPreference.isLoveMU() ? "Ya" : "Tidak");

            btnSave.setText("Ubah");
        } else {
            tvName.setText(TEXT_EMPTY);
            tvAge.setText(TEXT_EMPTY);
            tvEmail.setText(TEXT_EMPTY);
            tvPhoneNo.setText(TEXT_EMPTY);
            tvIsLove.setText(TEXT_EMPTY);

            btnSave.setText("Tambah Baru");

            isPreferenceEmpty = true;
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_save){
            Intent intent = new Intent(MainActivity.this, FormUserPreferenceActivity.class);
            if (isPreferenceEmpty){
                intent.putExtra(FormUserPreferenceActivity.EXTRA_TYPE_FORM, FormUserPreferenceActivity.TYPE_ADD);
            } else {
                intent.putExtra(FormUserPreferenceActivity.EXTRA_TYPE_FORM, FormUserPreferenceActivity.TYPE_EDIT);
            }
            startActivityForResult(intent, FormUserPreferenceActivity.REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FormUserPreferenceActivity.REQUEST_CODE){
            showExistingPreference();
        }
    }
}

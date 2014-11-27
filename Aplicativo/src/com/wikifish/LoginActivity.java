
package com.wikifish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
    private EditText etEmail;
    private EditText etPassword;
    private Button btLogin;
    private Button btRegister;
    private String mEmail;
    private String mPassword;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        btLogin = (Button) findViewById(R.id.ib_login);
        btRegister = (Button) findViewById(R.id.ib_register);

        btLogin.setOnClickListener(getLoginListener());

        btRegister.setOnClickListener(getRegisterListener());
    }

    private boolean isValidEmail(final String email) {
        return email.contains("@") && email.contains(".");
    }

    private boolean isValidPassword(final String password) {
        return password.length() >= 4;
    }

    private OnClickListener getRegisterListener() {
        return new OnClickListener() {

            @Override
            public void onClick(final View v) {
                fillVariables();

                if (!checkErr()) {
                    final Bundle data = new Bundle();
                    data.putString("email", mEmail);
                    data.putString("password", mPassword);
                    final DialogCreate dialog = new DialogCreate(
                            LoginActivity.this, data);

                    dialog.show();
                }
            }
        };
    }

    private OnClickListener getLoginListener() {
        return new OnClickListener() {

            @Override
            public void onClick(final View v) {
                fillVariables();
                if (!checkErr()) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        };
    }

    private void fillVariables() {
        mEmail = etEmail != null ? etEmail.getText().toString() : "";
        mPassword = etPassword != null ? mPassword = etPassword.getText()
                .toString() : "";

    }

    private Boolean checkErr() {
        Boolean err = false;
        if (!isValidEmail(mEmail)) {
            etEmail.setError("email invalid");
            err = true;
        }
        if (!isValidPassword(mPassword)) {
            etPassword.setError("password too short");
            err = true;
        }
        return err;
    }
}

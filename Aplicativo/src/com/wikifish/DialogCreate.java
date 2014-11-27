
package com.wikifish;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DialogCreate extends Dialog {
    private EditText etEmail;
    private EditText etPassword;
    private EditText etName;
    private Button btCreate;
    private Button btCancel;
    private String mEmail;
    private String mPassword;
    private final String mMatchPassword;
    private String mName;

    public DialogCreate(final Context context, final Bundle data) {
        super(context);
        mEmail = data.getString("email");
        mMatchPassword = data.getString("password");
        setTitle(R.string.title_create);
        setContentView(R.layout.dialog_create_user);
        setCanceledOnTouchOutside(true);
        init();

    }

    private void init() {
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        btCreate = (Button) findViewById(R.id.ib_create);
        btCancel = (Button) findViewById(R.id.ib_cancel);
        etEmail.setText(mEmail);
        etEmail.setEnabled(false);
        etPassword.setText(mPassword);
        btCreate.setOnClickListener(getCreateListener());

        btCancel.setOnClickListener(getCancelListener());

    }

    private View.OnClickListener getCreateListener() {
        return new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                fillVariables();
                if (!createUser()) {
                    DialogCreate.this.dismiss();
                }

            }

        };
    }

    private View.OnClickListener getCancelListener() {

        return new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                DialogCreate.this.dismiss();

            }
        };
    }

    private void fillVariables() {
        mEmail = etEmail != null ? etEmail.getText().toString() : "";
        mPassword = etPassword != null ? mPassword = etPassword.getText()
                .toString() : "";
        mName = etName != null ? etName.getText().toString() : "";
    }

    private Boolean createUser() {
        Boolean err = false;
        if (!isValidEmail(mEmail)) {
            etEmail.setError("email invalid");
            err = true;
        }
        if (!isValidPassword(mPassword)) {
            etPassword.setError("no match password");
            err = true;
        }
        return err;

    }

    private boolean isValidEmail(final String email) {
        return email.contains("@") && email.contains(".");
    }

    private boolean isValidPassword(final String password) {
        return mMatchPassword.equals(password);
    }
}

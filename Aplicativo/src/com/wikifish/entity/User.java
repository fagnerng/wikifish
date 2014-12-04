
package com.wikifish.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class User implements ToJson, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String email;
    private String password;

    public User(final String name, final String email, final String password) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(final JSONObject json) {
        super();
        name = (String) getObjectByTag("name", json, "");
        email = (String) getObjectByTag("email", json, "sample@mail.com");
        password = (String) getObjectByTag("password", json, "123456");
    }

    private Object getObjectByTag(final String tag, final JSONObject json, final Object defaultValue) {
        Object value = null;
        try {
            value = json.get(tag);
        } catch (final Exception e) {
            e.getMessage();
        }

        return value != null ? value : defaultValue;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) throws Exception {
        if (!isValidEmail(email)) {
            throw new Exception("Email invalid");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) throws Exception {
        if (!isValidPassword(password)) {
            throw new Exception("Password invalid");
        }
        this.password = password;
    }

    private boolean isValidEmail(final String email) {
        return email.contains("@") && email.contains(".");
    }

    private boolean isValidPassword(final String password) {
        return password.length() > 4;
    }

    @Override
    public JSONObject toJson() {
        final JSONObject json = new JSONObject();
        try {
            json.put("name", name);
            json.put("email", email);
            json.put("password", password);

        } catch (final JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return json;
    }
}

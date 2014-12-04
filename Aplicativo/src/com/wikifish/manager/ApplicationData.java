
package com.wikifish.manager;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.wikifish.entity.User;

public class ApplicationData extends Application {

    private User mUser = null;
    private static ApplicationData sContext;

    @Override
    public final void onCreate() {
        restoreUser();
        sContext = this;
    }

    public void restoreUser() {
        final SharedPreferences userDetails = getSharedPreferences("userdetails",
                MODE_PRIVATE);
        final String email = userDetails.getString("email", "");
        final String password = userDetails.getString("password", "");
        final String name = userDetails.getString("name", "");
try{
        mUser = new User(name, email, password);
}catch(Exception e){
 e.getMessage();           
        }

    }

    public User getUser() {
        return mUser;
    }

    public void setUser(final User user) {
        final SharedPreferences userDetails = getSharedPreferences("userdetails",
                MODE_PRIVATE);
        final Editor edit = userDetails.edit();
        edit.clear();
        edit.putString("email", user.getEmail());
        edit.putString("password", user.getPassword());
        edit.putString("name", user.getName());
        edit.commit();
    }

    public static ApplicationData getInstance() {

        return sContext;
    }
}

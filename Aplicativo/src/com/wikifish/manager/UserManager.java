
package com.wikifish.manager;

import com.wikifish.entity.User;

public class UserManager {

    private User mUser;
    private static UserManager mInstance;

    private UserManager() {
        if (mUser == null)
            setUser(new User("name", "email@wikifish.com"));

    }

    public static UserManager getInstance() {
        if (mInstance == null)
            mInstance = new UserManager();
        return mInstance;
    }

    public void setUser(User mUser) {
        this.mUser = mUser;
    }

    public User getUser() {
        return mUser;
    }

}

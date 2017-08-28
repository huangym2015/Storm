package org.ian.storm.ec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ian on 2017/8/28.
 */
@Entity(nameInDb = "userprofile")
public class UserProfile  {
    @Id
    private long userId = 0;
    private String username = null;
    private String email = null;
    private String avatar = null;
    private String password = null;
    private String gender = null;
    @Generated(hash = 1814485698)
    public UserProfile(long userId, String username, String email, String avatar,
            String password, String gender) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.avatar = avatar;
        this.password = password;
        this.gender = gender;
    }
    @Generated(hash = 968487393)
    public UserProfile() {
    }
    public long getUserId() {
        return this.userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }


}

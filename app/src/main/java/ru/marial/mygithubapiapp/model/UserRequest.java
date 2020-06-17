package ru.marial.mygithubapiapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserRequest implements Serializable {
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("avatar_url")
    @Expose
    private String avatar;
    @SerializedName("followers")
    @Expose
    private int followers;
    @SerializedName("bio")
    @Expose
    private String biografy;
    @SerializedName("name")
    @Expose
    private String full_name;
    @SerializedName("created_at")
    @Expose
    private String created;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("email")
    @Expose
    private String email;

    public String getLogin() {
        return login;
    }

    public String getAvatar() {
        return avatar;
    }

    public long getFollowers() {
        return followers;
    }

    public String getBiografy() {
        return biografy;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getCreated() {
        return created;
    }

    public String getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }
}

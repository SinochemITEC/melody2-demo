package com.eyeieye.melody.demo.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ExtendedUser implements Serializable {

    private static final long serialVersionUID = 1588580529927661192L;
    public final static String NAME = "_extended_user";

    private UserAgent user;

    public ExtendedUser() {
        this.user = new UserAgent();
    }

    private Set<String> extendAttributes = new HashSet<>();

    public void addExtendAttribute(String attr){
        extendAttributes.add(attr);
    }
    public String[] getExtendAttributes(){
        return extendAttributes.toArray(new String[0]);
    }

    public void removeExtendAttribute(String attr){
        extendAttributes.remove(attr);
    }

    public UserAgent getUser() {
        return user;
    }

    public void setUser(UserAgent user) {
        this.user = user;
    }
}
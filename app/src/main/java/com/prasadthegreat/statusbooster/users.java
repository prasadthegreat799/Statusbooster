package com.prasadthegreat.statusbooster;

public class users {
    String name;
    String phone;
    String mail;
    String groupname;
    String grouplink;
    int count;

    public users() {}

    public users(String name, String phone, String mail, String groupname, String grouplink, int count) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.groupname = groupname;
        this.grouplink = grouplink;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getGrouplink() {
        return grouplink;
    }

    public void setGrouplink(String grouplink) {
        this.grouplink = grouplink;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
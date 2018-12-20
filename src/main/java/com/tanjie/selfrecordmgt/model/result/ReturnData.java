package com.tanjie.selfrecordmgt.model.result;

import com.tanjie.selfrecordmgt.model.User;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;

@ToString
public class ReturnData extends HashMap<String, Object> implements Serializable {


    public ReturnData() {
        super(10);
    }

    public ReturnData addObj(String key, Object value) {
        this.put(key, value);
        return this;
    }

    public ReturnData addName(Object value) {
        this.put("name", value);
        return this;
    }

    public ReturnData addRoles(Object value) {
        this.put("roles", value);
        return this;
    }

    public ReturnData addAvatar(Object value) {
        this.put("avatar", value);
        return this;
    }

    public ReturnData addUser(User user) {
        this.put("user", user);
        return this;
    }


}

package com.sgl.action;

import java.util.Date;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import com.sgl.model.User;
import com.sgl.service.UserService;    //修改为UserService

@controller("userAction")
public class UserAction {
    @Autowired
    private UserService userService;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String reg() {
        user.setId(UUID.randomUUID().toString());
        user.setRegtime(new Date());
        try {
            userService.addUser(user);
            ServletActionContext.getContext().getSession().put("user", user);
            ServletActionContext.getContext().getSession().put("msg", "注册成功了，可以去登陆了");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            ServletActionContext.getContext().getSession().put("msg", "注册失败了");
            return "error";
        }
    }
}
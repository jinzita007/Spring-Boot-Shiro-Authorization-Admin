package com.demo.model;

import java.util.List;

/**
 * @author zhouyulong
 * @date 2018/10/30 下午4:04.
 */
public class Permission {
    private Integer id;
    private Integer pid;
    private String name;
    private String url;
    private Integer ismenu;
    private Integer deep;
    private String code;
    private Integer status;

    private List<Permission> Children;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getIsmenu() {
        return ismenu;
    }

    public void setIsmenu(Integer ismenu) {
        this.ismenu = ismenu;
    }

    public Integer getDeep() {
        return deep;
    }

    public void setDeep(Integer deep) {
        this.deep = deep;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Permission> getChildren() {
        return Children;
    }

    public void setChildren(List<Permission> children) {
        Children = children;
    }
}

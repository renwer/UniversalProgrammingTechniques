package com.company.model;

public class Group {

    private final Long group_id;
    private String group_name;
    private String group_description;

    public Group(Long group_id, String group_name, String group_description) {
        this.group_id = group_id;
        this.group_name = group_name;
        this.group_description = group_description;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_description() {
        return group_description;
    }

    public void setGroup_description(String group_description) {
        this.group_description = group_description;
    }
}

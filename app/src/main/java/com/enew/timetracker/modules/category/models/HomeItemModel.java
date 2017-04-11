package com.enew.timetracker.modules.category.models;

public class HomeItemModel {

    public String name;
    public int CphotoId;

    public HomeItemModel(String name, int CphotoId) {
        this.name = name;
        this.CphotoId = CphotoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCphotoId() {
        return CphotoId;
    }

    public void setCphotoId(int cphotoId) {
        CphotoId = cphotoId;
    }

}

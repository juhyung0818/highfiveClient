package com.practice.practiceapp;


public class MenuItemEtc {
    String menu_name;
    String menu_icon_src;

    public String getMenu_name() {
        return menu_name;
    }
    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_icon_src() {
        return menu_icon_src;
    }
    public void setMenu_icon_src(String menu_icon_src) {
        this.menu_icon_src = menu_icon_src;
    }

    public MenuItemEtc() {
        this.menu_icon_src = "@drawable/profile";
        this.menu_name = "Button";
    }
    public MenuItemEtc(String name, String icon_src) {
        this.menu_name = name;
        this.menu_icon_src = icon_src;
    }
}

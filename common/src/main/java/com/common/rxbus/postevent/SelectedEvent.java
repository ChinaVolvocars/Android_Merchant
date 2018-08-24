package com.common.rxbus.postevent;

import java.util.List;

public class SelectedEvent {

   private List<String> selectedBeanList;
   private String key;

    public List<String> getSelectedBeanList() {
        return selectedBeanList;
    }

    public SelectedEvent(List<String> selectedBeanList, String key) {
        this.selectedBeanList = selectedBeanList;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

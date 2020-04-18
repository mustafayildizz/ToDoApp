package com.mgkhnyldz.todoapp.utils;

public class DataModel  {

    private String header;
    private String subHeader;

    public DataModel(String header, String subHeader) {
        this.header = header;
        this.subHeader = subHeader;
    }


    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSubHeader() {
        return subHeader;
    }

    public void setSubHeader(String subHeader) {
        this.subHeader = subHeader;
    }



}

package com.grddes.model;

public class Picture {
    private Integer pId;

    private String pUrl;

    private Integer hId;



    public Picture() {
    }

    public Picture(Integer pId, String pUrl, Integer hId) {
        this.pId = pId;
        this.pUrl = pUrl;
        this.hId = hId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpUrl() {
        return pUrl;
    }

    public void setpUrl(String pUrl) {
        this.pUrl = pUrl == null ? null : pUrl.trim();
    }

    public Integer gethId() {
        return hId;
    }

    public void sethId(Integer hId) {
        this.hId = hId;
    }


}
package com.grddes.model;

public class Work {
    private Integer wId;

    private String wName;

    private Double price;

    private String phone;

    private String descripe;

    private String address;

    private Boolean state;

    private Integer uId;

    private String releasetime;


    public Work() {
    }

    public Work(Integer wId, String wName, Double price, String phone, String descripe, String address, Boolean state, Integer uId, String releasetime) {
        this.wId = wId;
        this.wName = wName;
        this.price = price;
        this.phone = phone;
        this.descripe = descripe;
        this.address = address;
        this.state = state;
        this.uId = uId;
        this.releasetime = releasetime;
    }

    public Integer getwId() {
        return wId;
    }

    public void setwId(Integer wId) {
        this.wId = wId;
    }

    public String getwName() {
        return wName;
    }

    public void setwName(String wName) {
        this.wName = wName == null ? null : wName.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getDescripe() {
        return descripe;
    }

    public void setDescripe(String descripe) {
        this.descripe = descripe == null ? null : descripe.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(String releasetime) {
        this.releasetime = releasetime == null ? null : releasetime.trim();
    }

    @Override
    public String toString() {
        return "Work{" +
                "wId=" + wId +
                ", wName='" + wName + '\'' +
                ", price=" + price +
                ", phone='" + phone + '\'' +
                ", descripe='" + descripe + '\'' +
                ", address='" + address + '\'' +
                ", state=" + state +
                ", uId=" + uId +
                ", releasetime='" + releasetime + '\'' +
                '}';
    }
}

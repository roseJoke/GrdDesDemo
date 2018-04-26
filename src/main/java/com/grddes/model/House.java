package com.grddes.model;

public class House {
    private Integer hId;

    private String hostname;

    private Double price;

    private Double area;

    private String descripe;

    private String address;

    private String phone;

    private Boolean state;

    private Integer uId;

    public House() {

    }

    public House(Integer hId, String hostname, Double price, Double area, String descripe, String address, String phone, Boolean state, Integer uId) {
        this.hId = hId;
        this.hostname = hostname;
        this.price = price;
        this.area = area;
        this.descripe = descripe;
        this.address = address;
        this.phone = phone;
        this.state = state;
        this.uId = uId;
    }

    public Integer gethId() {
        return hId;
    }

    public void sethId(Integer hId) {
        this.hId = hId;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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

    @Override
    public String toString() {
        return "House{" +
                "hId=" + hId +
                ", hostname='" + hostname + '\'' +
                ", price=" + price +
                ", area=" + area +
                ", descripe='" + descripe + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", state=" + state +
                ", uId=" + uId +
                '}';
    }
}
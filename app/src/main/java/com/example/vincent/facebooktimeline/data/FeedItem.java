package com.example.vincent.facebooktimeline.data;

/**
 * Created by vincent on 12/3/16.
 */

public class FeedItem {
    private int coupon_id;
    private int customer_id;
    private int client_id;
    private int campaign_id;
    private String coupons;
    private String status;
    private String coupon_name;
    private String coupon_description;
    private String coupon_image;
    private String coupon_barcode;
    private String coupon_expiration;
    private String used_count;
    private String first_name;
    private String photo;
    private String last_name;


    public FeedItem() {
    }


    public FeedItem(int coupon_id, int customer_id, int client_id, int campaign_id, String coupons, String status, String coupon_name,
                 String coupon_description, String coupon_image, String coupon_barcode, String coupon_expiration, String used_count,
                 String first_name, String photo, String last_name) {
        this.coupon_id = coupon_id;
        this.customer_id = customer_id;
        this.client_id = client_id;
        this.campaign_id = campaign_id;
        this.coupons = coupons;
        this.status = status;
        this.coupon_name = coupon_name;
        this.coupon_description = coupon_description;
        this.coupon_image = coupon_image;
        this.coupon_barcode = coupon_barcode;
        this.coupon_expiration = coupon_expiration;
        this.used_count = used_count;
        this.first_name = first_name;
        this.photo = photo;
        this.last_name = last_name;
    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(int campaign_id) {
        this.campaign_id = campaign_id;
    }

    public String getCoupons() {
        return coupons;
    }

    public void setCoupons(String coupons) {
        this.coupons = coupons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCoupon_name() {
        return coupon_name;
    }

    public void setCoupon_name(String coupon_name) {
        this.coupon_name = coupon_name;
    }

    public String getCoupon_description() {
        return coupon_description;
    }

    public void setCoupon_description(String coupon_description) {
        this.coupon_description = coupon_description;
    }

    public String getCoupon_image() {
        return coupon_image;
    }

    public void setCoupon_image(String coupon_image) {
        this.coupon_image = coupon_image;
    }

    public String getCoupon_barcode() {
        return coupon_barcode;
    }

    public void setCoupon_barcode(String coupon_barcode) {
        this.coupon_barcode = coupon_barcode;
    }

    public String getCoupon_expiration() {
        return coupon_expiration;
    }

    public void setCoupon_expiration(String coupon_expiration) {
        this.coupon_expiration = coupon_expiration;
    }

    public String getUsed_count() {
        return used_count;
    }

    public void setUsed_count(String used_count) {
        this.used_count = used_count;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
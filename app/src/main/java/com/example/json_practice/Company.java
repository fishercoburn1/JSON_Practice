package com.example.json_practice;

import com.google.gson.annotations.SerializedName;

public class Company {
    @SerializedName("name")
    public String companyName;
    @SerializedName("year")
    public int companyYear;
    @SerializedName("Product")
    public String companyProduct;

    public String getCompanyName() {return companyName;}

    public int getCompanyYear(){return companyYear;}

    public String getCompanyRecentUpdate(){return companyProduct;}

    public Company(String name, int year, String update){
        companyName = name;
        companyYear = year;
        companyProduct = update;
    }
}

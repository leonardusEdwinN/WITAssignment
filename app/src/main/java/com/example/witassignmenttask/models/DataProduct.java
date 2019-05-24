package com.example.witassignmenttask.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

public class DataProduct extends Ordering<DataProduct> implements Parcelable, Comparable {

    private int rowNumber;
    private int idProduct;
    private String name;
    private String referenceCode;
    private int priceBeforeDiscount;
    private int price;
    private int quantity;
    private int sequenceNumber;
    private int totalDiscount;
    private boolean typeDiscountPercent;
    private int discount;
    private double weight;
    private String metaKeyword;
    private String cover;
    private String defaultCategory;
    private int idManufacturer;
    private String manufacturer;

    public DataProduct() {
    }

    @Override
    public int compare(@Nullable DataProduct left, @Nullable DataProduct right) {
        return Ints.compare(left.getPrice(), right.getPrice());
    }

    protected DataProduct(Parcel in) {
        rowNumber = in.readInt();
        idProduct = in.readInt();
        name = in.readString();
        referenceCode = in.readString();
        priceBeforeDiscount = in.readInt();
        price = in.readInt();
        quantity = in.readInt();
        sequenceNumber = in.readInt();
        totalDiscount = in.readInt();
        typeDiscountPercent = in.readByte() != 0;
        discount = in.readInt();
        weight = in.readDouble();
        metaKeyword = in.readString();
        cover = in.readString();
        defaultCategory = in.readString();
        idManufacturer = in.readInt();
        manufacturer = in.readString();
    }

    public static final Creator<DataProduct> CREATOR = new Creator<DataProduct>() {
        @Override
        public DataProduct createFromParcel(Parcel in) {
            return new DataProduct(in);
        }

        @Override
        public DataProduct[] newArray(int size) {
            return new DataProduct[size];
        }
    };

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public int getPriceBeforeDiscount() {
        return priceBeforeDiscount;
    }

    public void setPriceBeforeDiscount(int priceBeforeDiscount) {
        this.priceBeforeDiscount = priceBeforeDiscount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(int totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public boolean isTypeDiscountPercent() {
        return typeDiscountPercent;
    }

    public void setTypeDiscountPercent(boolean typeDiscountPercent) {
        this.typeDiscountPercent = typeDiscountPercent;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getMetaKeyword() {
        return metaKeyword;
    }

    public void setMetaKeyword(String metaKeyword) {
        this.metaKeyword = metaKeyword;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDefaultCategory() {
        return defaultCategory;
    }

    public void setDefaultCategory(String defaultCategory) {
        this.defaultCategory = defaultCategory;
    }

    public int getIdManufacturer() {
        return idManufacturer;
    }

    public void setIdManufacturer(int idManufacturer) {
        this.idManufacturer = idManufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(rowNumber);
        dest.writeInt(idProduct);
        dest.writeString(name);
        dest.writeString(referenceCode);
        dest.writeInt(priceBeforeDiscount);
        dest.writeInt(price);
        dest.writeInt(quantity);
        dest.writeInt(sequenceNumber);
        dest.writeInt(totalDiscount);
        dest.writeByte((byte) (typeDiscountPercent ? 1 : 0));
        dest.writeInt(discount);
        dest.writeDouble(weight);
        dest.writeString(metaKeyword);
        dest.writeString(cover);
        dest.writeString(defaultCategory);
        dest.writeInt(idManufacturer);
        dest.writeString(manufacturer);
    }

    @Override
    public int compareTo(Object that) {

        return 0;
    }


}

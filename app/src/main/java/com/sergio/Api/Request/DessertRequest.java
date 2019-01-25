package com.sergio.Api.Request;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DessertRequest implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ppu")
    @Expose
    private double ppu;
    @SerializedName("batters")
    @Expose
    private Batters batters;
    @SerializedName("topping")
    @Expose
    private List<Topping> topping;

    protected DessertRequest(Parcel in) {
        id = in.readInt();
        type = in.readString();
        name = in.readString();
        ppu = in.readDouble();
    }

    public static final Creator<DessertRequest> CREATOR = new Creator<DessertRequest>() {
        @Override
        public DessertRequest createFromParcel(Parcel in) {
            return new DessertRequest(in);
        }

        @Override
        public DessertRequest[] newArray(int size) {
            return new DessertRequest[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPpu() {
        return ppu;
    }

    public void setPpu(double ppu) {
        this.ppu = ppu;
    }

    public Batters getBatters() {
        return batters;
    }

    public void setBatters(Batters batters) {
        this.batters = batters;
    }

    public List<Topping> getTopping() {
        return topping;
    }

    public void setTopping(List<Topping> topping) {
        this.topping = topping;
    }

    public DessertRequest(String name, String ppu, String type, Batters batters, List<Topping> topping){
        this.name = name; this.ppu = Double.valueOf(ppu);
        this.type = type; this.batters = batters;
        this.topping = topping;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(type);
        parcel.writeString(name);
        parcel.writeDouble(ppu);
    }
}

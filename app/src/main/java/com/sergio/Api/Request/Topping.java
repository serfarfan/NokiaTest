package com.sergio.Api.Request;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Topping {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("type")
    @Expose
    private String type;

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

    public Topping (String type){
        this.type = type;
    }

}

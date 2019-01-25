package com.sergio.Api.Request;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Batters {

    @SerializedName("batter")
    @Expose
    private List<Batter> batter = new ArrayList<>();

    public List<Batter> getBatter() {
        return batter;
    }

    public void setBatter(List<Batter> batter) {
        this.batter = batter;
    }

    public Batters(List<Batter> batter){
        this.batter = batter;

    }
}

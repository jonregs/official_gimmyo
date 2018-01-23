package com.gimmyo.app.bidboard;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jon on 9/30/2017.
 */

public class BidBoardResult {

    @SerializedName("results")
    private List<BidItem> results;

    public List<BidItem> getResults() {
        return results;
    }

    public void setResults(List<BidItem> results) {
        this.results = results;
    }
}

package com.gimmyo.app.rest;

import com.gimmyo.app.bidboard.BidBoardResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jon on 9/29/2017.
 */

public interface ApiInterface {

    @GET("{bids}")
    Call<BidBoardResult> getBidItems(@Path("bids") String bids);

}

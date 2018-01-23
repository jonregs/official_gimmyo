package com.gimmyo.app.bidboard;

import com.bumptech.glide.Glide;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * Created by jonregalado on 1/20/18.
 */

public class BidBoardViewModel {
    
    private BidItem mBidItem;
    
    public BidBoardViewModel(BidItem bidItem) {
        mBidItem = bidItem;
    }
    
    public String getBidOfferId() {
        return mBidItem.getBidOfferId();
    }
    
    public String getBidOfferImage() {
        return mBidItem.getBidOfferImage();
    }
    
    @BindingAdapter({"image"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).centerCrop().into(view);
    }
}

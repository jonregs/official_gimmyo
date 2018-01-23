package com.gimmyo.app.bidboard.adapter;

import com.gimmyo.app.BidBoardFragment;
import com.gimmyo.app.R;
import com.gimmyo.app.bidboard.BidBoardViewModel;
import com.gimmyo.app.bidboard.BidItem;
import com.gimmyo.app.commons.Constants;
import com.gimmyo.app.databinding.BidboardItemBinding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by jonregalado on 1/20/18.
 */

public class BidItemAdapter extends RecyclerView.Adapter<BidItemAdapter.BindingHolder> {
    
    private List<BidItem> mBidItems;
    
    private OnBidBoardItemSelected mListener;
    
    public BidItemAdapter(List<BidItem> bidItems, Context context) {
        mBidItems = bidItems;
        
        mListener = (OnBidBoardItemSelected) context;
    }
    
    
    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        
        BidboardItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.bidboard_item, parent, false);
        
        return new BindingHolder(binding);
    }
    
    @Override
    public void onBindViewHolder(BidItemAdapter.BindingHolder holder, final int position) {
        
        BidboardItemBinding binding = holder.binding;
        binding.setBidItem(new BidBoardViewModel(mBidItems.get(position)));
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(Constants.TAG, "Inside onBindViewHolder, item clicked ");
                mListener.OnBidBoardItemSelected(mBidItems.get(position));
            }
        });
        
    }
    
    @Override
    public int getItemCount() {
        return mBidItems.size();
    }
    
    public static class BindingHolder extends RecyclerView.ViewHolder {
        
        private BidboardItemBinding binding;
        
        public BindingHolder(BidboardItemBinding binding) {
            super(binding.bidCard);
            this.binding = binding;
        }
    }
    
    public interface OnBidBoardItemSelected {
        void OnBidBoardItemSelected(BidItem bidItem);
    }
}
package com.gimmyo.app;

import com.gimmyo.app.bidboard.BidBoardResult;
import com.gimmyo.app.bidboard.BidItem;
import com.gimmyo.app.bidboard.adapter.BidItemAdapter;
import com.gimmyo.app.commons.Constants;
import com.gimmyo.app.rest.ApiClient;
import com.gimmyo.app.rest.ApiInterface;
import com.google.gson.GsonBuilder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jonregalado on 1/19/18.
 */

public class BidBoardFragment extends Fragment {
    
    public static final String ARG_PAGE = "ARG_PAGE";
    private String mPage;
    BidItemAdapter bidItemAdapter;
    
    public static Fragment newInstance(String page) {
        Bundle args = new Bundle();
        args.putString(ARG_PAGE, page);
        BidBoardFragment fragment = new BidBoardFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getString(ARG_PAGE);
        
        loadBidData(mPage);
    }
    
    private void loadBidData(String mPage) {
        String url_path = null;
        
        if (url_path == null) {
            switch (mPage) {
                case Constants.HOME_KEY:
                    url_path = "bids";
                    break;
                case Constants.ADD_BIDS_KEY:
                    url_path = "bids";
                    break;
                case Constants.PROFILE_KEY:
                    url_path = "bids";
                    break;
                case Constants.MORE_KEY:
                    url_path = "bids";
                    break;
                default:
                    break;
            }
            fetchBidData(url_path);
        }
    }
    
    private void fetchBidData(String url_path) {
        ApiInterface apiService = ApiClient.getClient()
                .create(ApiInterface.class);
    
        Log.i(Constants.TAG, "fetchBidData: " + url_path);
        
        Call<BidBoardResult> call = apiService.getBidItems(url_path);
        call.enqueue(new Callback<BidBoardResult>() {
            @Override
            public void onResponse(Call<BidBoardResult> call, Response<BidBoardResult> response) {
                List<BidItem> bidItems = response.body()
                        .getResults();
                Log.i(Constants.TAG, "onResponse: " + new GsonBuilder().setPrettyPrinting()
                        .create()
                        .toJson(bidItems));
                
                bidItemAdapter = new BidItemAdapter(bidItems, getContext());
                final RecyclerView recyclerView = getView().findViewById(R.id.main_bidboard);
                
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(bidItemAdapter);
                
            }
            
            @Override
            public void onFailure(Call<BidBoardResult> call, Throwable t) {
                Log.e(Constants.TAG, t.toString());
            }
        });
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        
        return view;
    }
}

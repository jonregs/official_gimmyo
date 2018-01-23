package com.gimmyo.app;

import com.gimmyo.app.BidDetailsPage.BidDetailsPage;
import com.gimmyo.app.bidboard.BidItem;
import com.gimmyo.app.bidboard.adapter.BidItemAdapter;
import com.gimmyo.app.commons.Constants;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, BidItemAdapter.OnBidBoardItemSelected{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TabLayout tabLayout = findViewById(R.id.bottom_nav);
    
        Fragment fragment = BidBoardFragment.newInstance(Constants.HOME_KEY);
        replaceFragment(fragment, Constants.HOME_KEY);
        
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Add Bids"));
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("More"));
        
        tabLayout.addOnTabSelectedListener(this);
    }
    
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Fragment fragment;
        String fragmentTag;
            switch (tab.getPosition()) {
                case 0:
                    fragment = BidBoardFragment.newInstance(Constants.HOME_KEY);
                    fragmentTag = Constants.HOME_KEY;
                    replaceFragment(fragment, fragmentTag);
                    break;
                case 1:
                    fragment = BidBoardFragment.newInstance(Constants.ADD_BIDS_KEY);
                    fragmentTag = Constants.ADD_BIDS_KEY;
                    replaceFragment(fragment, fragmentTag);
                    break;
                case 2:
                    fragment = BidBoardFragment.newInstance(Constants.PROFILE_KEY);
                    fragmentTag = Constants.PROFILE_KEY;
                    replaceFragment(fragment, fragmentTag);
                    break;
                case 3:
                    fragment = BidBoardFragment.newInstance(Constants.MORE_KEY);
                    fragmentTag = Constants.MORE_KEY;
                    replaceFragment(fragment, fragmentTag);
                    break;
                default:
                    break;
            }
        }
    
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    
    }
    
    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    
    }
    
    private void replaceFragment(Fragment fragment, String fragmentTag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, fragmentTag);
        fragmentTransaction.commit();
    }
    
    @Override
    public void OnBidBoardItemSelected(BidItem bidItem) {
        Log.i(Constants.TAG, "OnBidBoardItemSelected: " + bidItem.getBidOfferId());
        Intent intent = new Intent(this, BidDetailsPage.class);
        startActivity(intent);
    }
}

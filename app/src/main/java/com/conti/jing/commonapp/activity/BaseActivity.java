package com.conti.jing.commonapp.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.conti.jing.commonapp.R;
import com.conti.jing.commonapp.widget.WithBackTitleBar;

/**
 * Created by jing on 16/9/23.
 */
public abstract class BaseActivity extends Activity {

    private LinearLayout mParentLinearLayout;
    private ViewGroup mRootViewGroup;
    private LayoutInflater mLayoutInflater;
    private View mContainerView;
    private WithBackTitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initContentView(R.layout.activity_base);
        loadData();
    }

    private void loadData() {
        Runnable dataRunnable = new Runnable() {
            @Override
            public void run() {
                loadDataFromApi();
            }
        };
        Thread dataThread = new Thread(dataRunnable);
        dataThread.start();
    }

    private void initContentView(int layoutResId) {
        mRootViewGroup = (ViewGroup) findViewById(android.R.id.content);
        mRootViewGroup.removeAllViews();
        mParentLinearLayout = new LinearLayout(this);
        mParentLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mRootViewGroup.addView(mParentLinearLayout);
        mLayoutInflater = LayoutInflater.from(this);
        mContainerView = mLayoutInflater.inflate(layoutResId, mParentLinearLayout, true);
        mTitleBar = (WithBackTitleBar) mContainerView.findViewById(R.id.wbtb_title_bar);
    }

    protected WithBackTitleBar getTitleBar() {
        if (mTitleBar != null) {
            return mTitleBar;
        }
        return null;
    }

    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        Configuration configuration = new Configuration();
        configuration.setToDefaults();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, displayMetrics);
        return resources;
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mParentLinearLayout.addView(view, params);
    }

    @Override
    public void setContentView(int layoutResID) {
        mLayoutInflater.inflate(layoutResID, mParentLinearLayout, true);
    }

    @Override
    public void setContentView(View view) {
        mParentLinearLayout.addView(view);
    }

    protected abstract void loadDataFromApi();

}

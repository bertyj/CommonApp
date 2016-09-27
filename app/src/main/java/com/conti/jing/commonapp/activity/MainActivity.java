package com.conti.jing.commonapp.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.conti.jing.commonapp.R;
import com.conti.jing.commonapp.widget.WithBackTitleBar;

/**
 * Created by jing on 16/9/27.
 */
public class MainActivity extends BaseActivity implements WithBackTitleBar.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTitleBar().showBackButton();
        getTitleBar().showEditButton();
        getTitleBar().setTitleContent("MainActivity");
//        getTitleBar().setVisibility(View.GONE);
        setContentView(R.layout.activity_main);
        getTitleBar().setOnClickListener(this);
    }

    @Override
    public void loadDataFromApi() {

    }

    @Override
    public void loadEditEvent() {
        Toast.makeText(this, "MainActivity Edit", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadBackEvent() {
        finish();
    }

}

package com.conti.jing.commonapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.conti.jing.commonapp.R;

/**
 * Created by jing on 16/9/23.
 */
public class WithBackTitleBar extends LinearLayout implements View.OnClickListener {

    private Button mBtnBack;
    private Button mBtnEdit;
    private TextView mTvContent;
    private OnClickListener mClickListener;

    public WithBackTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.with_back_title_bar, this);
        initView(view);
    }

    private void initView(View view) {
        mBtnBack = (Button) view.findViewById(R.id.btn_title_back);
        mBtnEdit = (Button) view.findViewById(R.id.btn_title_edit);
        mTvContent = (TextView) view.findViewById(R.id.tv_title_content);
        mBtnBack.setVisibility(View.INVISIBLE);
        mBtnEdit.setVisibility(View.INVISIBLE);
    }

    public void showBackButton() {
        mBtnBack.setVisibility(View.VISIBLE);
        mBtnBack.setOnClickListener(this);
    }

    public void showEditButton() {
        mBtnEdit.setVisibility(View.VISIBLE);
        mBtnEdit.setOnClickListener(this);
    }

    public void setTitleContent(String title) {
        mTvContent.setText(title);
    }

    public void setTitleContent(int titleResId) {
        mTvContent.setText(titleResId);
    }

    public void setOnClickListener(OnClickListener listener) {
        mClickListener = listener;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_title_back:
                if (mClickListener != null) {
                    mClickListener.loadBackEvent();
                }
                break;
            case R.id.btn_title_edit:
                if (mClickListener != null) {
                    mClickListener.loadEditEvent();
                }
                break;
            default:
                break;
        }
    }

    public interface OnClickListener {
        void loadEditEvent();

        void loadBackEvent();
    }

}

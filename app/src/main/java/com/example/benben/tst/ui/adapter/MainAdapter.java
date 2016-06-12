package com.example.benben.tst.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.benben.tst.R;

import java.util.List;

/**
 * Created by benben on 2016/6/7.
 * 首页的适配器
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private OnItemClickListener mListener;
    private String[] mData;

    public MainAdapter(String[] mData) {
        this.mData = mData;
    }

    private View rootView;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (rootView == null) {
            rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        }
        ViewHolder holder = new ViewHolder(rootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 点击事件
     */
    public interface OnItemClickListener {
        void ItemClickListener(View view, int position);

        void ItemLongClickListener(View view, int position);
    }

    public void setItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

}

package me.texy.treeview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xinyuanzhong on 2017/4/21.
 */

public class BaseNodeAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<TreeNode> treeNodeList;

    public BaseNodeAdapter(Context context, List<TreeNode> treeNodeList) {
        this.context = context;
        this.treeNodeList = treeNodeList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return treeNodeList.get(0).getViewHolder();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        treeNodeList.get(0).getViewHolder().bindView(holder, treeNodeList.get(position));
    }

    @Override
    public int getItemCount() {
        return treeNodeList.size();
    }
}

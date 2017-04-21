package me.texy.treeview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xinyuanzhong on 2017/4/21.
 */

public class FirstLevelViewHolder extends NodeViewHolder {

    public FirstLevelViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public View getView() {
        return null;
    }

    @Override
    public void bindView(RecyclerView.ViewHolder holder, TreeNode treeNode) {
        FirstLevelViewHolder h = (FirstLevelViewHolder)holder;

    }

    @Override
    public RecyclerView.ViewHolder newInstance(View view) {
        return new FirstLevelViewHolder(getView());
    }
}

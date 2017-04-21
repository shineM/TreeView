package me.texy.treeview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xinyuanzhong on 2017/4/20.
 */
public abstract class NodeViewHolder extends RecyclerView.ViewHolder {
    private TreeNode treeNode;

    private View nodeView;

    public NodeViewHolder(View itemView) {
        super(itemView);
    }

    public void addView(RecyclerView recyclerView) {

    }
    public abstract View getView();

    public abstract void bindView(RecyclerView.ViewHolder holder, TreeNode treeNode);

    public RecyclerView.ViewHolder newInstance(View view){
        new RecyclerView.ViewHolder(getView());
    }
}

package me.texy.treeview.treeview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zxy on 17/4/23.
 */

public abstract class NodeViewBinder extends RecyclerView.ViewHolder{
    private TreeNode treeNode;

    public NodeViewBinder(View itemView) {
        super(itemView);
    }

    public TreeNode getTreeNode() {
        return treeNode;
    }

    public void setTreeNode(TreeNode treeNode) {
        this.treeNode = treeNode;
    }

    protected abstract int getLayoutId(int level);

    protected abstract void bindView(View view, TreeNode treeNode);
}

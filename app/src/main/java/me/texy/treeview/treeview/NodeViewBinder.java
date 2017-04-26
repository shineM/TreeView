package me.texy.treeview.treeview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zxy on 17/4/23.
 */

public abstract class NodeViewBinder extends RecyclerView.ViewHolder {

    public NodeViewBinder(View itemView) {
        super(itemView);
    }

    protected abstract int getLayoutId(int level);

    protected abstract void bindView(View view, TreeNode treeNode);
}

package me.texy.treeview.treeview.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import me.texy.treeview.treeview.TreeNode;

/**
 * Created by zxy on 17/4/23.
 */

public abstract class BaseNodeViewBinder extends RecyclerView.ViewHolder {

    public BaseNodeViewBinder(View itemView) {
        super(itemView);
    }

    public abstract int getLayoutId(int level);

    public abstract void bindView(View view, TreeNode treeNode);

    public int getToggleTriggerViewId() {
        return 0;
    }

    public void onNodeToggled(View item, TreeNode treeNode, boolean expand) {
        //empty
    }
}

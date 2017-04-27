package me.texy.treeview.treeview.base;

import android.view.View;

import me.texy.treeview.treeview.base.NodeViewBinder;

/**
 * Created by xinyuanzhong on 2017/4/27.
 */

public abstract class CheckableNodeViewBinder extends NodeViewBinder {

    public CheckableNodeViewBinder(View itemView) {
        super(itemView);
    }

    public abstract int getCheckableViewId();
}

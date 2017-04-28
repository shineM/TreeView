package me.texy.treeview.treeview.base;

import android.view.View;

/**
 * Created by xinyuanzhong on 2017/4/27.
 */

public abstract class CheckableNodeViewBinder extends BaseNodeViewBinder {

    public CheckableNodeViewBinder(View itemView) {
        super(itemView);
    }

    public abstract int getCheckableViewId();
}

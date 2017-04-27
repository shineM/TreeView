package me.texy.treeview.treeview.base;

import android.view.View;

/**
 * Created by zxy on 17/4/23.
 */

public abstract class BaseNodeViewFactory {

    public abstract NodeViewBinder getNodeViewBinder(View view, int level);

}

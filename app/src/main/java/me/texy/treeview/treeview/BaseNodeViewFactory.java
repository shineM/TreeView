package me.texy.treeview.treeview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by zxy on 17/4/23.
 */

public abstract class BaseNodeViewFactory {
    public abstract NodeViewBinder getNodeViewBinder(View view, int level);
}

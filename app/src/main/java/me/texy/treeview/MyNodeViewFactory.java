package me.texy.treeview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import me.texy.treeview.treeview.BaseNodeAdapter;
import me.texy.treeview.treeview.BaseNodeViewFactory;
import me.texy.treeview.treeview.NodeViewBinder;
import me.texy.treeview.treeview.TreeNode;

/**
 * Created by zxy on 17/4/23.
 */

public class MyNodeViewFactory extends BaseNodeViewFactory {

    @Override
    public NodeViewBinder getNodeViewBinder(View view, int level) {
        switch (level){
            case 0:
                return new FirstLevelNodeViewBinder(view);
            case 1:
                return new SecondLevelNodeViewBinder(view);
            default:
                return null;
        }
    }
}

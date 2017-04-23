package me.texy.treeview;

import android.view.View;
import android.widget.TextView;

import me.texy.treeview.treeview.NodeViewBinder;
import me.texy.treeview.treeview.TreeNode;

/**
 * Created by zxy on 17/4/23.
 */

public class FirstLevelNodeViewBinder extends NodeViewBinder {

    public FirstLevelNodeViewBinder(View itemView) {
        super(itemView);
    }

    @Override
    public int getLayoutId(int level) {
        return R.layout.item_first_level;
    }

    @Override
    protected void bindView(View view, TreeNode treeNode) {
        TextView textView = (TextView) view.findViewById(R.id.node_name_view);
        textView.setText(treeNode.getValue().toString());
    }
}

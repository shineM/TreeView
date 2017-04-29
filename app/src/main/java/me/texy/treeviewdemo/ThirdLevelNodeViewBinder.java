package me.texy.treeviewdemo;

import android.view.View;
import android.widget.TextView;

import me.texy.treeviewdemo.R;
import me.texy.treeview.TreeNode;
import me.texy.treeview.base.CheckableNodeViewBinder;

/**
 * Created by zxy on 17/4/23.
 */

public class ThirdLevelNodeViewBinder extends CheckableNodeViewBinder {

    public ThirdLevelNodeViewBinder(View itemView) {
        super(itemView);
    }

    @Override
    public int getCheckableViewId() {
        return R.id.checkBox;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_third_level;
    }

    @Override
    public void bindView(View view, TreeNode treeNode) {
        TextView textView = (TextView) view.findViewById(R.id.node_name_view);
        textView.setText(treeNode.getValue().toString());
    }
}

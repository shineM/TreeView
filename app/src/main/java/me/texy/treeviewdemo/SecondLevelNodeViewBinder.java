package me.texy.treeviewdemo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.texy.treeviewdemo.R;
import me.texy.treeview.TreeNode;
import me.texy.treeview.base.CheckableNodeViewBinder;

/**
 * Created by zxy on 17/4/23.
 */

public class SecondLevelNodeViewBinder extends CheckableNodeViewBinder {

    public SecondLevelNodeViewBinder(View itemView) {
        super(itemView);
    }

    @Override
    public int getCheckableViewId() {
        return R.id.checkBox;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_second_level;
    }

    @Override
    public void bindView(View view, final TreeNode treeNode) {
        TextView textView = (TextView) view.findViewById(R.id.node_name_view);
        ImageView imageView = (ImageView) view.findViewById(R.id.arrow_img);

        textView.setText(treeNode.getValue().toString());
        imageView.setRotation(treeNode.isExpanded() ? 90 : 0);
    }

    @Override
    public void onNodeToggled(View item, TreeNode treeNode, boolean expand) {
        ImageView imageView = (ImageView) item.findViewById(R.id.arrow_img);
        if (expand) {
            imageView.animate().rotation(90).setDuration(200).start();
        } else {
            imageView.animate().rotation(0).setDuration(200).start();
        }
    }
}

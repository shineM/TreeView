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

    TextView textView;
    ImageView imageView;
    public SecondLevelNodeViewBinder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.node_name_view);
        imageView = (ImageView) itemView.findViewById(R.id.arrow_img);
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
    public void bindView(final TreeNode treeNode) {
        textView.setText(treeNode.getValue().toString());
        imageView.setRotation(treeNode.isExpanded() ? 90 : 0);
        imageView.setVisibility(treeNode.hasChild() ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void onNodeToggled(TreeNode treeNode, boolean expand) {
        if (expand) {
            imageView.animate().rotation(90).setDuration(200).start();
        } else {
            imageView.animate().rotation(0).setDuration(200).start();
        }
    }
}

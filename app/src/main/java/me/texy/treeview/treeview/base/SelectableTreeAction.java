package me.texy.treeview.treeview.base;

import java.util.List;

import me.texy.treeview.treeview.TreeNode;
import me.texy.treeview.treeview.base.BaseTreeAction;

/**
 * Created by xinyuanzhong on 2017/4/27.
 */

public interface SelectableTreeAction extends BaseTreeAction {
    void selectNode(TreeNode treeNode);

    void deselectNode(TreeNode treeNode);

    void selectAll();

    void deselectAll();

    List<TreeNode> getSelectedNodes();
}

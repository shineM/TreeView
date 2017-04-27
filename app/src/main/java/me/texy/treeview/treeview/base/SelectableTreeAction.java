package me.texy.treeview.treeview.base;

import java.util.List;

import me.texy.treeview.treeview.TreeNode;
import me.texy.treeview.treeview.base.BaseTreeAction;

/**
 * Created by xinyuanzhong on 2017/4/27.
 */

public interface SelectableTreeAction extends BaseTreeAction {
    void selectNode(TreeNode treeNode);

    void disSelectNode(TreeNode treeNode);

    void selectAll();

    void disSelectAll();

    List<TreeNode> getSelectedNodes();
}

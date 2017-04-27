package me.texy.treeview.treeview.base;

import java.util.List;

import me.texy.treeview.treeview.TreeNode;

/**
 * Created by xinyuanzhong on 2017/4/20.
 */

public interface BaseTreeAction {
    void expandAll();

    void expandNode(TreeNode treeNode);

    void expandLevel(int level);

    void collapseAll();

    void collapseNode(TreeNode treeNode);

    void collapseLevel(int level);

    void toggleNode(TreeNode treeNode);

    void deleteNode(TreeNode node);

    void addNode(TreeNode parent, TreeNode treeNode);

    List<TreeNode> getAllNodes();

}

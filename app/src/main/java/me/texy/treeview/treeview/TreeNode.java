package me.texy.treeview.treeview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinyuanzhong on 2017/4/20.
 */

public class TreeNode {
    private int level;

    private Object value;

    private TreeNode parent;

    private List<TreeNode> children;

    private NodeViewBinder viewBinder;
    private boolean expanded;
    private boolean itemClickEnable = true;

    public TreeNode(Object value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public NodeViewBinder getViewBinder() {
        return viewBinder;
    }

    public void setViewBinder(NodeViewBinder viewBinder) {
        this.viewBinder = viewBinder;
        this.viewBinder.setTreeNode(this);
    }

    public void addChild(TreeNode treeNode) {
        children.add(treeNode);
    }

    public static TreeNode root() {
        TreeNode treeNode = new TreeNode(null);
        return treeNode;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public boolean hasChild() {
        return children.size() > 0;
    }

    public boolean isItemClickEnable() {
        return itemClickEnable;
    }

    public void setItemClickEnable(boolean itemClickEnable) {
        this.itemClickEnable = itemClickEnable;
    }
}

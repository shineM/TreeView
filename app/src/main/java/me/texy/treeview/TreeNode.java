package me.texy.treeview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by xinyuanzhong on 2017/4/20.
 */

public class TreeNode {
    private int level;

    private Object value;

    private TreeNode parent;

    private List<TreeNode> children;

    private NodeViewHolder viewHolder;

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

    public NodeViewHolder getViewHolder() {
        return viewHolder;
    }

    public void setViewHolder(NodeViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }

    public RecyclerView.Adapter getAdapter(Context context) {
        return Base;
    }

    public RecyclerView.ViewHolder createViewHolder() {
        return getViewHolder().newInstance(getViewHolder().getView());
    }
}

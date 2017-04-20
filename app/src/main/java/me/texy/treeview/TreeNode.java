package me.texy.treeview;

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
}

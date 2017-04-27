package me.texy.treeview.treeview.helper;

import java.util.ArrayList;
import java.util.List;

import me.texy.treeview.treeview.TreeNode;

/**
 * Created by xinyuanzhong on 2017/4/27.
 */

public class TreeHelper {

    public static void expandAll(TreeNode node) {
        if (node == null) {
            return;
        }
        expandNode(node, true);
    }

    /**
     * Expand node and calculate the visible addition nodes.
     *
     * @param treeNode     target node to expand
     * @param includeChild should expand child
     * @return the visible addition nodes
     */
    public static List<TreeNode> expandNode(TreeNode treeNode, boolean includeChild) {
        List<TreeNode> expandChildren = new ArrayList<>();

        if (treeNode == null) {
            return expandChildren;
        }

        treeNode.setExpanded(true);

        if (!treeNode.hasChild()) {
            return expandChildren;
        }

        for (TreeNode child : treeNode.getChildren()) {
            expandChildren.add(child);

            if (includeChild || child.isExpanded()) {
                expandChildren.addAll(expandNode(child, includeChild));
            }
        }

        return expandChildren;
    }

    /**
     * Expand the same deep(level) nodes.
     *
     * @param root  the tree root
     * @param level the level to expand
     * @return
     */
    public static void expandLevel(TreeNode root, int level) {
        if (root == null) {
            return;
        }

        for (TreeNode child : root.getChildren()) {
            if (child.getLevel() == level) {
                expandNode(child, false);
            } else {
                expandLevel(child, level);
            }

        }
    }

    public static void collapseAll(TreeNode node) {
        if (node == null) {
            return;
        }
        for (TreeNode child : node.getChildren()) {
            performCollapseNode(child, true);
        }
    }

    /**
     * Collapse node and calculate the visible removed nodes.
     *
     * @param node         target node to collapse
     * @param includeChild should collapse child
     * @return the visible addition nodes before remove
     */
    public static List<TreeNode> collapseNode(TreeNode node, boolean includeChild) {
        List<TreeNode> treeNodes = performCollapseNode(node, includeChild);
        node.setExpanded(false);
        return treeNodes;
    }

    private static List<TreeNode> performCollapseNode(TreeNode node, boolean includeChild) {
        List<TreeNode> collapseChildren = new ArrayList<>();

        if (node == null) {
            return collapseChildren;
        }
        if (includeChild) {
            node.setExpanded(false);
        }
        for (TreeNode child : node.getChildren()) {
            collapseChildren.add(child);

            if (child.isExpanded()) {
                collapseChildren.addAll(performCollapseNode(child, includeChild));
            } else if (includeChild) {
                performCollapseNodeInner(child);
            }
        }

        return collapseChildren;
    }

    /**
     * Collapse node recursive without other calculation
     *
     * @param node target node to collapse
     */
    private static void performCollapseNodeInner(TreeNode node) {
        if (node == null) {
            return;
        }
        node.setExpanded(false);
        for (TreeNode child : node.getChildren()) {
            performCollapseNodeInner(child);
        }
    }

    public static void collapseLevel(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        for (TreeNode child : root.getChildren()) {
            if (child.getLevel() == level) {
                collapseNode(child, false);
            } else {
                collapseLevel(child, level);
            }
        }
    }

    public static List<TreeNode> getAllNodes(TreeNode root) {
        List<TreeNode> allNodes = new ArrayList<>();

        fillNodeList(allNodes, root);
        allNodes.remove(root);

        return allNodes;
    }

    private static void fillNodeList(List<TreeNode> treeNodes, TreeNode treeNode) {
        treeNodes.add(treeNode);

        if (treeNode.hasChild()) {
            for (TreeNode child : treeNode.getChildren()) {
                fillNodeList(treeNodes, child);
            }
        }
    }
}

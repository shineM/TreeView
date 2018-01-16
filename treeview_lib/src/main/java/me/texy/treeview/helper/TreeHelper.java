/*
 * Copyright 2016 - 2017 ShineM (Xinyuan)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
 * ANY KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under.
 */

package me.texy.treeview.helper;

import java.util.ArrayList;
import java.util.List;

import me.texy.treeview.TreeNode;

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
     * Collapse all children node recursive
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

    /**
     * Select the node and node's children,return the visible nodes
     *
     * @param treeNode
     * @param select
     * @return
     */
    public static List<TreeNode> selectNodeAndChild(TreeNode treeNode, boolean select) {
        List<TreeNode> expandChildren = new ArrayList<>();

        if (treeNode == null) {
            return expandChildren;
        }

        treeNode.setSelected(select);

        if (!treeNode.hasChild()) {
            return expandChildren;
        }

        if (treeNode.isExpanded()) {
            for (TreeNode child : treeNode.getChildren()) {
                expandChildren.add(child);

                if (child.isExpanded()) {
                    expandChildren.addAll(selectNodeAndChild(child, select));
                } else {
                    selectNodeInner(child, select);
                }
            }
        } else {
            selectNodeInner(treeNode, select);
        }
        return expandChildren;
    }

    private static void selectNodeInner(TreeNode treeNode, boolean select) {
        if (treeNode == null) {
            return;
        }
        treeNode.setSelected(select);
        if (treeNode.hasChild()) {
            for (TreeNode child : treeNode.getChildren()) {
                selectNodeInner(child, select);
            }
        }
    }

    /**
     * Select parent when all the brothers have been selected, otherwise deselect parent,
     * and check the grand parent recursive.
     *
     * @param treeNode
     * @param select
     * @return
     */
    public static List<TreeNode> selectParentIfNeedWhenNodeSelected(TreeNode treeNode, boolean select) {
        List<TreeNode> impactedParents = new ArrayList<>();
        if (treeNode == null) {
            return impactedParents;
        }

        //ensure that the node's level is bigger than 1(first level is 1)
        TreeNode parent = treeNode.getParent();
        if (parent == null || parent.getParent() == null) {
            return impactedParents;
        }

        List<TreeNode> brothers = parent.getChildren();
        int selectedBrotherCount = 0;
        for (TreeNode brother : brothers) {
            if (brother.isSelected()) selectedBrotherCount++;
        }

        if (select && selectedBrotherCount == brothers.size()) {
            parent.setSelected(true);
            impactedParents.add(parent);
            impactedParents.addAll(selectParentIfNeedWhenNodeSelected(parent, true));
        } else if(select){
            impactedParents.add(parent);
            impactedParents.addAll(selectParentIfNeedWhenNodeSelected(parent, true));
        } else if (!select && selectedBrotherCount == brothers.size() - 1) {
            // only the condition that the size of selected's brothers
            // is one less than total count can trigger the deselect
            parent.setSelected(false);
            impactedParents.add(parent);
            impactedParents.addAll(selectParentIfNeedWhenNodeSelected(parent, false));
        } else {
            // only some selected/deselected: refresh parent view
            impactedParents.add(parent);
            impactedParents.addAll(selectParentIfNeedWhenNodeSelected(parent, false));
        }
        return impactedParents;
    }

    /**
     * Get the selected nodes under current node, include itself
     *
     * @param treeNode
     * @return
     */
    public static List<TreeNode> getSelectedNodes(TreeNode treeNode) {
        List<TreeNode> selectedNodes = new ArrayList<>();
        if (treeNode == null) {
            return selectedNodes;
        }

        if (treeNode.isSelected() && treeNode.getParent() != null) selectedNodes.add(treeNode);

        for (TreeNode child : treeNode.getChildren()) {
            selectedNodes.addAll(getSelectedNodes(child));
        }
        return selectedNodes;
    }

    /**
     * Return true when the node has one selected child(recurse all children) at least,
     * otherwise return false
     *
     * @param treeNode
     * @return
     */
    public static boolean hasOneSelectedNodeAtLeast(TreeNode treeNode) {
        if (treeNode == null || treeNode.getChildren().size() == 0) {
            return false;
        }
        List<TreeNode> children = treeNode.getChildren();
        for (TreeNode child : children) {
            if (child.isSelected() || hasOneSelectedNodeAtLeast(child)) {
                return true;
            }
        }
        return false;
    }
}

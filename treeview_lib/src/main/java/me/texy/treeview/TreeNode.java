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

package me.texy.treeview;

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

    private int index;

    private boolean expanded;

    private boolean selected;

    private boolean itemClickEnable = true;

    public TreeNode(Object value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public static TreeNode root() {
        TreeNode treeNode = new TreeNode(null);
        return treeNode;
    }

    public void addChild(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        children.add(treeNode);
        treeNode.setIndex(getChildren().size());
        treeNode.setParent(this);
    }


    public void removeChild(TreeNode treeNode) {
        if (treeNode == null || getChildren().size() < 1) {
            return;
        }
        if (getChildren().indexOf(treeNode) != -1) {
            getChildren().remove(treeNode);
        }
    }

    public boolean isLastChild() {
        if (parent == null) {
            return false;
        }
        List<TreeNode> children = parent.getChildren();
        return children.size() > 0 && children.indexOf(this) == children.size() - 1;
    }

    public boolean isRoot() {
        return parent == null;
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
        if (children == null) {
            return new ArrayList<>();
        }
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
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

    public String getId() {
        return getLevel() + "," + getIndex();
    }

    private int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}

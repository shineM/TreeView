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

package me.texy.treeview.base;

import java.util.List;

import me.texy.treeview.TreeNode;

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

    // TODO: 17/4/30
    // 1.add node at position
    // 2.add slide delete or other operations

}

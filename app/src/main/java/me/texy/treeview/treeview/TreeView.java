package me.texy.treeview.treeview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xinyuanzhong on 2017/4/20.
 */

public class TreeView implements TreeAction {
    private TreeNode root;
    private Context context;

    private BaseNodeViewFactory baseNodeViewFactory;
    private RecyclerView rootView;

    public TreeView(TreeNode root, Context context) {
        this.root = root;
        this.context = context;
    }

    public BaseNodeViewFactory getBaseNodeViewFactory() {
        return baseNodeViewFactory;
    }

    public void setBaseNodeViewFactory(BaseNodeViewFactory baseNodeViewFactory) {
        this.baseNodeViewFactory = baseNodeViewFactory;
    }

    public View getView() {
        if (rootView == null) {
            rootView = buildChildrenView(root);
        }
        return rootView;
    }

    /**
     * build a RecyclerView from <code>treeNode</code>'s children
     *
     * @param treeNode target node
     * @return
     */
    @NonNull
    private RecyclerView buildChildrenView(TreeNode treeNode) {
        RecyclerView recyclerView = new RecyclerView(context);
        /**
         * disable multi touch event to prevent terrible data set error when calculate list.
         */
        recyclerView.setMotionEventSplittingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        NodeViewAdapter adapter = new NodeViewAdapter(context, treeNode.getChildren(), baseNodeViewFactory);
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    @Override
    public void expandAll() {
        if (root == null) {
            return;
        }
        for (TreeNode child : root.getChildren()) {
            expandNode(child, true, false);
        }
        refreshTreeView();
    }

    private void expandNode(TreeNode treeNode, boolean expandChild, boolean immediately) {
        if (treeNode == null) {
            return;
        }
        treeNode.setExpanded(true);

        if (expandChild) {
            for (TreeNode child : treeNode.getChildren()) {
                expandNode(child, expandChild, false);
            }
        }
        if (immediately) {
            refreshTreeView();
        }
    }

    private void refreshTreeView() {
        if (rootView != null) {
            ((NodeViewAdapter) rootView.getAdapter()).refreshView();
        }
    }

    @Override
    public void expandNode(TreeNode treeNode) {
        expandNode(treeNode, false, true);
    }

    @Override
    public void expandLevel(int level) {
        expandLevel(root, level);
        refreshTreeView();
    }

    private void expandLevel(TreeNode treeNode, int level) {
        if (treeNode == null) {
            return;
        }
        for (TreeNode child : treeNode.getChildren()) {
            if (child.getLevel() == level) {
                expandNode(child, false, false);
            } else {
                expandLevel(child, level);
            }
        }
    }

    @Override
    public void collapseAll() {

    }

    @Override
    public void collapseNode(TreeNode treeNode) {

    }

    @Override
    public void collapseLevel(int level) {

    }

    @Override
    public void toggleNode(TreeNode treeNode) {

    }

    @Override
    public void deleteNode(TreeNode node) {

    }

    @Override
    public void addNode(TreeNode parent, TreeNode treeNode) {

    }
}

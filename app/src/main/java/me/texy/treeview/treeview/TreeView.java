package me.texy.treeview.treeview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import me.texy.treeview.treeview.base.BaseNodeViewFactory;
import me.texy.treeview.treeview.base.SelectableTreeAction;
import me.texy.treeview.treeview.helper.TreeHelper;
import me.texy.treeview.treeview.listener.OnNodeToggleListener;

/**
 * Created by xinyuanzhong on 2017/4/20.
 */

public class TreeView implements SelectableTreeAction {
    private TreeNode root;

    private Context context;

    private BaseNodeViewFactory baseNodeViewFactory;

    private RecyclerView rootView;

    private TreeViewAdapter adapter;

    private OnNodeToggleListener onNodeToggleListener;

    public TreeView(@NonNull TreeNode root, @NonNull Context context) {
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
            rootView = buildRootView();
        }
        return rootView;
    }

    /**
     * build a RecyclerView from root
     *
     * @return
     */
    @NonNull
    private RecyclerView buildRootView() {
        RecyclerView recyclerView = new RecyclerView(context);
        /**
         * disable multi touch event to prevent terrible data set error when calculate list.
         */
        recyclerView.setMotionEventSplittingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new TreeViewAdapter(context, root, baseNodeViewFactory);
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    @Override
    public void expandAll() {
        if (root == null) {
            return;
        }
        TreeHelper.expandAll(root);

        //change too much data set,just notifyDataSetChanged
        refreshTreeView();
    }


    private void refreshTreeView() {
        if (rootView != null) {
            ((TreeViewAdapter) rootView.getAdapter()).refreshView();
        }
    }

    @Override
    public void expandNode(TreeNode treeNode) {
        adapter.expandNode(treeNode);
    }

    @Override
    public void expandLevel(int level) {
        TreeHelper.expandLevel(root, level);

        //change too much data set,just notifyDataSetChanged
        refreshTreeView();
    }

    @Override
    public void collapseAll() {
        if (root == null) {
            return;
        }
        TreeHelper.collapseAll(root);

        //change too much data set,just notifyDataSetChanged
        refreshTreeView();
    }

    @Override
    public void collapseNode(TreeNode treeNode) {
        adapter.collapseNode(treeNode);
    }

    @Override
    public void collapseLevel(int level) {
        TreeHelper.collapseLevel(root, level);

        //change too much data set,just notifyDataSetChanged
        refreshTreeView();
    }

    @Override
    public void toggleNode(TreeNode treeNode) {
        if (treeNode.isExpanded()) {
            collapseNode(treeNode);
        } else {
            expandNode(treeNode);
        }
    }

    @Override
    public void deleteNode(TreeNode node) {
        adapter.deleteNode(node);
    }

    @Override
    public void addNode(TreeNode parent, TreeNode treeNode) {
        parent.addChild(treeNode);

        refreshTreeView();
    }

    @Override
    public List<TreeNode> getAllNodes() {
        return TreeHelper.getAllNodes(root);
    }

    @Override
    public void selectNode(TreeNode treeNode) {

    }

    @Override
    public void disSelectNode(TreeNode treeNode) {

    }

    @Override
    public void selectAll() {

    }

    @Override
    public void disSelectAll() {

    }

    @Override
    public List<TreeNode> getSelectedNodes() {
        return null;
    }
}

package me.texy.treeview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by xinyuanzhong on 2017/4/20.
 */

public class TreeView implements TreeAction {
    private TreeNode root;
    private Context context;

    public TreeView(TreeNode root, Context context) {
        this.root = root;
        this.context = context;
    }

    public View getView() {
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new BaseNodeAdapter(context,root.getChildren()));
        return recyclerView;
    }

    @Override
    public void expandAll() {

    }

    @Override
    public void expandNode(TreeNode treeNode) {
        List<TreeNode> children = treeNode.getChildren();
        treeNode.getViewHolder();
    }

    @Override
    public void expandLevel(int level) {

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

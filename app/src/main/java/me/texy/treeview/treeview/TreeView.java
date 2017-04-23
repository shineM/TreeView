package me.texy.treeview.treeview;

import android.content.Context;
import android.support.annotation.NonNull;
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
            rootView = getChildrenView(root);
        }
        return rootView;
    }

    @NonNull
    private RecyclerView getChildrenView(TreeNode treeNode) {
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        BaseNodeAdapter adapter = new BaseNodeAdapter(context, treeNode.getChildren(), baseNodeViewFactory);
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }


    @Override
    public void expandAll() {

    }

    @Override
    public void expandNode(TreeNode treeNode) {
        treeNode.setExpanded(true);
        rootView.getAdapter().notifyDataSetChanged();
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

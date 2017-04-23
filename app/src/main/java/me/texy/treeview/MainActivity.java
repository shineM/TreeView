package me.texy.treeview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import me.texy.treeview.treeview.BaseNodeViewFactory;
import me.texy.treeview.treeview.NodeViewBinder;
import me.texy.treeview.treeview.TreeNode;
import me.texy.treeview.treeview.TreeView;

public class MainActivity extends AppCompatActivity {

    private ViewGroup viewGroup;
    private TreeNode root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = TreeNode.root();
        buildTree();
        viewGroup = (RelativeLayout) findViewById(R.id.container);
        TreeView treeView = new TreeView(root, this);
        treeView.setBaseNodeViewFactory(new MyNodeViewFactory());
        viewGroup.addView(treeView.getView());
    }

    private void buildTree() {
        for (int i = 0; i < 30; i++) {
            TreeNode treeNode = new TreeNode(new String("Parent  " + "No." + i));
            treeNode.setLevel(0);
            for (int j = 0; j < 5; j++) {
                TreeNode treeNode1 = new TreeNode(new String("Child " + "No." + j));
                treeNode1.setLevel(1);
                treeNode.addChild(treeNode1);
            }
            root.addChild(treeNode);
        }
    }

}

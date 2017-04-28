package me.texy.treeview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

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
        View view = treeView.getView();
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        viewGroup.addView(view);
        treeView.expandLevel(1);
    }

    private void buildTree() {
        for (int i = 0; i < 3; i++) {
            TreeNode treeNode = new TreeNode(new String("Parent  " + "No." + i * 100));
            treeNode.setLevel(0);
            for (int j = 0; j < 3; j++) {
                TreeNode treeNode1 = new TreeNode(new String("Child " + "No." + i * 100 + 10 * j));
                treeNode1.setLevel(1);
                for (int k = 0; k < 3; k++) {
                    TreeNode treeNode2 = new TreeNode(new String("Grand Child " + "No." + i * 100 + j * 10 + k));
                    treeNode2.setLevel(2);
                    treeNode1.addChild(treeNode2);
                }
                treeNode.addChild(treeNode1);
            }
            root.addChild(treeNode);
        }
    }

}

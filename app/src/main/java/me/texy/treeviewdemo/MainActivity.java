package me.texy.treeviewdemo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import me.texy.treeview.TreeNode;
import me.texy.treeview.TreeView;

public class MainActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    private ViewGroup viewGroup;
    private TreeNode root;
    private TreeView treeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();

        root = TreeNode.root();
        buildTree();
        treeView = new TreeView(root, this, new MyNodeViewFactory());
        View view = treeView.getView();
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        viewGroup.addView(view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.select_all:
                treeView.selectAll();
                break;
            case R.id.deselect_all:
                treeView.deselectAll();
                break;
            case R.id.expand_all:
                treeView.expandAll();
                break;
            case R.id.collapse_all:
                treeView.collapseAll();
                break;
            case R.id.expand_level:
                treeView.expandLevel(1);
                break;
            case R.id.collapse_level:
                treeView.collapseLevel(1);
                break;
            case R.id.show_select_node:
                Toast.makeText(getApplication(), getSelectedNodes(), Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private String getSelectedNodes() {
        StringBuilder stringBuilder = new StringBuilder("You have selected: ");
        List<TreeNode> selectedNodes = treeView.getSelectedNodes();
        for (int i = 0; i < selectedNodes.size(); i++) {
            if (i < 5) {
                stringBuilder.append(selectedNodes.get(i).getValue().toString() + ",");
            } else {
                stringBuilder.append("...and " + (selectedNodes.size() - 5) + " more.");
                break;
            }
        }
        return stringBuilder.toString();
    }

    private void buildTree() {
        for (int i = 0; i < 20; i++) {
            TreeNode treeNode = new TreeNode(new String("Parent  " + "No." + i));
            treeNode.setLevel(0);
            if(i != 3) { // avoids creating child nodes for "parent" 3 (which then is not a parent, so the semantic in the displayed text becomes incorrect)
                for (int j = 0; j < 10; j++) {
                    TreeNode treeNode1 = new TreeNode(new String("Child " + "No." + j));
                    treeNode1.setLevel(1);
                    if(j != 5) { // avoids creating grand child nodes for child node 5
                        // For the child node without grand children there should not be any arrow displayed.
                        // In the demo code this can be handled in method 'SecondLevelNodeViewBinder.bindView' like this:
                        // imageView.setVisibility(treeNode.hasChild() ? View.VISIBLE : View.INVISIBLE);
                        for (int k = 0; k < 5; k++) {
                            TreeNode treeNode2 = new TreeNode(new String("Grand Child " + "No." + k));
                            treeNode2.setLevel(2);
                            treeNode1.addChild(treeNode2);
                        }
                    }
                    treeNode.addChild(treeNode1);
                }
            }
            root.addChild(treeNode);
        }
    }

    private void setLightStatusBar(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            getWindow().setStatusBarColor(Color.WHITE);
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
        }
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewGroup = (RelativeLayout) findViewById(R.id.container);
        setSupportActionBar(toolbar);
        setLightStatusBar(viewGroup);
    }
}

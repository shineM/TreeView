# TreeView
An android tree view with high performance and rich functions
## Usage
**添加依赖**
```groovy
compile 'me.texy.treeview:treeview_lib:1.0.1'
```
**实现BaseNodeViewBinder**

Sample：
```java
public class FirstLevelNodeViewBinder extends BaseNodeViewBinder {
  public FirstLevelNodeViewBinder(View itemView) { 
    super(itemView);  
  }
  
  @Override
  public int getLayoutId() {
    return R.layout.item_first_level;
  }
  
  @Override
  public void bindView(View view, final TreeNode treeNode) {
    TextView textView = (TextView) view.findViewById(R.id.node_name_view)
    textView.setText(treeNode.getValue().toString());
  }
}

SecondLevelNodeViewBinder
ThirdLevelNodeViewBinder
.
.
.
```
如果需要用选择功能则继承自CheckableNodeViewBinder

**实现BaseNodeViewFactory**

Sample：
```java
public class MyNodeViewFactory extends BaseNodeViewFactory {
  @Override
  public BaseNodeViewBinder getNodeViewBinder(View view, int level) {
    switch (level) {
      case 0:
        return new FirstLevelNodeViewBinder(view);
      case 1:
        return new SecondLevelNodeViewBinder(view);
      case 2:
        return new ThirdLevelNodeViewBinder(view);
      default:
        return null;
    }
  }
}
```

**生成TreeView**

Sample:
```java
TreeNode root = TreeNode.root();
//build the tree as you want
for (int i = 0; i < 5; i++) {
  TreeNode treeNode = new TreeNode(new String("Child " + "No." + i));
  treeNode.setLevel(0);
  root.addChild(treeNode);
}
View treeView = new TreeView(root, context, new MyNodeViewFactory()).getView();
//add to view group where you want 
```

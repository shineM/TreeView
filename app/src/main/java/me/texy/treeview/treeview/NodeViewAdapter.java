package me.texy.treeview.treeview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinyuanzhong on 2017/4/21.
 */

public class NodeViewAdapter extends RecyclerView.Adapter {

    private Context context;
    /**
     * Whole node list,include expanded and collapsed.
     */
    private List<TreeNode> treeNodeList;
    /**
     * The current data set of Adapter,which means excluding the collapsed nodes.
     */
    private List<TreeNode> expandedNodeList;
    /**
     * The binder factory.A binder provide the layoutId which needed in method
     * <code>onCreateViewHolder</code> and the way how to render ViewHolder.
     */
    private BaseNodeViewFactory baseNodeViewFactory;
    /**
     * This parameter make no sense just for avoiding IllegalArgumentException of ViewHolder's
     * constructor.
     */
    private View EMPTY_PARAMETER;

    public NodeViewAdapter(Context context, List<TreeNode> treeNodeList,
                           @NonNull BaseNodeViewFactory baseNodeViewFactory) {
        this.context = context;
        this.treeNodeList = treeNodeList;
        this.baseNodeViewFactory = baseNodeViewFactory;

        this.EMPTY_PARAMETER = new View(context);
        this.expandedNodeList = new ArrayList<>();

        buildExpandedNodeList();
    }

    private void buildExpandedNodeList() {
        expandedNodeList.clear();

        for (TreeNode child : treeNodeList) {
            insertNode(child);
        }
    }

    private void insertNode(TreeNode treeNode) {
        expandedNodeList.add(treeNode);

        if (!treeNode.hasChild()) {
            return;
        }

        if (treeNode.isExpanded()) {
            for (TreeNode child : treeNode.getChildren()) {
                insertNode(child);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return expandedNodeList.get(position).getLevel();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int level) {
        View view = LayoutInflater.from(context).inflate(baseNodeViewFactory
                .getNodeViewBinder(EMPTY_PARAMETER, level).getLayoutId(level), parent, false);

        return baseNodeViewFactory.getNodeViewBinder(view, level);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final View nodeView = holder.itemView;
        final TreeNode treeNode = expandedNodeList.get(position);
        final NodeViewBinder viewBinder = getNodeBinder(treeNode);

        if (treeNode.isItemClickEnable()) {
            nodeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    treeNode.setExpanded(!treeNode.isExpanded());
                    if (treeNode.isExpanded()) {
                        insertChildrenToList(treeNode, true);
                    } else {
                        removeChildrenFromList(treeNode, true);
                    }
                }
            });
        }


        viewBinder.bindView(nodeView, treeNode);
    }

    private void removeChildrenFromList(TreeNode treeNode, boolean refreshImmediately) {
        if (!treeNode.hasChild()) {
            return;
        }

        int currentIndex = expandedNodeList.indexOf(treeNode);
        int preTotalCount = expandedNodeList.size();
        int preNextBrotherNodeIndex = -1;
        TreeNode nextBrotherNode = null;

        if (treeNode.getParent() != null &&
                treeNode.getParent().getChildren().size() > currentIndex + 1) {
            nextBrotherNode = treeNode.getParent().getChildren().get(currentIndex + 1);
            preNextBrotherNodeIndex = expandedNodeList.indexOf(nextBrotherNode);
        }

        for (TreeNode child : treeNode.getChildren()) {
            removeNode(child);
        }

        if (refreshImmediately) {
            notifyItemRangeRemoved(currentIndex + 1, nextBrotherNode == null
                    ? preTotalCount - expandedNodeList.size()
                    : preNextBrotherNodeIndex - currentIndex);
        }

    }

    private void removeNode(TreeNode treeNode) {
        if (treeNode.isExpanded()) {
            removeChildrenFromList(treeNode, false);
        }
        expandedNodeList.remove(treeNode);
    }

    private void insertChildrenToList(TreeNode treeNode, boolean refreshImmediately) {
        if (!treeNode.hasChild() || !treeNode.isExpanded()) {
            return;
        }

        TreeNode nextBrotherNode = null;
        int currentIndex = expandedNodeList.indexOf(treeNode);
        if (currentIndex < expandedNodeList.size() - 1) {
            nextBrotherNode = expandedNodeList.get(currentIndex + 1);
        }

        for (TreeNode child : treeNode.getChildren()) {
            insertNodeAtIndex(child, currentIndex + 1);
        }

        if (refreshImmediately) {
            notifyItemRangeInserted(currentIndex + 1, nextBrotherNode == null
                    ? expandedNodeList.size() - currentIndex
                    : expandedNodeList.indexOf(nextBrotherNode) - currentIndex);
        }
    }

    private void insertNodeAtIndex(TreeNode treeNode, int nodeIndex) {
        expandedNodeList.add(nodeIndex, treeNode);

        insertChildrenToList(treeNode, false);
    }

    private NodeViewBinder getNodeBinder(TreeNode treeNode) {
        return baseNodeViewFactory.getNodeViewBinder(EMPTY_PARAMETER, treeNode.getLevel());
    }

    @Override
    public int getItemCount() {
        return expandedNodeList == null ? 0 : expandedNodeList.size();
    }

    public void refreshView() {
        buildExpandedNodeList();
        notifyDataSetChanged();
    }
}

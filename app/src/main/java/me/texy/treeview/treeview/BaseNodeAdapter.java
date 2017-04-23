package me.texy.treeview.treeview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by xinyuanzhong on 2017/4/21.
 */

public class BaseNodeAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<TreeNode> treeNodeList;
    private BaseNodeViewFactory baseNodeViewFactory;
    private View EMPTY_PARAMETER;

    public BaseNodeAdapter(Context context, List<TreeNode> treeNodeList,
                           @NonNull BaseNodeViewFactory baseNodeViewFactory) {
        this.context = context;
        this.treeNodeList = treeNodeList;
        this.baseNodeViewFactory = baseNodeViewFactory;
        this.EMPTY_PARAMETER = new View(context);
        initNodesBinder();
    }

    private void initNodesBinder() {
        for (TreeNode node : treeNodeList) {
            node.setViewBinder(baseNodeViewFactory.getNodeViewBinder(EMPTY_PARAMETER, node.getLevel()));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TreeNode sampleNode = treeNodeList.get(0);
        View view = LayoutInflater.from(context).inflate(
                sampleNode.getViewBinder().getLayoutId(sampleNode.getLevel()), parent, false);

        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);
        container.addView(view);

        return baseNodeViewFactory.getNodeViewBinder(container, sampleNode.getLevel());
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final TreeNode treeNode = treeNodeList.get(position);
        final ViewGroup nodeContainer = (ViewGroup) holder.itemView;
        final NodeViewBinder viewBinder = treeNodeList.get(0).getViewBinder();

        if (treeNode.getParent() != null && treeNode.getParent().getParent() != null) {
//            holder.setIsRecyclable(false);
        }

        treeNode.setViewBinder((NodeViewBinder) holder);

        if (treeNode.isItemClickEnable()) {
            final View view = nodeContainer.getChildAt(0);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    treeNode.setExpanded(!treeNode.isExpanded());
                    notifyItemChanged(position);
                }
            });
        }

        if (treeNode.hasChild()) {
            boolean isAlreadyExpanded = nodeContainer.getChildCount() > 1;
            if (treeNode.isExpanded()) {
                if (!isAlreadyExpanded) {
                    RecyclerView childrenView = buildChildrenView(treeNode);
                    nodeContainer.addView(childrenView);
                }
            } else {
                if (isAlreadyExpanded) {
                    final View view = nodeContainer.getChildAt(1);
                    nodeContainer.removeView(view);
                }
            }
        }

        viewBinder.bindView(holder.itemView, treeNode);
    }

    @NonNull
    private RecyclerView buildChildrenView(TreeNode treeNode) {
        RecyclerView childrenView = new RecyclerView(context);
        childrenView.setNestedScrollingEnabled(false);
        childrenView.setFocusable(false);
        childrenView.getItemAnimator().setChangeDuration(0);
        childrenView.setLayoutManager(new LinearLayoutManager(context));
        BaseNodeAdapter adapter = new BaseNodeAdapter(context, treeNode.getChildren(), baseNodeViewFactory);
        childrenView.setAdapter(adapter);
        return childrenView;
    }

    @Override
    public int getItemCount() {
        return treeNodeList == null ? 0 : treeNodeList.size();
    }

}

package me.texy.treeview.treeview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by xinyuanzhong on 2017/4/21.
 */

public class BaseNodeAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<TreeNode> treeNodeList;
    private BaseNodeViewFactory baseNodeViewFactory;
    private View EMPTY_PARAMETOR;

    public BaseNodeAdapter(Context context, List<TreeNode> treeNodeList, BaseNodeViewFactory baseNodeViewFactory) {
        this.context = context;
        this.treeNodeList = treeNodeList;
        this.baseNodeViewFactory = baseNodeViewFactory;
        this.EMPTY_PARAMETOR = new View(context);
        initNodesBinder();
    }

    private void initNodesBinder() {
        for (TreeNode node : treeNodeList) {
            node.setViewBinder(baseNodeViewFactory.getNodeViewBinder(EMPTY_PARAMETOR, node.getLevel()));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(
                treeNodeList.get(0).getViewBinder().getLayoutId(treeNodeList.get(0).getLevel()), parent, false);

        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);
        container.addView(view);

        return baseNodeViewFactory.getNodeViewBinder(container, treeNodeList.get(0).getLevel());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final TreeNode treeNode = treeNodeList.get(position);
        final ViewGroup nodeContainer = (ViewGroup) holder.itemView;

        treeNode.setViewBinder((NodeViewBinder) holder);

        if (treeNode.isItemClickEnable()) {
            View view = nodeContainer.getChildAt(0);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("onBindViewHolder", "点击了树形结构" + treeNode.getValue().toString());
                    Log.d("onBindViewHolder", "点击position" + position);

                    treeNode.setExpanded(!treeNode.isExpanded());
                    notifyDataSetChanged();
                }
            });
        }

        if (treeNode.hasChild()) {
            boolean isAlreadyExpanded = nodeContainer.getChildCount() > 1;
            if (treeNode.isExpanded()) {
                if (!isAlreadyExpanded) {
                    Log.d("onBindViewHolder", "展开树形结构" + treeNode.getValue().toString());
                    RecyclerView childrenView = new RecyclerView(context);
                    childrenView.setLayoutManager(new LinearLayoutManager(context));
                    BaseNodeAdapter adapter = new BaseNodeAdapter(context, treeNode.getChildren(), baseNodeViewFactory);
                    childrenView.setAdapter(adapter);
                    nodeContainer.addView(childrenView);
                }
            } else {
                if (isAlreadyExpanded) {
                    nodeContainer.removeViewAt(1);
                }
            }
        }

        treeNodeList.get(0).getViewBinder().bindView(
                holder.itemView, treeNode);
    }

    @Override
    public int getItemCount() {
        return treeNodeList.size();
    }

    public static class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

    }
}

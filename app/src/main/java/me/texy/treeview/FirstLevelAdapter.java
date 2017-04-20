package me.texy.treeview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinyuanzhong on 2017/4/20.
 */

public class FirstLevelAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<String> stringList;

    public FirstLevelAdapter(Context context) {
        this.context = context;
        stringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            stringList.add("");
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_first_level, parent, false);
        return new FirstLevelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final FirstLevelViewHolder h = (FirstLevelViewHolder) holder;
        h.nodeContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h.childContainer.addView(new RecyclerView(context));
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringList == null ? 0 : stringList.size();
    }

    public static class FirstLevelViewHolder extends RecyclerView.ViewHolder {
        private ViewGroup nodeContainer;
        private ViewGroup childContainer;
        public FirstLevelViewHolder(View itemView) {
            super(itemView);
            nodeContainer = (ViewGroup) itemView.findViewById(R.id.node_container);
            childContainer = (ViewGroup) itemView.findViewById(R.id.child_container);
        }
    }
}

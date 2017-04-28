package me.texy.treeview.treeview.animator;


import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;

/**
 * Created by xinyuanzhong on 2017/4/28.
 */

public class TreeItemAnimator extends DefaultItemAnimator {
    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        super.animateAdd(holder);
        ViewCompat.setAlpha(holder.itemView, 1);
        return true;
    }
}

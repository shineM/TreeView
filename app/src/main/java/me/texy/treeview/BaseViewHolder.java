package me.texy.treeview;


import android.view.View;

/**
 * Created by xinyuanzhong on 2017/4/20.
 */

public abstract class BaseViewHolder<E> {
    public abstract View getView();

    public abstract void bind(View view, E value);
}

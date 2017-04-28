package me.texy.treeview;

import android.view.View;

import me.texy.treeview.treeview.base.BaseNodeViewFactory;
import me.texy.treeview.treeview.base.BaseNodeViewBinder;

/**
 * Created by zxy on 17/4/23.
 */

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

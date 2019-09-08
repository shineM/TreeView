package me.texy.treeviewdemo;

import android.view.View;

import me.texy.treeview.base.BaseNodeViewBinder;
import me.texy.treeview.base.BaseNodeViewFactory;


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

    @Override
    public int getNodeLayoutId(int level) {
        switch (level) {
            case 0:
                return R.layout.item_first_level;
            case 1:
                return R.layout.item_second_level;
            case 2:
                return R.layout.item_third_level;
            default:
                return 0;
        }
    }
}

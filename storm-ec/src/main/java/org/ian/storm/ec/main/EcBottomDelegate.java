package org.ian.storm.ec.main;

import android.graphics.Color;

import org.ian.storm.delegates.bottom.BaseBottomDelegate;
import org.ian.storm.delegates.bottom.BottomItemDelegate;
import org.ian.storm.delegates.bottom.BottomTabBean;
import org.ian.storm.delegates.bottom.ItemBuilder;
import org.ian.storm.ec.main.cart.CartDelegate;
import org.ian.storm.ec.main.discover.DiscoverDelegate;
import org.ian.storm.ec.main.index.IndexDelegate;
import org.ian.storm.ec.main.sort.SortDelegate;
import org.ian.storm.ec.main.user.UserDelegate;

import java.util.LinkedHashMap;

/**
 * Created by ian on 2017/9/1.
 */

public class EcBottomDelegate extends BaseBottomDelegate {


    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean,BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","主页"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}","分类"),new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"),new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new CartDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"),new UserDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        //设置起始页面
        return 0;
    }

    @Override
    public int setClickedColor() {
        //设置点击后图标颜色
        return Color.parseColor("#ffff8800");
    }
}

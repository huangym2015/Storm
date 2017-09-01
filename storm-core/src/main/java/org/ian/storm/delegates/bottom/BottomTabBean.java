package org.ian.storm.delegates.bottom;

/**
 * Created by ian on 2017/9/1.
 */
//存储tab
public final class BottomTabBean {
    private final CharSequence ICON;
    private final CharSequence TITLE;

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }

    public BottomTabBean(CharSequence icon, CharSequence title) {

        this.ICON = icon;
        this.TITLE = title;
    }
}

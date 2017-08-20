package org.ian.storm.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by ian on 2017/8/15.
 */

public enum EcIcons implements Icon{
    icon_ceshi('\ue605');

    private char character;

    EcIcons(char character){
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}

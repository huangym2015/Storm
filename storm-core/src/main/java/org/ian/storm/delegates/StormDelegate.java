package org.ian.storm.delegates;

/**
 * Created by ian on 2017/8/16.
 */

public abstract class StormDelegate extends PermissionCheckerDelegate {
    @SuppressWarnings("unchecked")
    public <T extends StormDelegate> T getParentDelegate(){
        return (T) getParentFragment();
    }
}

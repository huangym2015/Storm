package org.ian.storm.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by ian on 2017/8/28.
 */

public class ReleaseOpenHelper extends DaoMaster.OpenHelper {
    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}

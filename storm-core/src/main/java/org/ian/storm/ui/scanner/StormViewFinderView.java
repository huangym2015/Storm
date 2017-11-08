package org.ian.storm.ui.scanner;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * Created by ian on 2017/10/19.
 */

public class StormViewFinderView extends ViewFinderView {
    public StormViewFinderView(Context context) {
        this(context,null);
    }

    public StormViewFinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mSquareViewFinder=true;
        mBorderPaint.setColor(Color.YELLOW);
        mLaserPaint.setColor(Color.YELLOW);
    }
}

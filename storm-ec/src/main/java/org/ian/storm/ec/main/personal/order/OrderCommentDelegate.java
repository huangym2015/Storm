package org.ian.storm.ec.main.personal.order;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import org.ian.storm.delegates.StormDelegate;
import org.ian.storm.ec.R;
import org.ian.storm.ec.R2;
import org.ian.storm.ui.widget.AutoPhotoLayout;
import org.ian.storm.ui.widget.StarLayout;
import org.ian.storm.util.callback.CallbackManager;
import org.ian.storm.util.callback.CallbackType;
import org.ian.storm.util.callback.IGlobalCallback;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ian on 2017/10/17.
 */

public class OrderCommentDelegate extends StormDelegate {

    @BindView(R2.id.custom_star_layout)
    StarLayout mStarLayout =null;

    @BindView(R2.id.custom_auto_photo_layout)
    AutoPhotoLayout mAutoPhotoLayout =null;

    @OnClick(R2.id.top_tv_comment_commit)
    void onClickSubmit(){
        Toast.makeText(getContext(),"评分: "+mStarLayout.getStarCount(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        mAutoPhotoLayout.setDelegate(this);
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(@Nullable Uri args) {
                        mAutoPhotoLayout.onCropTarget(args);

                    }
                });
    }
}

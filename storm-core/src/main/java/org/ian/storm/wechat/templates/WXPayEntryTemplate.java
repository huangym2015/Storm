package org.ian.storm.wechat.templates;

import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;

import org.ian.storm.activites.ProxyActivity;
import org.ian.storm.delegates.StormDelegate;
import org.ian.storm.wechat.BaseWXPayEntryActivity;

/**
 * Created by ian on 2017/8/30.
 */

public class WXPayEntryTemplate extends BaseWXPayEntryActivity {

    @Override
    protected void onPaySuccess() {
        Toast.makeText(this,"支付成功",Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0,0);

    }

    @Override
    protected void onPayFail() {
        Toast.makeText(this,"支付失败",Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0,0);

    }

    @Override
    protected void onPaCancel() {
        Toast.makeText(this,"用户取消",Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0,0);

    }

    @Override
    public void onReq(BaseReq baseReq) {

    }
}

package org.ian.storm.delegates.bottom;

import android.widget.Toast;

import org.ian.storm.R;
import org.ian.storm.app.Storm;
import org.ian.storm.delegates.StormDelegate;

/**
 * Created by ian on 2017/9/1.
 */

//具体页面
//public abstract class BottomItemDelegate  extends StormDelegate implements View.OnKeyListener{

public abstract class BottomItemDelegate  extends StormDelegate{
//
//    private long mExitTime = 0;
//    private static final int EXIT_TIME = 2000;
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        final View rootView = getView();
//        if (rootView!=null){
//            //返回当前页面，要取得焦点，否则点击操作无效
//            rootView.setFocusableInTouchMode(true);
//            rootView.requestFocus();
//            rootView.setOnKeyListener(this);
//        }
//    }
//
//    @Override
//    public boolean onKey(View v, int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
//            if (System.currentTimeMillis()-mExitTime>EXIT_TIME){
// //               Toast.makeText(getContext(),"双击退出"+getString(R.string.app_name),Toast.LENGTH_LONG).show();
//                mExitTime=System.currentTimeMillis();
//            }else {
//                _mActivity.finish(); //宿主activity
//                if(mExitTime!=0){
//                    mExitTime=0;
//                }
//            }
//            return true;
//        }
//        return false;
//    }

    //再点一次推出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME=0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis()-TOUCH_TIME<WAIT_TIME){
            _mActivity.finish();
        }else{
            TOUCH_TIME=System.currentTimeMillis();
            Toast.makeText(_mActivity,"双击退出"+ Storm.getApplicationContext().getString(R.string.app_name),Toast.LENGTH_LONG).show();
        }
        return true;
    }
}











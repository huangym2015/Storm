package org.ian.storm.ui.camera;

import android.net.Uri;

import org.ian.storm.delegates.PermissionCheckerDelegate;
import org.ian.storm.util.file.FileUtil;

/**
 * Created by ian on 2017/10/10.
 * 照相机调用类
 */

public class StormCamera {

    public static Uri createCropFile() {
        return Uri.parse(FileUtil.createFile("crop_image",
                FileUtil.getFileNameByTime("IMG", "jpg")).getPath());

    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}

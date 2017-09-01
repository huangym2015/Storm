package org.ian.myec.example.generators;

import org.ian.storm.annotations.AppRegisterGenerator;
import org.ian.storm.wechat.templates.AppRegisterTemplate;

/**
 * Created by ian on 2017/8/30.
 */
@AppRegisterGenerator(
        packageName = "org.ian.myec.example",
        registerTemplate = AppRegisterTemplate.class
)
public class AppRegsiter {
}

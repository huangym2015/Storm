package org.ian.storm.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ian on 2017/8/30.
 */

@Target(ElementType.TYPE)  //用在类上
@Retention(RetentionPolicy.SOURCE)  //编译时处理
public @interface PayEntryGenerator {

    String packageName();

    Class<?> payEntryTemplate();
}

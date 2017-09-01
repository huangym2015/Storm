package org.ian.myec.example.generators;

import org.ian.storm.annotations.PayEntryGenerator;
import org.ian.storm.wechat.templates.WXPayEntryTemplate;

/**
 * Created by ian on 2017/8/30.
 */
@PayEntryGenerator(
        packageName = "org.ian.myec.example",
        payEntryTemplate = WXPayEntryTemplate.class
)
public class WeChatPayEntry {
}

package org.ian.myec.example.generators;

import org.ian.storm.annotations.EntryGenerator;
import org.ian.storm.wechat.templates.WXEntryTemplate;

/**
 * Created by ian on 2017/8/30.
 */
@EntryGenerator(
        packageName = "org.ian.myec.example",
        entryTemplate = WXEntryTemplate.class
)
public class WeChatEntry {
}

/*
 *  Copyright 2006 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package dbgo.system.util.messages;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 系统信息处理类
* @author 戴辉辉 
* @date Jun 17, 2016 9:26:02 AM 
* @version V1.0
 */
public class Messages {
    private static final String BUNDLE_NAME = "properties.messages";//"dbgo.system.util.messages.messages";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
            .getBundle(BUNDLE_NAME);

    private Messages() {
    }

    public static String getString(String key) {
        try {
            return new String(RESOURCE_BUNDLE.getString(key).getBytes("ISO-8859-1"),"UTF-8");
        } catch (Exception e) {
            return '!' + key + '!';
        }
    }

    public static String getString(String key, String parm1) {
        try {
            return new String(MessageFormat.format(RESOURCE_BUNDLE.getString(key),
                    new Object[] { parm1 }).getBytes("ISO-8859-1"),"UTF-8");
        } catch (Exception e) {
            return '!' + key + '!';
        }
    }

    public static String getString(String key, String parm1, String parm2) {
        try {
        	return new String(MessageFormat.format(RESOURCE_BUNDLE.getString(key),
                    new Object[] { parm1, parm2 }).getBytes("ISO-8859-1"),"UTF-8");
        } catch (Exception e) {
            return '!' + key + '!';
        }
    }

    public static String getString(String key, String parm1, String parm2,
            String parm3) {
        try {
            return MessageFormat.format(RESOURCE_BUNDLE.getString(key),
                    new Object[] { parm1, parm2, parm3 });
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}

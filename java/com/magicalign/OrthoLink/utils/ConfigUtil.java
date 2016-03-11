package com.magicalign.OrthoLink.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import android.content.Context;
import android.content.res.AssetManager;

/**
 * @CopyRight: MagicAlign.com
 * @Description: 读取配置文件
 * @Author: zhounuanyun
 * @CreateTime: 2015-7-31
 *
 */
public class ConfigUtil {

	private static HashMap<String, String> mProperties;

	public static String getProperty(String key, Context context) {
		Properties properties = new Properties();
		AssetManager assetManager = context.getAssets();

		try {
			InputStream inputStream = assetManager
					.open("config/config.properties");
			properties.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties.getProperty(key);
	}

	public static void addProperty(String key, Context context) {
		if (mProperties == null) {
			mProperties = new HashMap<String, String>();
		}
		String value = getProperty(key, context);
		mProperties.put(key, value);
	}

	public static String getProperty(String key) {
		if (mProperties.containsKey(key)) {
			return mProperties.get(key);
		} else {
			return null;
		}
	}
}

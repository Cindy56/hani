/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.sys.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.game.common.mapper.JsonMapper;
import com.game.common.utils.CacheUtils;
import com.game.common.utils.SpringContextHolder;
import com.game.manager.modules.sys.dao.DictDao;
import com.game.modules.sys.entity.Dict;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 字典工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class DictUtils {

	private static DictDao dictDao = SpringContextHolder.getBean(DictDao.class);

	public static final String CACHE_DICT_MAP = "dictMap";

	public static String getDictLabel(String value, String type, String defaultValue) {
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)) {
			for (Dict dict : getDictList(type)) {
				if (type.equals(dict.getType()) && value.equals(dict.getValue())) {
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}

	public static String getDictLabels(String values, String type, String defaultValue) {
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)) {
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")) {
				valueList.add(getDictLabel(value, type, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getDictValue(String label, String type, String defaultLabel) {
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)) {
			for (Dict dict : getDictList(type)) {
				if (type.equals(dict.getType()) && label.equals(dict.getLabel())) {
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}

	public static List<Dict> getDictList(String type) {
		@SuppressWarnings("unchecked")
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>) CacheUtils.get(CACHE_DICT_MAP);
		if (dictMap == null) {
			dictMap = Maps.newHashMap();
			for (Dict dict : dictDao.findAllList(new Dict())) {
				List<Dict> dictList = dictMap.get(dict.getType());
				if (dictList != null) {
					dictList.add(dict);
				} else {
					dictMap.put(dict.getType(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}
		List<Dict> dictList = dictMap.get(type);
		if (dictList == null) {
			dictList = Lists.newArrayList();
		}
		return dictList;
	}

	/**
	 * 根据多个type 组装 数据返回
	 * @param type
	 * @param args
	 * @return
	 */
	public static List<Dict> getDictAssemblyList(String type, String... args) {
		if (null == type) {
			return null;
		}
		List<Dict> dictList = new ArrayList<Dict>();
		dictList.addAll(getDictList(type));
		if (args.length != 0) {
			for (int i = 0; i < args.length; i++) {
				dictList.addAll(getDictList(args[i]));
			}
		}
		return dictList;
	}

	/**
	 * 返回字典列表（JSON）
	 * @param type
	 * @return
	 */
	public static String getDictListJson(String type) {
		return JsonMapper.toJsonString(getDictList(type));
	}

	/**
	 * 根据类型获取字典，然后通过传入的值获取label
	 * @param type 字典类型
	 * @param value 要转换lebel的值
	 * @param value 获取不到时返回的默认值
	 * @return 返回value对应的label
	 * @author Terry
	 */
	public static String getDictLabelForList(String type, String value, String defaultValue) {
		// 如果传入的类型或者传入的值时空，直接返回默认值
		if (StringUtils.isBlank(type) || StringUtils.isBlank(value)) {
			return defaultValue;
		}

		// 获取缓存字典
		@SuppressWarnings("unchecked")
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>) CacheUtils.get(CACHE_DICT_MAP);

		// 如果缓存字典为空，从数据库获取一次，刷入缓存
		if (dictMap == null) {
			dictMap = Maps.newHashMap();
			for (Dict dict : dictDao.findAllList(new Dict())) {
				List<Dict> dictList = dictMap.get(dict.getType());
				if (dictList != null) {
					dictList.add(dict);
				} else {
					dictMap.put(dict.getType(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}

		// 从缓存字典中获取类型键值对
		List<Dict> dictList = dictMap.get(type);
		if (dictList == null) {
			return defaultValue;
		}

		// 用与传入值相等的键值对去获取label返回
		for (Dict dict : dictList) {
			if (value.equals(dict.getValue())) {
				return getDictLabel(value, type, defaultValue);
			}
		}

		// 如果没有，返回默认值
		return defaultValue;
	}

	/**
	 * 根据类型获取字典，然后通过传入的key前缀过滤列表
	 * @param type 字典类型
	 * @param key 指定的前缀
	 * @return 返回以指定前缀开头的字典列表
	 * @author Terry
	 */
	public static List<Dict> getDictLabelForStartWith(String type, String key) {
		// 如果传入的类型或者传入的值时空，直接返回一个空列表
		if (StringUtils.isBlank(type) || StringUtils.isBlank(key)) {
			List<Dict> dictList = new ArrayList<>();
			return dictList;
		}

		// 获取缓存字典
		@SuppressWarnings("unchecked")
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>) CacheUtils.get(CACHE_DICT_MAP);

		// 如果缓存字典为空，从数据库获取一次，刷入缓存
		if (dictMap == null) {
			dictMap = Maps.newHashMap();
			for (Dict dict : dictDao.findAllList(new Dict())) {
				List<Dict> dictList = dictMap.get(dict.getType());
				if (dictList != null) {
					dictList.add(dict);
				} else {
					dictMap.put(dict.getType(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}

		// 遍历过滤
		List<Dict> dictList = new ArrayList<>();
		for (Dict dict : dictMap.get(type)) {
			if (dict.getValue().startsWith(key)) {
				dictList.add(dict);
			}
		}
		return dictList;
	}
}
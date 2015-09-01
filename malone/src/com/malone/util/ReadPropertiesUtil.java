/**
 * 
 */
package com.malone.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.danga.MemCached.MemCachedClient;

/**
 * @author pengl
 * 2013-11-18
 * @版本 V1.0
 * 描述：加载项目指定配置文件至内存中
 */
@Component("readPropertiesUtil")
public class ReadPropertiesUtil {
	private Properties properties = null;
	private Logger _logger = LogManager.getLogger();
	@Resource
    private MemCachedClient memcachedClient;
	/**
	 * 加载配置文件
	 */
	public void init(){
		try {
			PropertiesExtra extra = new PropertiesExtra("needloads.properties");
			properties = extra.getProperties();
			Iterator itor = properties.entrySet().iterator();
			while(itor.hasNext()){
			    Map.Entry entry=(Map.Entry)itor.next();
			    String key = (String)entry.getKey();
			    String value = (String)entry.getValue();
			    boolean setResult = memcachedClient.set(key, new PropertiesExtra(value).getProperties());
			    if(setResult){
			    	_logger.warn("系统配置文件：【"+value+"】成功加载至Memcached缓存中...");
			    }else{
			    	_logger.warn("系统配置文件：【"+value+"】加载至Memcached缓存失败...");
			    }
			   
			} 
		}catch (Exception e) {
			_logger.warn(
						"系统读取属性配置文件出错，请检查 【needloads.properties】文件 " + e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据数据文件名和KEY值读取相应的VALUE值
	* @Title: getProperty 
	* @Description: TODO
	* @param prokey：needloads.properties文件中的key 
	* @param key：属性文件中的key 
	* @return void    返回类型 
	* @throws
	 */
	public String getProperty(String prokey,String key){
		String result = "";
		try {
			Properties pros = (Properties)memcachedClient.get(prokey);
			result = pros.getProperty(key);
		}catch (NullPointerException e) {
			_logger.error(
					"系统配置文件读取失败,指定文件或参数不存在" + e.getMessage());
		}catch (Exception e) {
			_logger.error(
					"系统配置文件读取失败,请检查原因: " + e.getMessage());
		}
		return result;
	}
}

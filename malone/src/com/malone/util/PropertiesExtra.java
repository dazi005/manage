/**
 * 
 */
package com.malone.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author pengl
 * 属性文件读取工具类
 * 2013-11-18
 */
public class PropertiesExtra {
	
	private Logger _logger = LogManager.getLogger();
	/**属性对象*/
	private Properties _properties = null;
	
	/**文件名，如果不传入文件路径默认为根*/
	private String fileName = "";
	
	/**文件路径,如：com/doone/fj1w/fj1w/login/*/
	private String path = "";
	
	public PropertiesExtra(){}
	
	public PropertiesExtra(String fileName)throws IOException{
		this.fileName = fileName;
		createProperties();
	}
	
	public PropertiesExtra(String fileName,String path)throws IOException{
		this.fileName = fileName;
		this.path     = path;
		createProperties();
	}
	
	/**
	 * <code>读取配制文件</code>
	 * @throws IOException
	 */
	private void createProperties()throws IOException{
		try{
			_properties = new Properties();
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			InputStream is = cl.getResourceAsStream(this.path+this.fileName);
			_properties.load(is);
			is.close();
		}catch(IOException iox){
			_logger.warn("读取配制文件："+this.path+this.fileName+"失败。"+iox.getMessage());
			throw iox;
		}
		catch(RuntimeException rux){
			_logger.warn("生成配制文件引用异常:"+rux.getMessage()+",配制文件路径："+this.path+this.fileName);
			throw rux;
		}
	}
	
	
	public Properties getProperties(){
		return this._properties;
	}
	
	public Object clone(){
		return this._properties.clone();
	}
	
	public Enumeration elements(){
		return this._properties.elements();
	}
	
	public void setProperty(String key,String value){
		this._properties.setProperty(key,value);
	}
	
	public String getProperty(String key){
		return this._properties.getProperty(key);
	}

	public String toString(){
		try{
		    StringBuffer sb = new StringBuffer();
		    Iterator _iterator = _properties.entrySet().iterator();
		    while(_iterator.hasNext()){
		        Map.Entry  _entry = (Map.Entry)_iterator.next();
		        sb.append(_entry.getKey());
		        sb.append("=");
		        sb.append(_entry.getValue());
		        sb.append("\n");
		    }
			
			return sb.toString();
		}catch(Exception ex){
			_logger.warn(ex.getMessage());
			return ex.getMessage();
		}
	}
}

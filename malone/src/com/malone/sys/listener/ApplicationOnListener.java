/**
 * 
 */
package com.malone.sys.listener;

import javax.annotation.Resource;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.malone.util.ReadPropertiesUtil;

/**
 * @author pengl
 * 2015-6-8下午5:06:13
 */
@Component
public class ApplicationOnListener implements ApplicationListener<ContextRefreshedEvent>{
	@Resource(name="readPropertiesUtil")
	private ReadPropertiesUtil readPropertiesUtil;
	/**
	 * 监听服务器启动
	 */
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){ //root application context
			//加载配置文件至内存中
			readPropertiesUtil.init();
		}
	}

}

/**
 * 
 */
package com.malone.sys.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import com.malone.sys.dto.SysOperLog;

/**
 * @author pengl
 * 系统操作日志管理
 * 2015-5-15下午2:01:32
 */
@Service("sysLogService")
public class SysLogService{
	
	public void InsertSysLog(SysOperLog sysOperLog ,SqlSession sqlSession){
		sqlSession.insert("com.malone.sys.log.insertSysLog", sysOperLog);
	}
}

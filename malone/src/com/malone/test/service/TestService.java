package com.malone.test.service;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import com.malone.test.dto.TestVo;

@Service("testImpl")
public class TestService{
	@Resource
	private SqlSessionFactory sessionFactory;
	
	public TestVo selectUserById(int id) {
		TestVo testbean = sessionFactory.openSession().selectOne("com.malone.test.service.TestService.selectUserById" ,id);
		return testbean;
	}

}

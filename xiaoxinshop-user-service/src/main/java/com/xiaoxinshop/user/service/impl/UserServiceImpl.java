package com.xiaoxinshop.user.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.entity.User;
import com.xiaoxinshop.mapper.UserMapper;
import com.xiaoxinshop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<User> findAll() {
		return null;
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {

		return null;
	}

    @Autowired
    JmsTemplate jmsTemplate;

	@Autowired
	Destination queueMessageDestination;

	@Autowired
	RedisTemplate redisTemplate;

	@Override
	public void createSmsCode(String phone) {


		String code =  (long) (Math.random()*1000000)+"";

		String key = "register"+phone;
		redisTemplate.boundValueOps(key).set(code);
		redisTemplate.boundValueOps(key).expire(600L, TimeUnit.SECONDS);

		Map<String,String> map = new HashMap();
		map.put("phone",phone);
		map.put("code",code);
		String mapStr = JSON.toJSONString(map);

		jmsTemplate.send(queueMessageDestination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(mapStr);
			}
		});
	}

	@Autowired
	Destination queueUpdateMessageDestination;
	@Override
	public void updateSmsCode(String phone) {

		String code =  (long) (Math.random()*1000000)+"";
		String key = "update"+phone;
		redisTemplate.boundValueOps(key).set(code);
		redisTemplate.boundValueOps(key).expire(600L, TimeUnit.SECONDS);

		Map<String,String> map = new HashMap();
		map.put("phone",phone);
		map.put("code",code);
		String mapStr = JSON.toJSONString(map);

		jmsTemplate.send(queueUpdateMessageDestination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(mapStr);
			}
		});
	}

	@Override
	public boolean checkSmsCode(String phone, String code) {
		String key = "register"+phone;
		String smsCode =(String) redisTemplate.boundValueOps(key).get();
		if (smsCode!=null && smsCode.equals(code)){
			return  true;
		}
		return false;
	}

	@Override
	public boolean checkUpdateSmsCode(String phone, String code) {
		String key = "update"+phone;
		String smsCode =(String) redisTemplate.boundValueOps(key).get();
		System.out.println(smsCode);
		if (smsCode!=null && smsCode.equals(code)){
			return  true;
		}
		return false;
	}

	/**
	 * 增加
	 */
	@Override
	public void add(User user) {
		//创建日期
		user.setCreated(new Date());
		//修改日期
		user.setUpdated(new Date());

		userMapper.insert(user);
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(User user){
		user.setUpdated(new Date());
		userMapper.updateByPrimaryKeySelective(user);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public User findById(Long id){
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public User findByUserName(String name) {
		return userMapper.selectByUserName(name);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			userMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(User user, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		return null;
	}
	
}

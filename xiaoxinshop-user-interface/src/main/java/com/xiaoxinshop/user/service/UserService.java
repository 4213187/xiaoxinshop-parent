package com.xiaoxinshop.user.service;
import java.util.List;

import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.entity.User;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface UserService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<User> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);


	/**
	 * 生成验证码
	 * @param phone
	 */
	public  void createSmsCode(String phone);

	/**
	 * 生成验证码
	 * @param phone
	 */
	public  void updateSmsCode(String phone);


	/**
	 * 校验验证码
	 * @param phone
	 * @param code
	 * @return
	 */
	public boolean  checkSmsCode(String phone,String code);

	/**
	 * 校验验证码
	 * @param phone
	 * @param code
	 * @return
	 */
	public boolean  checkUpdateSmsCode(String phone,String code);
	
	
	/**
	 * 增加
	*/
	public void add(User user);
	
	
	/**
	 * 修改
	 */
	public void update(User user);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public User findById(Long id);

	/**
	 * 通过名字查询对应的用户
	 * @param name
	 * @return
	 */
	public User findByUserName(String name);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long[] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(User user, int pageNum, int pageSize);
	
}

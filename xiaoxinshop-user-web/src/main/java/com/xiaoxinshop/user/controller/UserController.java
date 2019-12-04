package com.xiaoxinshop.user.controller;
import java.util.List;

import com.xiaoxinshop.entity.PageResult;
import com.xiaoxinshop.entity.ResultVo;
import com.xiaoxinshop.entity.User;
import com.xiaoxinshop.user.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.alibaba.dubbo.config.annotation.Reference;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserController {

	@Reference
	private UserService userService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<User> findAll(){
		return userService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows){
		return userService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public ResultVo add(@RequestBody User user,String smscode){

        boolean checkSmsCode = userService.checkSmsCode(user.getPhone(), smscode);
        if(checkSmsCode==false){
            return new ResultVo(false, "验证码输入错误！");
        }


        try {
			userService.add(user);
			return new ResultVo(true, "注册成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "注册失败");
		}
	}

	/**
	 * 发送短信验证码
	 * @param phone
	 * @return
	 */
	@RequestMapping("/sendCode")
	public ResultVo sendCode(String phone){
		//判断手机号格式

		try {
			//生成验证码 并把验证码发送给ActiveMQ 以及放入缓存
			userService.createSmsCode(phone);
			return new ResultVo(true, "验证码发送成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(true, "验证码发送失败");
		}
	}






	/**
	 * 修改
	 * @param user
	 * @return
	 */
	@RequestMapping("/update")
	public ResultVo update(@RequestBody User user){
		try {
			userService.update(user);
			return new ResultVo(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findById")
	public User findById(Long id){
		return userService.findById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public ResultVo delete(Long [] ids){
		try {
			userService.delete(ids);
			return new ResultVo(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVo(false, "删除失败");
		}
	}

	/**
	 *
	 * @param user
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody User user, int pageNum, int pageSize  ){
		return userService.findPage(user, pageNum, pageSize);
	}
	
}

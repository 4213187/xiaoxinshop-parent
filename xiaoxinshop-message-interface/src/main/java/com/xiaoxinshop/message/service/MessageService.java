package com.xiaoxinshop.message.service;

/**
 * @Author 小浩
 * @Date 2019/12/4 17:13
 * @Version 1.0
 **/
public interface MessageService {

    /**
     * 发送验证码
     * @param phone
     * @param code
     * @return
     */
  boolean  sendSms(String phone,String code);

    /**
     * 发送更新验证码
     * @param phone
     * @param code
     * @return
     */
    boolean sendUpdateSms(String phone, String code);

    /**
     * 发送验证码
     * @param phone
     * @param code
     * @return
     */
    boolean  sendNoticeSms(String phone,String code);
}

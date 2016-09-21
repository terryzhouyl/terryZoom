package com.terry.dao.impl;

import org.springframework.stereotype.Repository;

import com.terry.dao.WeixinUserDao;
import com.terry.entity.WeixinUser;

@Repository("weixinUserDaoImpl")
public class WeixinUserDaoImpl extends BaseDaoImpl<WeixinUser> implements WeixinUserDao {

}

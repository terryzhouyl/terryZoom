package com.terry.dao.impl;

import org.springframework.stereotype.Repository;

import com.terry.dao.MessageTemplateDao;
import com.terry.entity.MessageTemplate;

@Repository("messageTemplateDaoImpl")
public class MessageTemplateDaoImpl extends BaseDaoImpl<MessageTemplate> implements MessageTemplateDao{

}

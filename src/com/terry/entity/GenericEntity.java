/*******************************************************************************
 *    系统 ： 恩普勒斯框架
 *    版权 ：(C) Copyright NPLUS 2013-2014 All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于恩普勒斯内部使用，禁止转发
 ******************************************************************************/

package com.terry.entity;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.*;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
@MappedSuperclass
public class GenericEntity<T> implements Serializable {
	private static Logger logger = LoggerFactory.getLogger(GenericEntity.class);

	protected T id;

	/**
	 * 实体类ID自动生成
	 * 
	 * @return
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}

	/**
	 * 返回该类的hashCode
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(-1785541825, 1605299849)
				.appendSuper(super.hashCode()).append(this.getClass())
				.append(this.id).toHashCode();
	}

	/**
	 * 覆盖equals方法
	 * 
	 * @param obj
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj.getClass().isInstance(this) || this.getClass()
				.isInstance(obj))) {
			return false;
		}
		
		try {
			@SuppressWarnings("unchecked")
			GenericEntity<T> entity = (GenericEntity<T>) obj;
			if (entity.getId() == null && getId() == null) {
				boolean equals = true;
				Field[] fields = this.getClass().getDeclaredFields();
				
				for (Field field : fields) {
					Object value1 = field.get(this);
					Object value2 = field.get(entity);
					if (value1 == null && value2 == null){
						continue;
					}
					if (value1 != null) {
						equals = equals && value1.equals(value2);
					}

					if (!equals){
						break;
					}
				}
				
				return equals;
			}
			if (entity.getId() == null || getId() == null) {
				return false;
			}
			
			return entity.getId().equals(getId());
		} catch (Exception e) {
			logger.error("can not reflect the Entity for:{} Exception", obj
					.getClass().getName());
			return false;
		}
	}
}

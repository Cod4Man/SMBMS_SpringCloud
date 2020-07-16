package com.codeman.smbms.mapper;

import java.sql.Connection;
import java.util.List;

import com.codeman.smbms.entity.Provider;
import org.apache.ibatis.annotations.Param;

public interface ProviderMapper {
	
	/**
	 * 增加供应商
	 * @param provider
	 * @return
	 * @
	 */
	public int add(Provider provider);


	/**
	 * 通过供应商名称、编码获取供应商列表-模糊查询-providerList
	 * @param proName
	 * @return
	 * @
	 */
	public List<Provider> getProviderList(@Param("proName") String proName, @Param("proCode") String proCode);
	
	/**
	 * 通过proId删除Provider
	 * @param delId
	 * @return
	 * @
	 */
	public int deleteProviderById(@Param("delId") String delId);
	
	
	/**
	 * 通过proId获取Provider
	 * @param id
	 * @return
	 * @
	 */
	public Provider getProviderById(@Param("id") String id);
	
	/**
	 * 修改用户信息
	 * @return
	 * @
	 */
	public int modify(Provider provider);
	
	
}

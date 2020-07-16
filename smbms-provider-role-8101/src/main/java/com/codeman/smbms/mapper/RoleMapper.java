package com.codeman.smbms.mapper;


import com.codeman.smbms.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RoleMapper {

	public List<Role> getRoleList();

}

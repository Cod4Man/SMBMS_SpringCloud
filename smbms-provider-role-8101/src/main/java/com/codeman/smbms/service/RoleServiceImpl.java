package com.codeman.smbms.service;

import com.codeman.smbms.entity.Role;
import com.codeman.smbms.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Role> getRoleList() {
        List<Role> roleList = roleMapper.getRoleList();
        return roleList;
    }

	/*private SqlSession sqlSession;
	@Override
	public List<Role> getRoleList() {
        List<Role> roleList = new ArrayList<>();
        sqlSession = MyBatisUtil.getSqlSession();
        try {
             roleList = sqlSession.getMapper(RoleDao.class).getRoleList();
             sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (sqlSession != null) {
                sqlSession.rollback();
            }
        } finally {
            if (sqlSession != null) {
                MyBatisUtil.closeSqlSession(sqlSession);
            }
        }
		return roleList;
	}*/
}

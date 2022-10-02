package service.impl;

import mapper.StaffMapper;
import model.Staff;
import org.apache.ibatis.session.SqlSession;
import service.RestaurantEmpService;
import utils.SqlSessionUtil;

import java.util.List;

public class RestaurantEmpServiceImpl implements RestaurantEmpService {

    @Override
    public List<Staff> getStaffListByPrivilegeRange() {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        StaffMapper staffMapper =  sqlSession.getMapper(StaffMapper.class);
        List<Staff> staffs = staffMapper.selectStaffByPrivilegeRange(0, 9);
        SqlSessionUtil.close(sqlSession);
        return staffs;
    }

    @Override
    public boolean updatePrivilege(String staffId, String privilege) {
        return false;
    }

    @Override
    public boolean updatePosition(String staffId, String position) {
        return false;
    }
}

import mapper.StaffMapper;
import model.Staff;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.SqlSessionUtil;

import java.util.List;

public class RestaurantEmpServiceTest{
    @Test
    public void testgetStaffListByPrivilegeRange()
    {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        StaffMapper staffMapper = sqlSession.getMapper(StaffMapper.class);
        List<Staff> staffs = staffMapper.selectStaffByPrivilegeRange(0, 9);
        System.out.println(staffs);
        SqlSessionUtil.close(sqlSession);
    }

}

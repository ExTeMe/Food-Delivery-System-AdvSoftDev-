package mapper;

import model.Staff;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Staff Mapper
 * @author Hao Zeng
 * @version 1.0
 */

public interface StaffMapper {
    @Update("update staff set Privilege = #{privilege}, where Staff_ID = #{staffID}")
    int updatePrivilegeById(@Param("privilege") int privilege, @Param("staffID") int staffID);

    @Update("update staff set Position = #{position}, where Staff_ID = #{staffID}")
    int updatePositionById(@Param("position") String position, @Param("staffID") int staffID);

    Staff selectOneByStaffID(int staffID);

    List<Staff> selectStaffByPrivilegeRange(@Param("privilege1")int privilege1, @Param("privilege2")int privilege2);
}

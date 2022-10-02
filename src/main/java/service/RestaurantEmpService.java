package service;

import model.*;

import java.util.List;

/**
 * Restaurant staff Service
 * @author Hao Zeng
 * @version 1.0
 */
public interface RestaurantEmpService {
    List<Staff> getStaffListByPrivilegeRange();
    boolean updatePrivilege(String staffId, String privilege);
    boolean updatePosition(String staffId, String position);
}

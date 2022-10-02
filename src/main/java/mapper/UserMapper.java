package mapper;

import model.User;

public interface UserMapper {
    User selectById(int userId);
}

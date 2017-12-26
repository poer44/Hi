package dao;

import entity.User;
import entity.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String userid);

    int insert(User record);
    
    int countnewuser();

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);
    
    List<User> selectnew();

    User selectByPrimaryKey(String userid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	int updateByPrimaryKeySelective(String userid, String password,
			String username, String sex, String birthday, String address,
			String emotion, String email, String sign);

}
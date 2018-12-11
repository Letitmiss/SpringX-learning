package com.cong.springx.mapper;

import com.cong.springx.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.mybatis.spring.annotation.MapperScan;

/**
 * 访问数据库接口
 */
public interface UserMapper {
    /**
     *  useGeneratedKeys 设置使用主键
     *  keyColumn  数据库对应字段
     *  keyProperty java对象属性
     * @param user
     * @return
     */
    @Insert("INSERT INTO user(username,city,phone,pwd,create_time) VALUES(#{userName},#{userCity},#{phone},#{pwd},#{createTime})")
    @Options(useGeneratedKeys = true,keyProperty = "userId",keyColumn = "id")
    int insert(User user);
}

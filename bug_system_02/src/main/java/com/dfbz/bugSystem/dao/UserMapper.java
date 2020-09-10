package com.dfbz.bugSystem.dao;

import com.dfbz.bugSystem.po.User;
import java.util.List;

import com.dfbz.bugSystem.po.UserRole;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    @Select("select * from wp_user where user_name=#{userName} and password=#{password}")
    @Results({
            @Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="USER_NAME", property="userName", jdbcType=JdbcType.VARCHAR),
            @Result(column="PASSWORD", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="PROJECT_ID", property="projectId", jdbcType=JdbcType.INTEGER),
            @Result(column="TEAM_ID", property="teamId", jdbcType=JdbcType.INTEGER),
            @Result(column="REGISTER_ID", property="registerId", jdbcType=JdbcType.VARCHAR),
            @Result(column="REGISTER_DATE", property="registerDate", jdbcType=JdbcType.VARCHAR),
            @Result(column="UPDATE_ID", property="updateId", jdbcType=JdbcType.VARCHAR),
            @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.VARCHAR),
            @Result(column="DEL_FLAG", property="delFlag", jdbcType=JdbcType.INTEGER),
            @Result(column="DEL_DATE", property="delDate", jdbcType=JdbcType.VARCHAR)
    })
    User login(User user);


 

    @Insert({"<script>",
            "insert into wp_user ",
            "<trim prefix='(' suffix=')' suffixOverrides=','>",
            "<if test='userName != null'>",
               "USER_NAME,",
            "</if>",
            "<if test='password != null'>",
                "PASSWORD,",
            "</if>",
            "<if test='projectId != null'>",
                "PROJECT_ID,",
            "</if>",
            "<if test='teamId != null'>",
                "TEAM_ID,",
            "</if>",
            "<if test='registerId != null'>",
                "REGISTER_ID,",
            "</if>",
            "<if test='registerId != null'>",
                "REGISTER_DATE,",
            "</if>",
            "<if test='registerId != null'>",
                "UPDATE_ID,",
            "</if>",
            "<if test='registerId != null'>",
                "UPDATE_DATE,",
            "</if>",
            "<if test='registerId != null'>",
                "DEL_FLAG,",
            "</if>",
            "<if test='registerId != null'>",
                "DEL_DATE,",
            "</if>",
            "</trim>",

            "<trim prefix='values (' suffix=')' suffixOverrides=','>",
            "<if test='userName != null'>",
                "#{userName,jdbcType=VARCHAR},",
            "</if>",
            "<if test='password != null'>",
                "#{password,jdbcType=VARCHAR},",
            "</if>",
            "<if test='projectId != null'>",
                "#{projectId,jdbcType=INTEGER}",
            "</if>",
            "<if test='teamId != null'>",
                "#{teamId,jdbcType=INTEGER},",
            "</if>",
            "<if test='registerId != null'>",
                "#{registerId,jdbcType=VARCHAR},",
            "</if>",
            "<if test='registerId != null'>",
                "#{registerDate,jdbcType=VARCHAR},",
            "</if>",
            "<if test='registerId != null'>",
                "#{updateId,jdbcType=VARCHAR}, ",
            "</if>",
            "<if test='registerId != null'>",
                "#{updateDate,jdbcType=VARCHAR},",
            "</if>",
            "<if test='registerId != null'>",
                "#{delFlag,jdbcType=INTEGER},",
            "</if>",
            "<if test='registerId != null'>",
                "#{delDate,jdbcType=VARCHAR},",
            "</if>",
            "</trim>",
            "</script>" })
    @SelectKey(keyColumn = "user_id",keyProperty = "userId",before = false,
            statement = "select last_insert_id()", resultType = Integer.class)
    int insertSelection(User record);




    @Select({
        "select",
        "USER_ID, USER_NAME, PASSWORD, PROJECT_ID, TEAM_ID, REGISTER_ID, REGISTER_DATE, ",
        "UPDATE_ID, UPDATE_DATE, DEL_FLAG, DEL_DATE",
        "from wp_user",
        "where USER_ID = #{userId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="USER_NAME", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="PASSWORD", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="PROJECT_ID", property="projectId", jdbcType=JdbcType.INTEGER),
        @Result(column="TEAM_ID", property="teamId", jdbcType=JdbcType.INTEGER),
        @Result(column="REGISTER_ID", property="registerId", jdbcType=JdbcType.VARCHAR),
        @Result(column="REGISTER_DATE", property="registerDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_ID", property="updateId", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEL_FLAG", property="delFlag", jdbcType=JdbcType.INTEGER),
        @Result(column="DEL_DATE", property="delDate", jdbcType=JdbcType.VARCHAR)
    })
    User selectByPrimaryKey(Integer userId);


    @Select({
        "select",
        "*",
        "from wp_user where project_id=#{projectId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="USER_NAME", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="PASSWORD", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="PROJECT_ID", property="projectId", jdbcType=JdbcType.INTEGER),
        @Result(column="TEAM_ID", property="teamId", jdbcType=JdbcType.INTEGER),
        @Result(column="REGISTER_ID", property="registerId", jdbcType=JdbcType.VARCHAR),
        @Result(column="REGISTER_DATE", property="registerDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_ID", property="updateId", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEL_FLAG", property="delFlag", jdbcType=JdbcType.INTEGER),
        @Result(column="DEL_DATE", property="delDate", jdbcType=JdbcType.VARCHAR),
            @Result(column = "USER_ID",property = "role",
                    one = @One(select = "com.dfbz.bugSystem.dao.UserRoleMapper.findByUserRole"))
    })
    List<User> selectAll(Integer projectId);


    @Update({
        "update wp_user",
        "set USER_NAME = #{userName,jdbcType=VARCHAR},",
          "PASSWORD = #{password,jdbcType=VARCHAR},",
          "PROJECT_ID = #{projectId,jdbcType=INTEGER},",
          "TEAM_ID = #{teamId,jdbcType=INTEGER},",
          "REGISTER_ID = #{registerId,jdbcType=VARCHAR},",
          "REGISTER_DATE = #{registerDate,jdbcType=VARCHAR},",
          "UPDATE_ID = #{updateId,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=VARCHAR},",
          "DEL_FLAG = #{delFlag,jdbcType=INTEGER},",
          "DEL_DATE = #{delDate,jdbcType=VARCHAR}",
        "where USER_ID = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);
}
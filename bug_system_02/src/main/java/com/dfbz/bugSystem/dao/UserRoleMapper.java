package com.dfbz.bugSystem.dao;

import com.dfbz.bugSystem.po.Role;
import com.dfbz.bugSystem.po.UserRole;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserRoleMapper {
    @Delete({
        "delete from wp_user_role_ref",
        "where USER_ID = #{userId,jdbcType=INTEGER}",
          "and ROLE_ID = #{roleId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    @Insert({
        "insert into wp_user_role_ref (USER_ID, ROLE_ID, ",
        "REGISTER_ID, REGISTER_DATE,DEL_FLAG)",
        "values (#{userId,jdbcType=INTEGER}, #{roleId,jdbcType=INTEGER}, ",
        "#{registerId,jdbcType=VARCHAR}, #{registerDate,jdbcType=VARCHAR},#{delFlag,jdbcType=INTEGER})"
    })
    int insert(UserRole record);

    @Select({
        "select",
        "USER_ID, ROLE_ID, REGISTER_ID, REGISTER_DATE, UPDATE_ID, UPDATE_DATE, DEL_FLAG, ",
        "DEL_DATE",
        "from wp_user_role_ref",
        "where USER_ID = #{userId,jdbcType=INTEGER}",
          "and ROLE_ID = #{roleId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="REGISTER_ID", property="registerId", jdbcType=JdbcType.VARCHAR),
        @Result(column="REGISTER_DATE", property="registerDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_ID", property="updateId", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEL_FLAG", property="delFlag", jdbcType=JdbcType.INTEGER),
        @Result(column="DEL_DATE", property="delDate", jdbcType=JdbcType.VARCHAR)
    })
    UserRole selectByPrimaryKey(@Param("userId") Integer userId, @Param("roleId") Integer roleId);


    @Update({
        "update wp_user_role_ref",
        "set REGISTER_ID = #{registerId,jdbcType=VARCHAR},",
          "REGISTER_DATE = #{registerDate,jdbcType=VARCHAR},",
          "UPDATE_ID = #{updateId,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=VARCHAR},",
          "DEL_FLAG = #{delFlag,jdbcType=INTEGER},",
          "DEL_DATE = #{delDate,jdbcType=VARCHAR}",
        "where USER_ID = #{userId,jdbcType=INTEGER}",
          "and ROLE_ID = #{roleId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserRole record);


    @Select({
            "select ur.role_id,r.role_name",
            "from wp_user_role_ref ur left join mst_role r ",
            "on ur.role_id=r.role_id where ur.user_id=#{userId}"
    })
    @Results({
            @Result(column="ROLE_NAME", property="roleName", jdbcType=JdbcType.VARCHAR),
            @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER, id=true)

    })
    List<Role> findByUserId(Integer userId);




    @Select({
            "select ur.role_id,r.role_name",
            "from wp_user_role_ref ur left join mst_role r ",
            "on ur.role_id=r.role_id ",
            "left join wp_user u on ur.user_id=u.user_id",
            "where ur.user_id=#{userId}"
    })
    @Results({
            @Result(column="ROLE_NAME", property="roleName", jdbcType=JdbcType.VARCHAR),
            @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER, id=true)

    })
    Role findByUserRole(Integer userId);

}
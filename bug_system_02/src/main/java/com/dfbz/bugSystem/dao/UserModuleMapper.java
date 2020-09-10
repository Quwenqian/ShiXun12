package com.dfbz.bugSystem.dao;

import com.dfbz.bugSystem.po.Module;
import com.dfbz.bugSystem.po.UserModule;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserModuleMapper {
    @Delete({
        "delete from wp_role_mod_ref",
        "where USER_ID = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(@Param("userId") Integer userId);

    @Insert({
        "insert into wp_role_mod_ref (ROLE_ID, MODULE_ID, ",
        "USER_ID, REGISTER_ID, ",
        "REGISTER_DATE )",
        "values (#{roleId,jdbcType=INTEGER}, #{moduleId,jdbcType=INTEGER}, ",
        "#{userId,jdbcType=INTEGER}, #{registerId,jdbcType=VARCHAR}, ",
        "#{registerDate,jdbcType=VARCHAR})"
    })
    int insert(UserModule record);

    @Select({
        "select",
        "ROLE_ID, MODULE_ID, USER_ID, REGISTER_ID, REGISTER_DATE, UPDATE_ID, UPDATE_DATE",
        "from wp_role_mod_ref",
        "where ROLE_ID = #{roleId,jdbcType=INTEGER}",
          "and MODULE_ID = #{moduleId,jdbcType=INTEGER}",
          "and USER_ID = #{userId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="MODULE_ID", property="moduleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="REGISTER_ID", property="registerId", jdbcType=JdbcType.VARCHAR),
        @Result(column="REGISTER_DATE", property="registerDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_ID", property="updateId", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.VARCHAR)
    })
    UserModule selectByPrimaryKey(@Param("roleId") Integer roleId, @Param("moduleId") Integer moduleId, @Param("userId") Integer userId);

    @Select({
        "select",
        "ROLE_ID, MODULE_ID, USER_ID, REGISTER_ID, REGISTER_DATE, UPDATE_ID, UPDATE_DATE",
        "from wp_role_mod_ref"
    })
    @Results({
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="MODULE_ID", property="moduleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="REGISTER_ID", property="registerId", jdbcType=JdbcType.VARCHAR),
        @Result(column="REGISTER_DATE", property="registerDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_ID", property="updateId", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.VARCHAR)
    })
    List<UserModule> selectAll();

    @Update({
        "update wp_role_mod_ref",
        "set REGISTER_ID = #{registerId,jdbcType=VARCHAR},",
          "REGISTER_DATE = #{registerDate,jdbcType=VARCHAR},",
          "UPDATE_ID = #{updateId,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=VARCHAR}",
        "where ROLE_ID = #{roleId,jdbcType=INTEGER}",
          "and MODULE_ID = #{moduleId,jdbcType=INTEGER}",
          "and USER_ID = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserModule record);


    /**
     * 根据用户ID查找当前用户所有、具有的权限集合
     * @param userId
     * @return
     */
    @Select("select rm.ROLE_ID,m.module_id,m.module_name  " +
            "from wp_module m RIGHT JOIN wp_role_mod_ref rm on m.MODULE_ID=rm.MODULE_ID " +
            " where rm.user_id=#{userId}")
    @Results({
            @Result(column = "module_id",property = "moduleId",id = true),
            @Result(column = "module_name",property = "moduleName")
    })
    List<Module> findByUserId(Integer userId);
}
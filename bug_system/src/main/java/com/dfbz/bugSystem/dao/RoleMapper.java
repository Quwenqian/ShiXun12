package com.dfbz.bugSystem.dao;

import com.dfbz.bugSystem.po.Role;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RoleMapper {

    @Delete({
        "delete from mst_role",
        "where ROLE_ID = #{roleId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer roleId);


    @Insert({
        "insert into mst_role (ROLE_ID, ROLE_NAME, ",
        "REGISTER_ID, REGISTER_DATE)",
        "values (#{roleId,jdbcType=INTEGER}, #{roleName,jdbcType=VARCHAR}, ",
        "#{registerId,jdbcType=VARCHAR}, #{registerDate,jdbcType=VARCHAR}) ",

    })
    int insert(Role record);


    @Select({
        "select",
        "ROLE_ID, ROLE_NAME, REGISTER_ID, REGISTER_DATE, UPDATE_ID, UPDATE_DATE",
        "from mst_role",
        "where ROLE_ID = #{roleId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ROLE_NAME", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="REGISTER_ID", property="registerId", jdbcType=JdbcType.VARCHAR),
        @Result(column="REGISTER_DATE", property="registerDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_ID", property="updateId", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.VARCHAR)
    })
    Role selectByPrimaryKey(Integer roleId);


    @Select({
        "select",
        "ROLE_ID, ROLE_NAME, REGISTER_ID, REGISTER_DATE, UPDATE_ID, UPDATE_DATE",
        "from mst_role"
    })
    @Results({
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ROLE_NAME", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="REGISTER_ID", property="registerId", jdbcType=JdbcType.VARCHAR),
        @Result(column="REGISTER_DATE", property="registerDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_ID", property="updateId", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.VARCHAR)
    })
    List<Role> selectAll();

    @Update({
        "update mst_role",
        "set ROLE_NAME = #{roleName,jdbcType=VARCHAR},",
          "REGISTER_ID = #{registerId,jdbcType=VARCHAR},",
          "REGISTER_DATE = #{registerDate,jdbcType=VARCHAR},",
          "UPDATE_ID = #{updateId,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=VARCHAR}",
        "where ROLE_ID = #{roleId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Role record);
}
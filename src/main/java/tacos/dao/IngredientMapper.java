package tacos.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tacos.domain.Ingredient;

import java.util.List;

/**
 * @author yuanfu
 * @data 2021/5/12 19:07
 */
@Repository
public interface IngredientMapper {

    @Select("select id, name from Ingredient")
    List<Ingredient> findAll();

    @Select("select id, name from Ingredient where id = #{id}")
    Ingredient findById(long id);

    @Select("select id, name from Ingredient where id in #{idList}")
    List<Ingredient> findByIdList(List<Long> idList);

    @Select("select id, name from Ingredient where name = #{name}")
    Ingredient findByName(String name);

    @Insert("insert into Ingredient (name) values (#{name})")
    int add(String name);

    // TODO
    int delete();
}

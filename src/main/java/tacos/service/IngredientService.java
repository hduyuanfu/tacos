package tacos.service;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tacos.domain.Ingredient;

import java.util.List;

/**
 * @author yuanfu
 * @data 2021/5/12 19:44
 */
public interface IngredientService {

    List<Ingredient> findAllIngredients();

    Ingredient findIngredientById(long id);

    List<Ingredient> findIngredientsByIdList(List<Long> idList);

    Ingredient findIngredientByName(String name);

    int addIngredient(String name);
}

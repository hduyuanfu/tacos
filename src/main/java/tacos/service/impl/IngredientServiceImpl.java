package tacos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tacos.dao.IngredientMapper;
import tacos.domain.Ingredient;
import tacos.service.IngredientService;

import java.util.List;

/**
 * @author yuanfu
 * @data 2021/5/13 13:42
 */
@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientMapper ingredientMapper;

    @Autowired
    public IngredientServiceImpl(IngredientMapper ingredientMapper) {
        this.ingredientMapper = ingredientMapper;
    }

    @Override
    public List<Ingredient> findAllIngredients() {
        return ingredientMapper.findAll();
    }

    @Override
    public Ingredient findIngredientById(long id) {
        return ingredientMapper.findById(id);
    }

    @Override
    public List<Ingredient> findIngredientsByIdList(List<Long> idList) {
        return ingredientMapper.findByIdList(idList);
    }

    @Override
    public Ingredient findIngredientByName(String name) {
        return ingredientMapper.findByName(name);
    }

    @Override
    public int addIngredient(String name) {
        return ingredientMapper.add(name);
    }
}

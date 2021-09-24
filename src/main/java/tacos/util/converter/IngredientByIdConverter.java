package tacos.util.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.domain.Ingredient;
import tacos.service.IngredientService;

/**
 * @author yuanfu
 * @data 2021/5/13 18:48
 */
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientService ingredientService;

    @Autowired
    public IngredientByIdConverter(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientService.findIngredientById(Long.parseLong(id));
    }
}

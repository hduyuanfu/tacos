package tacos.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.domain.Ingredient;
import tacos.domain.Malatang;
import tacos.service.IngredientService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author yuanfu
 * @data 2021/5/12 19:02
 */
@Api(tags = "麻辣烫配料相关接口")
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes(value={"malatang", "design"})
public class DesignController {

    private IngredientService ingredientService;

    @Autowired
    public DesignController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @ModelAttribute(name="malatang")
    public Malatang malatang() {
        return new Malatang();
    }


    @ApiOperation("获取配料列表")
    // @ApiImplicitParam(name = "model", value = "模型", defaultValue = "null")
    @GetMapping
    public String showDesignForm(Model model) {

        List<Ingredient> ingredients = ingredientService.findAllIngredients();

        // List<Ingredient> ingredients = new ArrayList<>();
        // ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        model.addAttribute("design", ingredients);

        return "design";
    }

    // @PostMapping
    // public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors) {
    //     if(errors.hasErrors()) {
    //         return "design";
    //     }
    //     log.info("Processing design: " + design);
    //
    //     return "redirect:/orders/current";
    // }
    @ApiOperation("根据所选配料验证麻辣烫是否符合要求")
    // @ApiImplicitParams({
    //         @ApiImplicitParam(name = "design", value = "所挑选的麻辣烫配料", defaultValue = "null"),
    //         @ApiImplicitParam(name = "errors", value = "errors", defaultValue = "null")
    // })
    @PostMapping
    public String processDesign(
            @Valid Malatang design,
            Errors errors) {
        if(errors.hasErrors()) {
            return "design";
        }
        return "redirect:/orders/current";
    }
}

package tacos.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author yuanfu
 * @data 2021/5/12 18:56
 */
@Data
public class Malatang {

    @NotNull
    @Size(min=3, message="至少选择三个配料")
    private List<Ingredient> ingredients;

}

package tacos.domain;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author yuanfu
 * @data 2021/5/12 18:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    private long id;
    private String name;

}


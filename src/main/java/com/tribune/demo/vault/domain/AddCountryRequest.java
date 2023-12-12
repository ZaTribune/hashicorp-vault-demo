package com.tribune.demo.vault.domain;

import com.tribune.demo.vault.db.entity.Country;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * DTO for {@link Country}
 */
@Value
public class AddCountryRequest implements Serializable {

    @NotBlank
    String name;
    @NotBlank
    String description;
}

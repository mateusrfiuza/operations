package br.com.financial.operations.resource.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestBody implements Serializable {

    @NotBlank
    @NotEmpty
    @JsonProperty("document_number")
    private String documentNumber;

}

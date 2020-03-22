package br.com.financial.operations.resource.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse implements Serializable {

    @JsonProperty("account_id")
    private Long id;

    @JsonProperty("document_number")
    private String documentNumber;

}

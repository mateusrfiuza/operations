package br.com.financial.operations.domain.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Account {

    private Long id;
    private String documentNumber;

}

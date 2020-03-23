package br.com.financial.operations.controller;

import br.com.financial.operations.domain.account.AccountService;
import br.com.financial.operations.domain.transaction.TransactionService;
import br.com.financial.operations.resource.OperationsResource;
import br.com.financial.operations.resource.payload.AccountRequestBody;
import br.com.financial.operations.resource.payload.AccountResponseMapper;
import br.com.financial.operations.resource.payload.TransactionRequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static br.com.financial.operations.resource.OperationsResource.ACCOUNTS_URI;
import static br.com.financial.operations.resource.OperationsResource.TRANSACTIONS_URI;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionsResourceTest {

    private OperationsResource resource;

    @MockBean
    private AccountService accountService;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private AccountResponseMapper accountResponseMapper;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        resource = new OperationsResource(accountService, transactionService, accountResponseMapper);

        mockMvc = MockMvcBuilders.standaloneSetup(resource).build();

    }

    @Test
    public void valid_request() throws Exception {
        // given
        // when
        mockMvc.perform(post(TRANSACTIONS_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(new TransactionRequestBody(1L, 1, new BigDecimal("10.00")))))

                // then
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void request_without_attributes() throws Exception {
        // given
        // when
        mockMvc.perform(post(TRANSACTIONS_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(new TransactionRequestBody(null, 0, null))))

                // then
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void request_negative_amount() throws Exception {
        // given
        // when
        mockMvc.perform(post(TRANSACTIONS_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(new TransactionRequestBody(1L, 1, new BigDecimal("-10.00")))))

                // then
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void request_operation_type_equal_zero() throws Exception {
        // given
        // when
        mockMvc.perform(post(TRANSACTIONS_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(new TransactionRequestBody(1L, 0, new BigDecimal("10.00")))))

                // then
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


}

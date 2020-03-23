package br.com.financial.operations.behavior;

import br.com.financial.operations.MainApplication;
import br.com.financial.operations.helper.DatabaseHelper;
import br.com.financial.operations.infrastructure.database.AccountDAO;
import br.com.financial.operations.infrastructure.database.AccountEntity;
import br.com.financial.operations.resource.payload.TransactionRequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static br.com.financial.operations.resource.OperationsResource.TRANSACTIONS_URI;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MainApplication.class})
@AutoConfigureMockMvc
public class TransactionTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private AccountDAO accountDAO;

    @Before
    public void before() {
        databaseHelper.clearDatabase();
    }

    @Test
    public void successful_transaction_creation() throws Exception {
        //Given
        final var accountId = accountDAO.save(new AccountEntity("XPTO")).getId();

        // when
        mockMvc.perform(post(TRANSACTIONS_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper()
                        .writeValueAsString(new TransactionRequestBody(accountId, 1, new BigDecimal("10.00")))))
                //then
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void transaction_without_created_account() throws Exception {
        final var accountNotRegistered = 999L;

        mockMvc.perform(post(TRANSACTIONS_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper()
                        .writeValueAsString(new TransactionRequestBody(accountNotRegistered, 1, new BigDecimal("10.00")))))
                //then
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(jsonPath("$").doesNotExist());
    }

}

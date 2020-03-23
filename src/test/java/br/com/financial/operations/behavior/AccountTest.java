package br.com.financial.operations.behavior;

import br.com.financial.operations.MainApplication;
import br.com.financial.operations.helper.DatabaseHelper;
import br.com.financial.operations.infrastructure.database.AccountDAO;
import br.com.financial.operations.infrastructure.database.AccountEntity;
import br.com.financial.operations.resource.payload.AccountRequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static br.com.financial.operations.resource.OperationsResource.ACCOUNTS_URI;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MainApplication.class})
@AutoConfigureMockMvc
public class AccountTest {

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
    public void successful_account_creation() throws Exception {
        // given
        // when
        mockMvc.perform(post(ACCOUNTS_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(new AccountRequestBody("XPTO"))))

        //then
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.account_id").exists())
                .andExpect(jsonPath("$.document_number").exists());
    }

    @Test
    public void document_already_registered() throws Exception {
        // given
        // Document Already Registered
        accountDAO.save(new AccountEntity("XPTO"));

        // when
        mockMvc.perform(post(ACCOUNTS_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(new AccountRequestBody("XPTO"))))

        //then
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    public void account_found() throws Exception {
        // given
        final var accountId = accountDAO.save(new AccountEntity("XPTO")).getId();

        // when
        mockMvc.perform(get(ACCOUNTS_URI+"/"+accountId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(new AccountRequestBody("XPTO"))))

        //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.account_id").exists())
                .andExpect(jsonPath("$.document_number").exists());
    }

    @Test
    public void account_not_found() throws Exception {
        // given
        // when
        mockMvc.perform(get(ACCOUNTS_URI+"/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(new AccountRequestBody("XPTO"))))

        //then
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(jsonPath("$.account_id").doesNotExist())
                .andExpect(jsonPath("$.document_number").doesNotExist());
    }

}

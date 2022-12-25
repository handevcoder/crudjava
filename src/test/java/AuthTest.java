import com.example.crud.controller.RoleController;
import com.example.crud.controller.TestController;
import com.example.crud.service.RoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoleController.class)
public class AuthTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    RoleService roleSvc;

    @Test
    public void test() {
        TestController auth = new TestController();
        Assertions.assertEquals("This is a public Content.", auth.allAcces());
    }



    @Test
    public void getAllRole() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/get-all-role")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.get-all-role").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.get-all-role[*].id").isNotEmpty());
    }
}

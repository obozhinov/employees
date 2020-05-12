package com.example.employees;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EmployeeControllerIntegrationTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository mockRepository;

    @WithMockUser("USER")
    @Test
    public void get_employee_by_id_OK() throws Exception {
        //setup
        Employee employee = new Employee(1L, 777777777L, "Olya", "Bozhinov");
        when(mockRepository.getById(employee.getId())).thenReturn(employee);

        //perform and assert
        mockMvc.perform(get("/employee/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("Olya")))
                .andExpect(jsonPath("$.lastname", is("Bozhinov")))
                .andExpect(jsonPath("$.ssn", is(777777777)));

        verify(mockRepository, times(1)).getById(1L);
    }

    @WithMockUser("USER")
    @Test
    public void get_employeeIdNotFound_404() throws Exception {
        mockMvc.perform(get("/employee/5")).andExpect(status().isNotFound());
    }

    @WithMockUser("USER")
    @Test
    public void create_employee_OK() throws Exception {
        //setup
        Employee employee = new Employee(1L, 777777777L, "Olya", "Bozhinov");
        when(mockRepository.save(employee)).thenReturn(employee);

        mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(employee)))
                .andExpect(status().isCreated());
//                .andExpect(jsonPath("$.firstname", is("Olya")))
//                .andExpect(jsonPath("$.lastname", is("Bozhinov")))
//                .andExpect(jsonPath("$.ssn", is(777777777)));

    }
}

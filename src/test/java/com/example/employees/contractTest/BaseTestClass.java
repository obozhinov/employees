package com.example.employees.contractTest;

import com.example.employees.EmployeeController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;

public class BaseTestClass {
    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new EmployeeController());
    }
}

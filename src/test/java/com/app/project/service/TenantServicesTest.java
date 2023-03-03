package com.app.project.service;

import com.app.project.entity.Contact;
import com.app.project.entity.Tenant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TenantServicesTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findMany() {
    }

    @Test
    void add() {
    }

    @Test
    void getAll() {
    }
    @Test
    void tenantToStringTest(){
        Tenant tenant=new Tenant("Ozwin","Lobo",new Contact("12345","12345"), UUID.fromString("6afdf2ec-5155-4237-befd-86dc01a73a66"));
        System.out.println(tenant);
    }

}
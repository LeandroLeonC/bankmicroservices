package com.leon.leandro.mscustomer;

import com.leon.leandro.mscustomer.domain.entity.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    void shouldCreateActiveCustomer() {
        Customer customer = new Customer();
        customer.setName("Juan Perez");
        customer.setIdentification("0102030405");
        customer.setPassword("1234");
        customer.setActive(true);

        assertNotNull(customer);
        assertEquals("Juan Perez", customer.getName());
        assertEquals("0102030405", customer.getIdentification());
        assertTrue(customer.getActive());
    }

    @Test
    void shouldDeactivateCustomer() {
        Customer customer = new Customer();
        customer.setActive(true);

        customer.setActive(false);

        assertFalse(customer.getActive());
    }
}

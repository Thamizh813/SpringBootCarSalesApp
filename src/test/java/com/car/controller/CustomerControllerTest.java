package com.car.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.car.dao.IAppointmentRepository;
import com.car.dao.ICarRepository;
import com.car.dao.ICustomerRepository;
import com.car.dao.IOrderRepository;
import com.car.dao.IPaymentRepository;
import com.car.models.Customer;

@SpringBootTest
public class CustomerControllerTest {

	
	@Autowired
	ICustomerRepository cusrepo;
	
	@Autowired
	IAppointmentRepository arepo;
	
	@Autowired
	IPaymentRepository payrepo;
	
	@Autowired
	ICarRepository carrepo;
	
	@Autowired
	IOrderRepository orepo;
	
	@Test
	public void createcustomers() {
		Customer c = new Customer();
		c.setContactNo("9798765424");
		c.setDob(LocalDate.of(2000, 12, 21));
		c.setEmail("thamzi@gamilcom");
		c.setName("TP");
		c.setUserId("123");
		c.setAddress(null);
		c.setOrder(null);
		c.setAppointments(null);
		c.setCars(null);
		c.setUser(null);
		cusrepo.save(c);
		assertNotNull(cusrepo.findByUserId("123"));
	}
	
	@Test
	public void viewallcustomers() {
		List<Customer> list = cusrepo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}

	@Test
	public void viewbycustomerId() {
		Customer a = cusrepo.findByUserId("123");
		assertEquals("TP", a.getName() );
	}
	
	@Test
	public void customerupdate() {
		Customer a = cusrepo.findByUserId("thamizh12");
		a.setName("thamizharasan");
		cusrepo.save(a);
		assertNotEquals("kanan", cusrepo.findByUserId("thamizh12").getName());
	}
	
	@Test
	public void deletecustomer() {
		Customer c = new Customer();
		c.setContactNo("9798765424");
		c.setDob(LocalDate.of(2000, 12, 21));
		c.setEmail("thamzi@gamilcom");
		c.setName("TP");
		c.setUserId("123678");
		c.setAddress(null);
		c.setOrder(null);
		c.setAppointments(null);
		c.setCars(null);
		c.setUser(null);
		cusrepo.save(c);
		cusrepo.deleteById("123678");
		assertThat(cusrepo.existsById("123678")).isFalse();
	}
}

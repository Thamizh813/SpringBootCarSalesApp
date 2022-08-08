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

import com.car.dao.ICustomerRepository;
import com.car.dao.IOrderRepository;
import com.car.dao.IPaymentRepository;
import com.car.models.Order;

@SpringBootTest
public class OrderControllerTest {
	
	@Autowired
	ICustomerRepository cusrepo;
	
	@Autowired
	IPaymentRepository payrepo;
	
	@Autowired
	IOrderRepository orepo;
	
	@Test
	public void createorders() {
		Order o = new Order();
		o.setAmount(23000.0);
		o.setBillingDate(LocalDate.of(2022, 8, 27));
		o.setOrderId(1567L);
		o.setPaymentMethod("card");
		o.setCustomers(cusrepo.findByUserId("karth12"));
		o.setPayment(payrepo.findByPaymentId(13));
		orepo.save(o);
		assertNotNull(orepo.findById(1567L).get());
	}
	
	@Test
	public void viewallorders() {
		List<Order> list = orepo.findAll();
		assertThat(list).size().isGreaterThan(1);
	}
	
	@Test
	public void viewbyorderId() {
		Order a = orepo.findByOrderId(1567L);
		assertEquals("karth12", a.getCustomers().getUserId());
	}
	
	@Test
	public void appointmentupdate() {
		Order o = new Order();
		o.setAmount(23000.0);
		o.setBillingDate(LocalDate.of(2022, 8, 27));
		o.setOrderId(15677L);
		o.setPaymentMethod("card");
		o.setCustomers(cusrepo.findByUserId("karth12"));
		o.setPayment(payrepo.findByPaymentId(12));
		orepo.save(o);
		Order a = orepo.findByOrderId(15677L);
		a.setAmount(45000.0);
		orepo.save(a);
		assertNotEquals(23000.0, orepo.findByOrderId(15677L).getAmount() );
	}
	
	@Test
	public void deleteorder() {
		Order o = new Order();
		o.setAmount(23000.0);
		o.setBillingDate(LocalDate.of(2022, 8, 27));
		o.setOrderId(156778L);
		o.setPaymentMethod("card");
		o.setCustomers(cusrepo.findByUserId("karth12"));
		o.setPayment(null);
		orepo.save(o);
		orepo.deleteById(156778L);
		assertThat(orepo.existsById(156778L)).isFalse();
	}

}

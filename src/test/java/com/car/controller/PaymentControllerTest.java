package com.car.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.car.dao.ICustomerRepository;
import com.car.dao.IOrderRepository;
import com.car.dao.IPaymentRepository;
import com.car.models.Payment;


@SpringBootTest
public class PaymentControllerTest {
	@Autowired
	ICustomerRepository cusrepo;
	
	@Autowired
	IPaymentRepository payrepo;
	
	@Autowired
	IOrderRepository orepo;
	
	@Test
	public void createpayments() {
		Payment p = new Payment();
		p.setAppointments(null);
		p.setCard(null);
		p.setOrders(null);
		p.setPaymentId(346L);
		p.setStatus("success");
		p.setType("rupay");
		payrepo.save(p);
		assertNotNull(payrepo.findByPaymentId(346L));
	}
	
	@Test
	public void viewallpayments() {
		List<Payment> list = payrepo.findAll();
		assertThat(list).size().isGreaterThan(3);
	}
	
	@Test
	public void viewbyPaymentId() {
		Payment a = payrepo.findByPaymentId(346L);
		assertEquals(null, a.getAppointments());
	}
	
	@Test
	public void paymentupdate() {
		Payment a = payrepo.findByPaymentId(12);
		a.setStatus("fail");
		payrepo.save(a);
		assertNotEquals("successfull", payrepo.findByPaymentId(12).getStatus());
	}
	
	@Test
	public void deletepayment() {
		Payment p = new Payment();
		p.setAppointments(null);
		p.setCard(null);
		p.setOrders(null);
		p.setPaymentId(123567L);
		p.setStatus("failure");
		p.setType("card");
		payrepo.save(p);
		assertThat(payrepo.existsById(123567L)).isTrue();
		payrepo.deleteById(123567L);
		assertThat(payrepo.existsById(123567L)).isFalse();
	}

}

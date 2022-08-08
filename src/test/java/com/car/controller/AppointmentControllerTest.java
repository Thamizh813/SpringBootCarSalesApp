package com.car.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.car.dao.IAppointmentRepository;
import com.car.dao.ICustomerRepository;
import com.car.dao.IPaymentRepository;
import com.car.models.Appointment;


@SpringBootTest
public class AppointmentControllerTest {


	@Autowired
	IAppointmentRepository arepo;
	@Autowired
	ICustomerRepository cusrepo;
	
	@Autowired
	IPaymentRepository payrepo;
	
	@Test
	public void createappointments() {
		Appointment a = new Appointment();
		a.setAppointmentId(3L);
		a.setInspectionType("carwheel");
		a.setLocation("pondy");
		a.setPreferredDate(LocalDate.of(2022, 8, 22));
		a.setPreferredTime(LocalTime.of(14, 45, 23));
		a.setCustomer(cusrepo.findByUserId("thamizh12"));
		a.setPayment(null);
		arepo.save(a);
		assertNotNull(arepo.findById(3L).get());
	}
	
	@Test
	public void viewallappointments() {
		List<Appointment> list = arepo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	
	@Test
	public void viewbyappointmentId() {
		Appointment a = arepo.findByAppointmentId(1L);
		assertEquals("thamizh12", a.getCustomer().getUserId());
	}
	
	@Test
	public void appointmentupdate() {
		Appointment a = arepo.findByAppointmentId(1L);
		a.setLocation("Lawspet");
		arepo.save(a);
		assertNotEquals("puducherry", arepo.findByAppointmentId(1L).getLocation());
	}
	
	@Test
	public void deleteappointment() {
		arepo.deleteById(1L);
		assertThat(arepo.existsById(1L)).isFalse();
	}
}

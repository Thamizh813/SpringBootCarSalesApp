package com.car.controller;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import com.car.dao.ICarRepository;
import com.car.models.Car;
import com.car.service.ICarService;


@SpringBootTest
public class CarControllerTest {
	
		@Autowired
		private ICarRepository carrepo;
		
		@Autowired
		private ICarService carservice;
		
		@Test
		public void testFindAll() {
			Car car1 = new Car(11L, "xyz", "s3", "cs", LocalDate.now(),"Pondy",null);
		    carrepo.save(car1);
		    Car car2 = new Car(12L, "xyz", "s4", "c55", LocalDate.now(),"Pondy",null);
		    carrepo.save(car2);
		    Car car3 = new Car(13L, "abc", "vbc", "234", LocalDate.now(),"TamilNadu",null);
		    carrepo.save(car3);
		}
		
		@Test
	     public void testSave() {
	          Car car = getCar();	     
		      carservice.addCar(car);
		      Car test = carrepo.findById(car.getCarId()).get();
		      assertEquals(car.getCarId(), test.getCarId());	     
		 }
		 
		
		 @Test
		   public void testFindById() {
			Car car = getCar();	     
		    carrepo.save(car);
		    Car result = carservice.getCar(car.getCarId());
		    Car test = carrepo.findById(car.getCarId()).get();
		    assertEquals(result.getCarId(), test.getCarId()); 
		   }
		 
		 
		  @Test
		   public void testDeleteById() {
			  Car car = getCar();	     
		      carrepo.save(car);
		      carservice.removeCar(car.getCarId());
		      assertThat(carrepo.existsById(car.getCarId())).isFalse();
		   }
		  
		 
		  
		  @Test
		  public void testFindByLocation() {
		    Car car1 = new Car(14L, "xyz", "s3", "cs", LocalDate.now(),"Pondy",null);
		    carrepo.save(car1);
		    Car car2 = new Car(15L, "xyz", "s4", "c55", LocalDate.now(),"Pondy",null);
		    carrepo.save(car2);
		    Car car3 = new Car(16L, "abc", "vbc", "234", LocalDate.now(),"TamilNadu",null);
		    carrepo.save(car3);
		    Optional<Car> result= carrepo.findById(14L);
		    assertEquals("Pondy", result.get().getRegistrationState());
		    assertNotEquals("Kerala", result.get().getRegistrationState());
		  }
		 
		  @Test
		  public void testFindByModel() {
		    Car car1 = new Car(17L, "xyz", "s3", "cs", LocalDate.now(),"Pondy",null);
		    carrepo.save(car1);
		    Car car2 = new Car(18L, "xyz", "s4", "c55", LocalDate.now(),"Pondy",null);
		    carrepo.save(car2);
		    Car car3 = new Car(19L, "abc", "s3", "234", LocalDate.now(),"TamilNadu",null);
		    carrepo.save(car3);
		    Optional<Car> result= carrepo.findById(17L);
		    assertEquals("s3", result.get().getModel());
		  }
		  
		  @Test
		  public void testFindByBrand() {
		    Car car1 = new Car(20L, "xyz", "s3", "cs", LocalDate.now(),"Pondy",null);
		    carrepo.save(car1);
		    Car car2 = new Car(21L, "xyz", "s4", "c55", LocalDate.now(),"Pondy",null);
		    carrepo.save(car2);
		    Car car3 = new Car(22L, "abc", "vbc", "234", LocalDate.now(),"TamilNadu",null);
		    carrepo.save(car3);
		    Optional<Car> result= carrepo.findById(21L);
		    assertEquals("xyz", result.get().getBrand());
	  }
		
		private Car getCar() {
		      Car car = new Car();
		      car.setCarId(31L);
		      car.setBrand("Datsun");
		      car.setModel("s3");
		      car.setVariant("x 43");
		      car.setRegistrationYear(LocalDate.now());
		      car.setRegistrationState("Pondicherry");
		      car.setCustomer(null);
		      return car;
		   }
		 
}

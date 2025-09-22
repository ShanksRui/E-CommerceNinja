package com.diciplina.Test_Diciplina.Config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.diciplina.Test_Diciplina.Entities.ItemOrder;
import com.diciplina.Test_Diciplina.Entities.Ninja;
import com.diciplina.Test_Diciplina.Entities.Order;
import com.diciplina.Test_Diciplina.Entities.Product;
import com.diciplina.Test_Diciplina.Entities.Store;
import com.diciplina.Test_Diciplina.Entities.Village;
import com.diciplina.Test_Diciplina.Entities.Enum.OrderStatus;
import com.diciplina.Test_Diciplina.Repository.ItemOrderRepository;
import com.diciplina.Test_Diciplina.Repository.NinjaRepository;
import com.diciplina.Test_Diciplina.Repository.OrderRepository;
import com.diciplina.Test_Diciplina.Repository.ProductRepository;
import com.diciplina.Test_Diciplina.Repository.StoreRepository;
import com.diciplina.Test_Diciplina.Repository.VillageRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
        @Autowired
		private NinjaRepository identRepository;
        @Autowired
		private VillageRepository vilaRepository; 
        @Autowired
        private StoreRepository storeRepository;
        @Autowired
        private ProductRepository productRepository;
        @Autowired
        private ItemOrderRepository itemOrderRepository;
        @Autowired
        private OrderRepository orderRepository;
   
	   @Override
	   public void run(String... args) throws Exception {
      
		   Village vila1 = new Village(null, "Hidden Leaf Village", "Land of Fire");
		   Village vila2 = new Village(null, "Hidden Sand Village", "Land of Wind");
		   Village vila3 = new Village(null, "Hidden Mist Village", "Land of Water");
		   Village vila4 = new Village(null, "Hidden Stone Village", "Land of Earth");
		   Village vila5 = new Village(null, "Hidden Cloud Village", "Land of Lightning");
		   vilaRepository.saveAll(Arrays.asList(vila1, vila2, vila3, vila4, vila5));	

		   Product p1 = new Product(null, "Kunai", 19.90);
		   Product p2 = new Product(null, "Shuriken", 15.50);
		   Product p3 = new Product(null, "Secret Scroll", 58.99);
		   Product p4 = new Product(null, "Explosive Tags", 58.99);
		   Product p5 = new Product(null, "Backpacks", 24.99);

		   Store s1 = new Store(null, "Tenten Store", "Ninja Weapons", vila1);
		   Store s2 = new Store(null, "Puppet Store", "Equipment", vila3);
		   Store s3 = new Store(null, "EspadaX Store", "Legendary Swords", vila2);
		   Store s4 = new Store(null, "Sainan Store", "Equipment", vila4);
		   Store s5 = new Store(null, "Hidden Mist Store", "Legendary Swords", vila5);

        s1.getProducts().add(p2);
		s2.getProducts().add(p3);
		s2.getProducts().add(p5);
		s3.getProducts().add(p4);
		s4.getProducts().add(p1);
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		storeRepository.saveAll(Arrays.asList(s1,s2,s3,s4,s5));
		
		Ninja in1 = new Ninja(null, "rock lee", 100, 37,200.0,vila1);
		Ninja in2 = new Ninja(null, "Naruto Uzumaki", 70, 17,150.0,vila4);
		Ninja in3 = new Ninja(null, "Sasuke Uchiha", 85, 29,89.0,vila2);
		Ninja in4 = new Ninja(null, "Kakashi Hatake", 77, 22,145.00,vila5);
		Ninja in5 = new Ninja(null, "Neji Hyuga",  95, 18,200.0,vila3);
		
		Order o1 = new Order(null, Instant.parse("2025-09-09T13:22:00Z"), OrderStatus.PENDING,in1);
		Order o2= new Order(null, Instant.parse("2025-09-09T10:26:00Z"), OrderStatus.DELIVERED,in3);
		Order o3 = new Order(null, Instant.parse("2025-09-09T08:11:00Z"), OrderStatus.SHIPPED,in4);
		identRepository.saveAll(Arrays.asList(in1,in2,in3,in4,in5));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
		ItemOrder i1 = new ItemOrder(o3, p1, 3);
		ItemOrder i2 = new ItemOrder(o1, p4, 2);
		ItemOrder i3 = new ItemOrder(o2, p3, 2);
		ItemOrder i4 = new ItemOrder(o2, p5, 2);
		itemOrderRepository.saveAll(Arrays.asList(i1,i2,i3,i4));
		
		
		
		
	}	
}

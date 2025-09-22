package com.diciplina.Test_Diciplina.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diciplina.Test_Diciplina.Entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}

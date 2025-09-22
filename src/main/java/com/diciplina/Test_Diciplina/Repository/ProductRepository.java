package com.diciplina.Test_Diciplina.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diciplina.Test_Diciplina.Entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

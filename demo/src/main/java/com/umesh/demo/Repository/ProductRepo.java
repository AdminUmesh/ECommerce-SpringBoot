package com.umesh.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.umesh.demo.Model.ProductDetail;

@Repository
public interface ProductRepo extends JpaRepository<ProductDetail, Long> {

    ProductDetail findById(int DeleteId);

    List<ProductDetail> findAll();
}

package edu.kh.com.daoapplication.repository;

import edu.kh.com.daoapplication.dao.KHTProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KHTProductRepository extends JpaRepository<KHTProduct, Long> {
}

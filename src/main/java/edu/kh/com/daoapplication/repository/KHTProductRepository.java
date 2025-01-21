package edu.kh.com.daoapplication.repository;

import edu.kh.com.daoapplication.dao.KHTProduct;
import edu.kh.com.daoapplication.dao.KHTUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KHTProductRepository extends JpaRepository<KHTProduct, Long> {

    KHTProduct findById(int id);

    KHTProduct findByName(String name);
}

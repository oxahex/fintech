package com.oxahex.domain.repository;

import com.oxahex.domain.entity.ProductInfo;
import com.oxahex.domain.type.OrganizationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long> {

    List<ProductInfo> findAllByOrganizationCode(OrganizationCode organizationCode);
}

package com.oxahex.product.service;

import com.oxahex.domain.entity.ProductInfo;
import com.oxahex.domain.repository.ProductInfoRepository;
import com.oxahex.domain.type.ProductCode;
import com.oxahex.product.dto.ProductInfoDto;
import com.oxahex.domain.type.OrganizationCode;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductInfoRepository productInfoRepository;

    @Transactional(readOnly = true)
    @Cacheable(value = "product", key = "#organizationCode.organizationCode", cacheManager = "redisCacheManager")
    public List<ProductInfoDto.Response> getProductInfoList(OrganizationCode organizationCode) {

        // 기관명과 일치하는 상품을 리스트에서 찾음
        List<ProductInfo> productList = productInfoRepository.findAllByOrganizationCode(organizationCode);

        return productList.stream().map(x -> ProductInfoDto.Response.builder()
                .productName(x.getProductName())
                .productCode(x.getProductCode())
                .productMaximumInterest(x.getProductMaximumInterest())
                .productMinimumInterest(x.getProductMinimumInterest())
                .organizationCode(x.getOrganizationCode()).build()).collect(Collectors.toList());

    }

    @Transactional
    @CacheEvict(value = "product", key = "#request.organizationCode", cacheManager = "redisCacheManager")
    public void addProductInfo(ProductInfoDto.Request request) {

        ProductCode productCode = ProductCode.getCode(request.getProductCode());
        OrganizationCode organizationCode = OrganizationCode.getCode(request.getOrganizationCode());

        // 상품 추가
        ProductInfo productInfo = productInfoRepository.save(
                ProductInfo.builder()
                        .organizationCode(organizationCode)
                        .productCode(productCode)
                        .productName(request.getProductName())
                        .productMaximumInterest(request.getProductMaximumInterest())
                        .productMinimumInterest(request.getProductMinimumInterest()).build()
        );

        // 상품 정보 추가
        productInfoRepository.save(productInfo);
    }
}

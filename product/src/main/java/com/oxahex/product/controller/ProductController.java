package com.oxahex.product.controller;

import com.oxahex.product.dto.BaseResponseDto;
import com.oxahex.product.dto.ProductInfoDto;
import com.oxahex.product.dto.ResponseDto;
import com.oxahex.product.service.ProductService;
import com.oxahex.domain.type.OrganizationCode;
import com.oxahex.product.type.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/fintech/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{organizationCode}")
    public ResponseEntity<ResponseDto.Success> getProducts(
            @PathVariable OrganizationCode organizationCode
    ) {

        return ResponseEntity.ok(ResponseDto.Success.builder()
                .data(productService.getProductInfoList(organizationCode))
                .responseCode(SuccessCode.SUCCESS.getStatus().value())
                .responseMessage(SuccessCode.SUCCESS.getDescription())
                .build());
    }

    /**
     * 상품 추가
     * @param request 추가할 상품 정보
     * @return 요청 성공 여부
     */
    @PostMapping("/information")
    public ResponseEntity<BaseResponseDto> addProduct(
            @RequestBody ProductInfoDto.Request request)
    {
        productService.addProductInfo(request);

        return ResponseEntity.ok(
                BaseResponseDto.builder()
                        .responseCode(SuccessCode.SUCCESS.getStatus().value())
                        .responseMessage(SuccessCode.SUCCESS.getDescription()).build()
        );
    }
}

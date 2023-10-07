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

        System.out.println("orgcode: " + organizationCode);



        // 기관 코드를 변경함 숫자로 변경함
        List<ProductInfoDto> productList = productService.getProductInfoList(organizationCode);

        // 변경한 숫자로 상품 데이터를 찾음


        return null;
    }

    /**
     * 상품 추가
     * @param request 추가할 상품 정보
     * @return
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

package js.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import js.product.entity.Credit;
import js.product.entity.Product;
import js.product.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductControllerTest {

    private static final int PRODUCT_1_ID = 1;
    private static final int PRODUCT_2_ID = 2;
    private static final String PRODUCT_1_NAME = "product1";
    private static final String PRODUCT_2_NAME = "product2";
    private static final Double PRODUCT_1_VALUE = 50.5;
    private static final Double PRODUCT_2_VALUE = 70.0;

    private static final String POST_ADDRESS = "/product/createProduct";
    private static final String GET_ADDRESS = "/product/getProducts";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Test
    void createProduct() throws Exception {
        Assertions.assertEquals(0, productService.getAllProducts().size());
        mockMvc.perform(post(POST_ADDRESS).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(prepareProduct(null, PRODUCT_1_NAME, PRODUCT_1_VALUE))))
                .andExpect(status().isOk());
        Assertions.assertEquals(1, productService.getAllProducts().size());
    }

    @Test
    void getProducts() throws Exception {
        productService.saveProduct(prepareProduct(PRODUCT_1_ID, PRODUCT_1_NAME, PRODUCT_1_VALUE));
        productService.saveProduct(prepareProduct(PRODUCT_2_ID, PRODUCT_2_NAME, PRODUCT_2_VALUE));
        mockMvc.perform(get(GET_ADDRESS)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo(PRODUCT_1_ID)))
                .andExpect(jsonPath("$[1].id", Matchers.equalTo(PRODUCT_2_ID)))
                .andExpect(jsonPath("$[0].productName", Matchers.equalTo(PRODUCT_1_NAME)))
                .andExpect(jsonPath("$[1].productName", Matchers.equalTo(PRODUCT_2_NAME)))
                .andExpect(jsonPath("$[0].value", Matchers.equalTo(PRODUCT_1_VALUE)))
                .andExpect(jsonPath("$[1].value", Matchers.equalTo(PRODUCT_2_VALUE)));
    }

    private Product prepareProduct(Integer id, String name, Double value){
        Product product = new Product();
        product.setId(id);
        product.setProductName(name);
        product.setValue(value);
        return product;
    }
}
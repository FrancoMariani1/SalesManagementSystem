package com.management.sales.sales;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.sales.sales.model.Customer;
import com.management.sales.sales.model.Invoice;
import com.management.sales.sales.model.Product;
import com.management.sales.sales.service.CustomerService;
import com.management.sales.sales.service.InvoiceService;
import com.management.sales.sales.service.ProductService;
import com.management.sales.sales.util.Jsons;
import jakarta.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;



@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class InvoiceTest {

        @Autowired
        private MockMvc mockMvc;
        @Autowired
        private InvoiceService invoiceService;
        @Autowired
        private CustomerService customerService;

        @Autowired
        private ProductService productService;

        @Autowired
        private ObjectMapper objectMapper;

        private Customer testCustomer;
        private Product testProduct;
        private Long testInvoiceId;

        @BeforeEach
        public void setup() {
            cleanup();
            cargarDataSet();
        }

    @AfterEach
    public void cleanup() {
//        List<Invoice> allInvoices = invoiceService.getAllInvoices();
//        for (Invoice invoice : allInvoices) {
        if(testInvoiceId != null) {
            invoiceService.deleteInvoice(testInvoiceId);
        }
        if(testProduct != null) {
            productService.deleteProduct(testProduct.getId());
        }
        if(testCustomer != null) {
            customerService.deleteCustomer(testCustomer.getId());
        }
        productService.deleteProduct(testProduct.getId());
        customerService.deleteCustomer(testCustomer.getId());
    }

    public void cargarDataSet() {
        testCustomer = customerService.addCustomer(new Customer("Santiago", "santiago@hotmail.com", "3415555555", "Francia 5000"));
        testProduct = productService.addProduct(new Product("Amargo", "Chocolates", "Aguila", 1000.00));

        Invoice testInvoice = new Invoice();
        testInvoice.setCustomer(testCustomer);
        testInvoice.setProduct(testProduct);
        testInvoice.setQuantity(1);
        testInvoice.setTotal_price(1000.00);
        testInvoice.setDate(new Date());

        Invoice createdInvoice = invoiceService.addInvoice(testInvoice);

        this.testInvoiceId = createdInvoice.getId();
    }

    private MvcResult createTestInvoice() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setCustomer(testCustomer);
        invoice.setProduct(testProduct);
        invoice.setQuantity(1);
        invoice.setTotal_price(1000.00);
        invoice.setDate(new Date());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/invoices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Jsons.asJsonString(invoice)))
                        .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        Map<String, Object> jsonResponse = objectMapper.readValue(responseContent, Map.class);
        testInvoiceId = ((Number)jsonResponse.get("id")).longValue();
        return mvcResult;


    }

    @Test
    public void listarInvoices() throws Exception {

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/invoices").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void addInvoice() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setCustomer(testCustomer);
        invoice.setProduct(testProduct);
        invoice.setQuantity(1);
        invoice.setTotal_price(1000.00);
        invoice.setDate(new Date());

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/invoices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Jsons.asJsonString(invoice)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());

    }

    @Test
    public void deleteInvoice() throws Exception {
        createTestInvoice();
        Invoice invoice = new Invoice();
        invoice.setCustomer(testCustomer);
        invoice.setProduct(testProduct);
        invoice.setQuantity(1);
        invoice.setTotal_price(1000.00);
        invoice.setDate(new Date());

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/invoices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Jsons.asJsonString(invoice)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());

        MvcResult responseDelete = mockMvc.perform(MockMvcRequestBuilders.delete("/invoices/{id}", this.testInvoiceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent()).andReturn();

        Assert.assertTrue(responseDelete.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void updateInvoice() throws Exception {
        createTestInvoice();
        Invoice invoice = new Invoice();
        invoice.setCustomer(testCustomer);
        invoice.setProduct(testProduct);
        invoice.setQuantity(1);
        invoice.setTotal_price(1000.00);
        invoice.setDate(new Date());

//        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/invoices/{id}", this.testInvoiceId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(Jsons.asJsonString(invoice)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
//
//        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
//
//        Invoice invoiceUpdate = new Invoice();
//        invoiceUpdate.setCustomer(testCustomer);
//        invoiceUpdate.setProduct(testProduct);
//        invoiceUpdate.setQuantity(2);
//        invoiceUpdate.setTotal_price(2000.00);
//        invoiceUpdate.setDate(new Date());

        MvcResult responseUpdate = mockMvc.perform(MockMvcRequestBuilders.put("/invoices/{id}",this.testInvoiceId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Jsons.asJsonString(invoice)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(responseUpdate.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void getInvoice() throws Exception {

        createTestInvoice();
        System.out.println("Test Invoice ID: " + this.testInvoiceId);

        MvcResult responseGet = mockMvc.perform(MockMvcRequestBuilders.get("/invoices/{id}", this.testInvoiceId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(responseGet.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void getInvoiceByCustomer() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setCustomer(testCustomer);
        invoice.setProduct(testProduct);
        invoice.setQuantity(1);
        invoice.setTotal_price(1000.00);
        invoice.setDate(new Date());

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/invoices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Jsons.asJsonString(invoice)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());

        MvcResult responseGet = mockMvc.perform(MockMvcRequestBuilders.get("/invoices/customer/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(responseGet.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void getInvoiceByDate() throws Exception {
        createTestInvoice();

        Invoice invoice = new Invoice();
        invoice.setCustomer(testCustomer);
        invoice.setProduct(testProduct);
        invoice.setQuantity(1);
        invoice.setTotal_price(1000.00);
        Date currentDate = new Date();
        invoice.setDate(currentDate);

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/invoices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Jsons.asJsonString(invoice)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);

        MvcResult responseGet = mockMvc.perform(MockMvcRequestBuilders.get("/invoices/date/2023-10-28", new Date())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(responseGet.getResponse().getContentAsString().isEmpty());
    }

}

//@Test
//    public void getInvoice() throws Exception {
//
////        Invoice invoice = new Invoice();
////        invoice.setCustomer(testCustomer);
////        invoice.setProduct(testProduct);
////        invoice.setQuantity(1);
////        invoice.setTotal_price(1000.00);
////        invoice.setDate(new Date());
//
//        createTestInvoice();
//
//        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/invoices")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(Jsons.asJsonString(invoice)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
//
//        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
//
//        MvcResult responseGet = mockMvc.perform(MockMvcRequestBuilders.get("/invoices/{id}", this.testInvoiceId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
//
//        Assert.assertFalse(responseGet.getResponse().getContentAsString().isEmpty());
//    }

//        public void cargarDataSet() {
//            Customer customer = customerService.addCustomer(new Customer("Santiago", "santiago@hotmail.com", "3415555555", "Francia 5000"));
//            Product p = productService.addProduct(new Product("Amargo", "Chocolates", "Aguila", 1000.00));
//
//        }
//        @Test
//        public void listarInvoices() throws Exception {
//            this.cargarDataSet();
//            MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/invoices/{id}", 1).accept(MediaType.APPLICATION_JSON))
//                    .andDo(MockMvcResultHandlers.print())
//                    .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
//
//            Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
//        }
//
//        @Test
//        public void addInvoice() throws Exception {
//            this.cargarDataSet();
//            MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/invoices/{id}", 1).accept(MediaType.APPLICATION_JSON))
//                    .andDo(MockMvcResultHandlers.print())
//                    .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
//
//            Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
//        }
//
//        @Test
//        public void updateInvoice() throws Exception {
//            this.cargarDataSet();
//            MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/invoices/{id}", 1).accept(MediaType.APPLICATION_JSON))
//                    .andDo(MockMvcResultHandlers.print())
//                    .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
//
//            Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
//        }
//
//        @Test
//        public void deleteInvoice() throws Exception {
//            this.cargarDataSet();
//            MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/invoices/{id}", 1).accept(MediaType.APPLICATION_JSON))
//                    .andDo(MockMvcResultHandlers.print())
//                    .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
//
//            Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
//        }
//
//        @Test
//        public void getInvoice() throws Exception {
//            this.cargarDataSet();
//            MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/invoices/{id}", 1).accept(MediaType.APPLICATION_JSON))
//                    .andDo(MockMvcResultHandlers.print())
//                    .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
//
//            Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
//        }
//
//        @Test
//        public void getInvoiceByDate() throws Exception {
//            this.cargarDataSet();
//            MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/invoices/{id}", 1).accept(MediaType.APPLICATION_JSON))
//                    .andDo(MockMvcResultHandlers.print())
//                    .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
//
//            Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
//        }

//        @Test
//        public void getInvoiceByCustomer() throws Exception {
//            this.cargarDataSet();
//            MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/invoices/{id}", 1).accept(MediaType.APPLICATION_JSON))
//                    .andDo(MockMvcResultHandlers.print())
//                    .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
//
//            Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
//        }
//
//        @Test
//        public void getInvoiceByProduct() throws Exception {
//            this.cargarDataSet();
//            MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/invoices/{id}", 1).accept(MediaType.APPLICATION_JSON))
//                    .andDo(MockMvcResultHandlers.print())
//                    .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
//
//            Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
//        }





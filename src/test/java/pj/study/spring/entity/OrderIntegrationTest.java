package pj.study.spring.entity;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/applicationContext.xml")
@Transactional
public class OrderIntegrationTest {

    @PersistenceContext
    EntityManager entityManager;

    public void before() {

    }

    @Test
    public void saveAndLoad() {
        Customer customer = newCustomer("Jaggu");
        Product product = newProduct("Laptop");
        entityManager.persist(customer);
        entityManager.persist(product);
        Set<OrderItem> orderItems = new HashSet<OrderItem>();
        orderItems.add(newOrderItem(product, 10, new BigDecimal("10.5")));
        Order order = newOrder(new Date(), customer, orderItems);
        entityManager.persist(order);
        assertNotNull(order.getId());
        order = entityManager.find(Order.class,  order.getId());
        assertNotNull(order);

    }

    private Product newProduct(String name) {
        Product product = new Product();
        product.setName(name);
        return product;
    }

    private Customer newCustomer(String name) {
        Customer customer = new Customer();
        customer.setName(name);
        return customer;
    }

    private OrderItem newOrderItem(Product product, Integer quantity, BigDecimal unitPrice) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItem.setUnitPrice(unitPrice);
        return orderItem;
    }

    private Order newOrder(Date orderDate, Customer customer, Set<OrderItem> orderItems) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(orderDate);
        for(OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        return order;
    }
}


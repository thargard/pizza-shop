package modsen.pizza.productservice.repository;

import modsen.pizza.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.categoryid=?1")
    List<Product> getByCategoriesId(Long categoryId);
}

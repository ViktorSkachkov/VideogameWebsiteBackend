package com.example.demo.persistence.repository;

import com.example.demo.domain.RankedItem;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AdditionOrderRepository extends JpaRepository<AdditionOrderPersistence, Long> {
    boolean deleteAllByAddition(int additionId);

    @Query("select aop from AdditionOrderPersistence aop")
    List<AdditionOrderPersistence> findAllTest();

    @Query("select aop from AdditionOrderPersistence aop where aop.id = ?1")
    AdditionOrderPersistence findByOrderId(Long id);

    @Query("select aop from AdditionOrderPersistence aop where aop.user = ?1")
    List<AdditionOrderPersistence> findByUserId(Long id);

    @Query("select aop from AdditionOrderPersistence aop where aop.approved = ?3 and aop.time between ?1 and ?2")
    List<AdditionOrderPersistence> getAdditionOrders(LocalDateTime startDate, LocalDateTime endDate, Boolean approved);

    /*@Query("select aop.addition from AdditionOrderPersistence aop where aop.approved = ?3 and aop.time between ?1 and ?2 group by aop.addition order by sum(aop.totalPrice) desc")
    List<Integer> getAdditionIds(LocalDateTime startDate, LocalDateTime endDate, Boolean approved);

    @Query("select sum(aop.units) from AdditionOrderPersistence aop where aop.approved = ?3 and aop.time between ?1 and ?2 group by aop.addition order by sum(aop.totalPrice) desc")
    List<Integer> getUnits(LocalDateTime startDate, LocalDateTime endDate, Boolean approved);

    @Query("select sum(aop.totalPrice) from AdditionOrderPersistence aop where aop.approved = ?3 and aop.time between ?1 and ?2 group by aop.addition order by sum(aop.totalPrice) desc")
    List<Double> getTotalPrice(LocalDateTime startDate, LocalDateTime endDate, Boolean approved);*/

    @Query("SELECT new com.example.demo.domain.RankedItem(aop.addition, SUM(aop.units), SUM(aop.totalPrice), '') FROM AdditionOrderPersistence aop where aop.approved = ?3 and aop.time between ?1 and ?2 group by aop.addition order by sum(aop.totalPrice) desc")
    List<RankedItem> getRankedAdditionItems(LocalDateTime startDate, LocalDateTime endDate, Boolean approved);

    @Query("select aop from AdditionOrderPersistence aop where aop.user = ?1 and aop.approved = ?2")
    List<AdditionOrderPersistence> findCartItemsByUserId(Long id, Boolean approved);
}

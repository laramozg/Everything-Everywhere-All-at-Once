package com.example.db.repository;

import com.example.db.dto.EmployerResponse;
import com.example.db.model.Employer;
import com.example.db.model.enums.StatusOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployerRepository extends CrudRepository<Employer,String> {

    @Query("SELECT NEW com.example.db.dto.EmployerResponse(e.order.id, e.hero.login, e.coordinator.login) FROM Employer e WHERE e.order.status = :state")
    List<EmployerResponse> getEmployersByOrderStatus(@Param("state") StatusOrder state);

}

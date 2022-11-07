package com.example.proxy.repository;

import com.example.proxy.entity.Request;
import liquibase.pro.packaged.L;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RequestRepository extends JpaRepository<Request, Long> {
    //todo get by user , status or not for all
    @Query(value = "SELECT r FROM Request r WHERE " +
            "(r.status.id= :statusId OR :statusId is null) AND" +
            "(r.requester.id =:userId OR :userId is null)")
    Page<Request> findByStatusAndRequester(@Param("statusId") Long statusId,
                                           @Param("userId") Long userId, Pageable pageable);

}

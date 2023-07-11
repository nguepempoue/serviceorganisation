package com.b2i.serviceorganisation.repository;

import com.b2i.serviceorganisation.model.AccountingClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountingClassRepository extends JpaRepository<AccountingClass, Long> {
}

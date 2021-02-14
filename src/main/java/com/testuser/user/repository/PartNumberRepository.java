package com.testuser.user.repository;

import com.testuser.user.model.PartNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartNumberRepository extends JpaRepository<PartNumber,Long> {
}

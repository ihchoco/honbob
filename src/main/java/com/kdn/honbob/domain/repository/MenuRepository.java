package com.kdn.honbob.domain.repository;

import com.kdn.honbob.domain.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}

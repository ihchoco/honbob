package com.kdn.honbob.domain.repository;

import com.kdn.honbob.domain.model.OrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Long> {
}

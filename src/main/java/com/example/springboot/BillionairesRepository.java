package com.example.springboot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillionairesRepository extends JpaRepository<Billionaires, Long> {
}

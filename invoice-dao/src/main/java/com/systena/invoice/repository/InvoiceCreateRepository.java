package com.systena.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systena.invoice.entity.InvoiceEntity;

/**
 * The Interface InvoiceCreateRepository.
 */
@Repository
public interface InvoiceCreateRepository extends JpaRepository<InvoiceEntity, Integer> {

}

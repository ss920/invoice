package com.systena.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systena.invoice.entity.InvoiceClientEntity;

/**
 * The Interface InvoiceFindRepository.
 */
@Repository
public interface InvoiceFindRepository extends JpaRepository<InvoiceClientEntity, Integer> {

}

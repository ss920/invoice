package com.systena.invoice.repository;

import com.systena.invoice.entity.InvoiceClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Interface InvoiceFindRepository.
 */
@Repository
public interface InvoiceFindRepository extends JpaRepository<InvoiceClientEntity, Integer> {

}

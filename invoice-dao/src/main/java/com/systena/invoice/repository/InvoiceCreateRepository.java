package com.systena.invoice.repository;

import com.systena.invoice.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Interface InvoiceCreateRepository.
 */
@Repository
public interface InvoiceCreateRepository extends JpaRepository<InvoiceEntity, Integer> {

    /**
     * Save.
     *
     * @param invoiceEntity the invoice entity
     * @return the invoice entity
     */
    @SuppressWarnings("unchecked")
    public InvoiceEntity save(InvoiceEntity invoiceEntity);
}

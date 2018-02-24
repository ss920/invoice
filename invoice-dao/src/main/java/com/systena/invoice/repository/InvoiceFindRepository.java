package com.systena.invoice.repository;

import com.systena.invoice.entity.InvoiceClientEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Interface InvoiceFindRepository.
 */
@Repository
public interface InvoiceFindRepository extends JpaRepository<InvoiceClientEntity, Integer> {

    /**
     * Find all.
     *
     * @return the invoice client entity
     */
    public List<InvoiceClientEntity> findAll();

    /**
     * Find one.
     *
     * @param invoiceNo the invoice no
     * @return the invoice client entity
     */
    public InvoiceClientEntity findOne(int invoiceNo);
}

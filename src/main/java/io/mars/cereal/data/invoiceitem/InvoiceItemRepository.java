package io.mars.cereal.data.invoiceitem;

import io.mars.cereal.model.InvoiceItem;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceItemRepository extends CrudRepository<InvoiceItem, Long> {
}

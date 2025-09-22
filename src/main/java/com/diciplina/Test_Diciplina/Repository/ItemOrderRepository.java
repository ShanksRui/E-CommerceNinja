package com.diciplina.Test_Diciplina.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diciplina.Test_Diciplina.Entities.ItemOrder;
import com.diciplina.Test_Diciplina.Entities.PK.ItemOrderPK;

public interface ItemOrderRepository extends JpaRepository<ItemOrder, ItemOrderPK> {

}

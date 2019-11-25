package com.sh.order;

import co.cantina.spring.jooq.sample.model.tables.records.OrdersRecord;
import com.sh.order.representation.OrderRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @Auther: jacobzheng
 * @Date: 2019/3/6 17:08
 * @Description:
 */
@Mapper
public interface OrdersMapper {

    OrdersMapper INSTANCE = Mappers.getMapper(OrdersMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(target = "status", ignore = true)
    OrderRepresentation bookRecordsToOrders(OrdersRecord booksRecord);

}

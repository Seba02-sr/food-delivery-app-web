package com.mycompany.tpdsw.service;

import java.util.List;

import com.mycompany.tpdsw.dto.ItemMenuDto;

public interface ItemMenuService {

    List<ItemMenuDto> findActiveByIdVendedor(Integer id);
}

package com.mycompany.tpdsw.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.tpdsw.dto.ItemMenuDto;
import com.mycompany.tpdsw.mapper.ItemMenuMapper;
import com.mycompany.tpdsw.model.ItemMenu;
import com.mycompany.tpdsw.repository.ItemMenuRepository;
import com.mycompany.tpdsw.service.ItemMenuService;

@Service
public class ItemMenuServiceImpl implements ItemMenuService {

    @Autowired
    private ItemMenuRepository itemMenuRepository;

    @Autowired
    private ItemMenuMapper itemMenuMapper;

    @Override
    public List<ItemMenuDto> findActiveByIdVendedor(Integer id) {
        List<ItemMenu> items = itemMenuRepository.findActiveByVendedorId(id);
        return items.stream().map(itemMenuMapper::mapToDto).collect(Collectors.toList());
    }

}

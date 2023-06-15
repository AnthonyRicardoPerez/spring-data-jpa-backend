package com.anthony.springboot.jpa.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.anthony.springboot.jpa.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long>{


}

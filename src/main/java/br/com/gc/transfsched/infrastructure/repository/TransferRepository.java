package br.com.gc.transfsched.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.gc.transfsched.infrastructure.entity.Transfer;

@Repository
public interface TransferRepository extends CrudRepository<Transfer, Long>{
	
}
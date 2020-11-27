package com.pim.lucas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pim.lucas.domain.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

	@Transactional(readOnly = true)
	Conta findByCpfOuCnpj(String cpfOuCnpj);
	

}

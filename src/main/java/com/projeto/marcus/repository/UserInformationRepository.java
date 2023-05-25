package com.projeto.marcus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.marcus.model.UserInformation;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {
	
	Optional<UserInformation> findByCpf(String cpf);

}

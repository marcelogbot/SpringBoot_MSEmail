package br.com.ms_spring.email.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ms_spring.email.models.ConfirmationTokenModel;

@Repository
public interface ConfirmationTokenReposiroty extends JpaRepository<ConfirmationTokenModel, Long> {
    Optional<ConfirmationTokenModel> findByToken(String token);
}

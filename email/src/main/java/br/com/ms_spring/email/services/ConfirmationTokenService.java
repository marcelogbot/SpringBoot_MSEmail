package br.com.ms_spring.email.services;

import org.springframework.stereotype.Service;

import br.com.ms_spring.email.models.ConfirmationTokenModel;
import br.com.ms_spring.email.repositories.ConfirmationTokenReposiroty;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    
    private final ConfirmationTokenReposiroty confirmationTokenReposiroty;

    public void saveConfirmationToken(ConfirmationTokenModel token) {
        confirmationTokenReposiroty.save(token);
    }
}

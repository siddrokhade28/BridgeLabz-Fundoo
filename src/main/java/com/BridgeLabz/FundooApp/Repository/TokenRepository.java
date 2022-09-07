package com.BridgeLabz.FundooApp.Repository;

import com.BridgeLabz.FundooApp.Model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<ConfirmationToken,Integer> {
    Optional<ConfirmationToken> findByToken (String token);
}

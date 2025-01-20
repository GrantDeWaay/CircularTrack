package com.example.circularinventory.repository;

import com.example.circularinventory.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account getAccountByEmail(String email);

    Account findBySessionTokenEquals(String sessionToken);
}
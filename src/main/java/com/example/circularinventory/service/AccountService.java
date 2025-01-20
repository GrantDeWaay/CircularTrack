package com.example.circularinventory.service;


import com.example.circularinventory.dto.CreateAccountDto;
import com.example.circularinventory.dto.LoginToAccountDto;
import com.example.circularinventory.model.Account;
import com.example.circularinventory.repository.AccountRepository;
import com.example.circularinventory.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService( AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean createAccount(CreateAccountDto createAccountDto) throws NoSuchAlgorithmException {
        String salt = generateSalt();
        String encryptedPassword = passwordEncrypt(createAccountDto.password, salt);
        Account account = new Account(createAccountDto.username, createAccountDto.email, encryptedPassword, salt);
        Account savedAccount = accountRepository.save(account);
        return savedAccount != null;
    }


    public String generateSalt(){
        return generateRandomString(8);
    }

    public String passwordEncrypt(String password, String salt) throws NoSuchAlgorithmException {
        String saltedPassword = password + salt;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
        String saltedEcryptedPassword = toHexString(hash);;
        return saltedEcryptedPassword;
    }

    public static String toHexString(byte[] hash)
    {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64)
            hexString.insert(0, '0');
        return hexString.toString();
    }

    public static String generateLoginTokenString()
    {
        return "ct_" + generateRandomString(16);
    }


    public static String generateRandomString(int length){
        StringBuilder randomStringConstructor = new StringBuilder();
        char[] randomCharsDomain = new char[]{
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
        for(int i = 0; i<length; i++){
            int randomIndex = (int) (Math.random() * randomCharsDomain.length);
            randomStringConstructor.append(randomCharsDomain[randomIndex]);
        }
        return randomStringConstructor.toString();
    }


    public String login(LoginToAccountDto loginToAccountDto) throws NoSuchAlgorithmException {
        Account accountAssociatedWithEmail = accountRepository.getAccountByEmail(loginToAccountDto.email);
        if(accountAssociatedWithEmail == null){
            return "";
        }
        String accountSalt = accountAssociatedWithEmail.getSalt();
        String encryptedPasswordSaved = accountAssociatedWithEmail.getPassword();
        String userEnteredPasswordPostSaltAndEncryption = passwordEncrypt(loginToAccountDto.password, accountSalt);
        if(userEnteredPasswordPostSaltAndEncryption.equals(encryptedPasswordSaved)){
            String loginTokenString = generateLoginTokenString();
            accountAssociatedWithEmail.setSessionToken(loginTokenString);
            Instant newExpirationTime = Instant.now().plus(Duration.ofHours(3));
            accountAssociatedWithEmail.setTokenExpirationTime(newExpirationTime);
            accountRepository.save(accountAssociatedWithEmail);
            return loginTokenString;
        }
        return "no";
    }

    public Account getAccountBySessionToken(String sessionToken){
        Account accountAssociatedWithToken = accountRepository.findBySessionTokenEquals(sessionToken);
        if(accountAssociatedWithToken == null) {
            return accountAssociatedWithToken;
            // ask to log in again
        }
        if(accountAssociatedWithToken.getTokenExpirationTime() == null){
            return accountAssociatedWithToken;
            // ask to log in again
        }
        if(accountAssociatedWithToken.getTokenExpirationTime().isBefore(Instant.now())){
            //some logic that sends them a different message
        }
        Instant newExpirationTime = Instant.now().plus(Duration.ofHours(3));
        accountAssociatedWithToken.setTokenExpirationTime(newExpirationTime);
        return accountRepository.save(accountAssociatedWithToken);
    }
}

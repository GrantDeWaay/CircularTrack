package com.example.circularinventory.controller;

import com.example.circularinventory.dto.*;
import com.example.circularinventory.model.Product;
import com.example.circularinventory.service.AccountService;
import com.example.circularinventory.service.MaterialsService;
import com.example.circularinventory.service.ProductService;
import com.example.circularinventory.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {


    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/create-account")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity createAccount(@RequestBody CreateAccountDto createAccountDto) throws NoSuchAlgorithmException {
        if(accountService.createAccount(createAccountDto)){
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<TokenDto> LoginToAccount(@RequestBody LoginToAccountDto loginToAccountDto) throws NoSuchAlgorithmException {
        return ResponseEntity.ok(new TokenDto( accountService.login(loginToAccountDto)));
    }
}

package com.hussain.walletapplication.Controller;


import com.hussain.walletapplication.Entity.Wallet;
import com.hussain.walletapplication.Services.ValidationErrorService;
import com.hussain.walletapplication.Services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wallet")
@CrossOrigin
public class WalletController {
    @Autowired
    private WalletService walletService;
    @Autowired
    private ValidationErrorService validationService;
    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(walletService.getAll(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return  new ResponseEntity<>(walletService.getById(id),HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Wallet wallet, BindingResult result){

        ResponseEntity errors = validationService.validate(result);
        if (errors!=null)return errors;
      Wallet walletsave = walletService.createOrUpdate(wallet);
       return  new ResponseEntity<Wallet>(walletsave,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody Wallet wallet, BindingResult result){

        ResponseEntity errors = validationService.validate(result);
        if (errors!=null)return errors;
        wallet.setId(id);
        Wallet walletsave = walletService.createOrUpdate(wallet);
        return  new ResponseEntity<Wallet>(walletsave,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id){
        walletService.Delete(id);
        return new ResponseEntity(HttpStatus.OK);


    }
}

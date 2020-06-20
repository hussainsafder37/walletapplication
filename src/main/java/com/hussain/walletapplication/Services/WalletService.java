package com.hussain.walletapplication.Services;

import com.hussain.walletapplication.Entity.Wallet;
import com.hussain.walletapplication.Exception.WalletException;
import com.hussain.walletapplication.Repository.WalletRepository;
import com.hussain.walletapplication.WalletapplicationApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;
    public List<Wallet> getAll(){
        return walletRepository.findAllByOrOrderByPriority();
    }
    public Wallet getById(Long id){
        Optional<Wallet> wallet = walletRepository.findById(id);
        if (wallet.isPresent()){
            return wallet.get();
        }
        throw new WalletException("wallet with" +id+ " doesnot exist");



    }
    public Wallet createOrUpdate(Wallet wallet){
      if (wallet.getId()== null){
         walletRepository.save(wallet);

      }else {
          walletRepository.save(wallet);
      }
      return wallet;
    }
    public boolean Delete(Long id){
        Optional<Wallet> wallet = walletRepository.findById(id);
        if (wallet.isPresent()){
            walletRepository.delete(wallet.get());
            return true;
        }
        throw new WalletException("wallet with" +id+ " doesnot exist");

    }
}

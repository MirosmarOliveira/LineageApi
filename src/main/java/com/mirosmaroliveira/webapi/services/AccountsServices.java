package com.mirosmaroliveira.webapi.services;

import com.mirosmaroliveira.webapi.model.AccountsModel;
import com.mirosmaroliveira.webapi.repositories.AccountsRepositories;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AccountsServices {
    final
    AccountsRepositories accountsRepositories;

    public AccountsServices(AccountsRepositories accountsRepositories) {
        this.accountsRepositories = accountsRepositories;
    }
    public AccountsModel saveAccounts(AccountsModel accountsModel) {
        return accountsRepositories.save(accountsModel);
    }
    public Page<AccountsModel> findAll(Integer page, Integer size, String direction, String orderBy){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return accountsRepositories.findAll(pageRequest);
    }
    public AccountsModel findById(String login) {
        Optional<AccountsModel> accountsModel = accountsRepositories.findById(login);
        return accountsModel.orElseThrow(() -> new ObjectNotFoundException("User not fund, LoginId: ",  login));
    }
    public AccountsModel update(AccountsModel accountsModel) {
        AccountsModel accountsModelUpdate = findById(accountsModel.getLogin());
        accountsModel.setCreated_time(accountsModelUpdate.getCreated_time());
        accountsModelUpdate.setLogin("");
        accountsModelUpdate.setEmail("");
        accountsModelUpdate.setPassword("");
        return accountsRepositories.save(accountsModel);
    }
    public void deleteById(String login) {
        accountsRepositories.deleteById(login);
    }
}

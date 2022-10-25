package com.mirosmaroliveira.webapi.controller;

import com.mirosmaroliveira.webapi.dto.AccountsDTO;
import com.mirosmaroliveira.webapi.services.AccountsServices;
import com.mirosmaroliveira.webapi.model.AccountsModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/accounts")
public class AccountsController {
    final AccountsServices accountsServices;

    public AccountsController(AccountsServices accountsServices) {
        this.accountsServices = accountsServices;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@Valid @RequestBody AccountsModel accountsModel) {
        accountsModel.setCreated_time(LocalDateTime.now(ZoneId.of("UTC")));
        accountsModel.setAccessLevel((byte) 0);
        AccountsModel objSave = accountsServices.saveAccounts(accountsModel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{login}").buildAndExpand(objSave.getLogin()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<AccountsModel>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "24") Integer size,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "login") String orderBy) {
        Page<AccountsModel> pageObj = accountsServices.findAll(page, size, direction, orderBy);
        return ResponseEntity.ok().body(pageObj);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{login}")
    public AccountsModel findById(@PathVariable String login) {
        return accountsServices.findById(login);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{login}" )
    public ResponseEntity<Void> update(@Valid @RequestBody AccountsModel accountsModel, @PathVariable String login) {
        accountsModel.setLogin(login);
        accountsServices.update(accountsModel);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{login}")
    public ResponseEntity<Void> delete(@PathVariable String login) {
        accountsServices.deleteById(login);
        return ResponseEntity.noContent().build();
    }
}

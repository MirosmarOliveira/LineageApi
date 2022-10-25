package com.mirosmaroliveira.webapi.repositories;

import com.mirosmaroliveira.webapi.model.AccountsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AccountsRepositories extends JpaRepository <AccountsModel, String> {

}

package com.example.bankapp.repository;

import com.example.bankapp.model.Account;
import com.example.bankapp.model.Currency;
import com.example.bankapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {

    //select * from account where balance > $(balance)
    List<Account> findAllByBalanceGreaterThan(Double balance);


    //select * from account where currency=$(currency) and balance < 100
    List<Account> findAllByCurrencyIsAndBalanceLessThan(Currency currency, Double balance);

}

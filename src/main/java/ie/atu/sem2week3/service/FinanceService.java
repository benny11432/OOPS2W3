package ie.atu.sem2week3.service;

import org.springframework.stereotype.Service;

@Service
public class FinanceService {

    public int calculateNet(int income, int expense) {
        return income - expense;
    }

    public int calculateTax(int income, double taxRate) {
        return (int) (income * taxRate);
    }

}

package ie.atu.sem2week3.controller;

import ie.atu.sem2week3.service.FinanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance")
public class FinanceController {
    private final FinanceService financeService;

    public FinanceController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping("/net")
    public ResponseEntity<?> calculateNet(@RequestParam int income, @RequestParam int expense) {
        if (expense > income) {
            return ResponseEntity
                    .badRequest()
                    .body("Invalid input: expense must be less than or equal to income");
        }
        int result = financeService.calculateNet(income, expense);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/tax")
    public ResponseEntity<?> calculateTax(@RequestParam int income, @RequestParam double taxRate) {
        if (taxRate < 0 || taxRate > 1) {
            return ResponseEntity
                    .badRequest()
                    .body("Invalid input: taxRate must be between 0 and 1 (e.g. 0.20 for 20%)");
        }
        int taxAmount = financeService.calculateTax(income, taxRate);
        return ResponseEntity.ok(taxAmount);
    }
}

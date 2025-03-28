package com.example.stock.storestock;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    // Injection du service StockService dans le contrôleur
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    // Afficher l'état du stock
    @GetMapping("/stockStatus")
    public ModelAndView showStock() {
        return new ModelAndView("stock", "articles", stockService.getStock());
    }

    // Réapprovisionner le stock
    @PostMapping("/reapprovisionner")
    public String reapprovisionner() {
        stockService.reapprovisionner();
        return "redirect:/stock/stockStatus";
    }
}


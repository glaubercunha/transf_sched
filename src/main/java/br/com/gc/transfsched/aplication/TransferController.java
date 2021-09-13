package br.com.gc.transfsched.aplication;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import br.com.gc.transfsched.domain.service.TransferService;
import br.com.gc.transfsched.infrastructure.entity.Transfer;

@Controller
public class TransferController {
    private static final Logger logger = LoggerFactory.getLogger(TransferController.class);
	
    @Autowired
    private TransferService service;
    
    @GetMapping(value = "/transfers")
    public String getTransfers(Model model) {
        List<Transfer> tranfers = service.findAll();
        model.addAttribute("transfers", tranfers);
        return "transfer-list";
    }
    
    @GetMapping(value = {"/transfers/add"})
    public String addTransfer(Model model) {
        model.addAttribute("transfer", new Transfer());
        return "transfer-add";
    }    
    
    @PostMapping(value = "/transfers/add")
    public String addTransfer(Model model,
            @ModelAttribute("transfer") Transfer transfer) {        
        try {
        	Transfer Savedtransfer = service.save(transfer);
        	logger.info(Savedtransfer.toString());
            return "redirect:/transfers/";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
     
            model.addAttribute("add", true);
            return "transfer-add";
        }        
    }    
    
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor is a custom date editor
    }    
}
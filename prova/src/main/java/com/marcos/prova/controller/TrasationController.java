package com.marcos.prova.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.prova.entity.Transaction;
import com.marcos.prova.repository.TransationRepository;


@RestController
public class TrasationController {

	@Autowired
	private TransationRepository transationRepository;
	
	@RequestMapping(value = "/transaction/all", method = RequestMethod.GET)
    public List<Transaction> GetAll() {
        return transationRepository.findAll();
    }
	
	@RequestMapping(value = "/transaction", method =  RequestMethod.POST)
    public Transaction Post(@Valid  @RequestBody Transaction transation)
    {
		
        return transationRepository.save(transation);
    }

	@RequestMapping(value = "/transaction", method =  RequestMethod.PUT)
    public ResponseEntity<Transaction> Put( @Valid @RequestBody Transaction newTransation)
    {
		
        Optional<Transaction> changeTransation = transationRepository.findById(newTransation.getId());
        if(changeTransation .isPresent()){
        	Transaction transation = changeTransation.get();
        	transation.setDate(newTransation.getDate());
        	transation.setTime(newTransation.getTime());
        	transation.setValue(newTransation.getValue());
        	transation.setPaymentStatus(newTransation.getPaymentStatus());
        	transation.setCardApplication(newTransation.getCardApplication());
        	transationRepository.save(transation);
            return new ResponseEntity<Transaction>(transation, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	
}

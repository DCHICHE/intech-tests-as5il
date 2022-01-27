package com.intech.comptabilite.service.entityservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.intech.comptabilite.model.CompteComptable;
import com.intech.comptabilite.model.EcritureComptable;
import com.intech.comptabilite.model.SequenceEcritureComptable;
import com.intech.comptabilite.repositories.CompteComptableRepository;
import com.intech.comptabilite.service.exceptions.NotFoundException;

@SpringBootTest
public class SequenceEcritureComptableServiceIntegrationTest {
	
	@Autowired 
	SequenceEcritureComptableService sequenceEcritureComptableService;
	
	    @Test
	    public void integrationTestgetDernierValeurByCodeAndAnnee() throws NotFoundException {
	    	SequenceEcritureComptable sequenceEcritureComptable = 
	    			new SequenceEcritureComptable("TEST",2022,2022);
	    	
	    	sequenceEcritureComptableService.upsert(sequenceEcritureComptable);
	    	
	    	int result = sequenceEcritureComptableService.getDernierValeurByCodeAndAnnee("TEST", 2022);
	       
	        Assertions.assertEquals(result, sequenceEcritureComptable.getDerniereValeur());

	    }
}

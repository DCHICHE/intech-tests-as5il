package com.intech.comptabilite.service.entityservice;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.intech.comptabilite.model.CompteComptable;
import com.intech.comptabilite.model.JournalComptable;
import com.intech.comptabilite.repositories.CompteComptableRepository;

@SpringBootTest
public class CompteComptableServiceIntegrationTest {

	@Autowired 
	CompteComptableService compteComptableService;

	    
	    @Test
	    public void integrationTestGetListCompteComptable() {
	    	List<CompteComptable> list = compteComptableService.getListCompteComptable();
	    	
	    	Assertions.assertEquals(list.size(), 7);
	    }
	    
}

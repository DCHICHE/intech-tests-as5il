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

import com.intech.comptabilite.model.EcritureComptable;
import com.intech.comptabilite.model.JournalComptable;
import com.intech.comptabilite.repositories.EcritureComptableRepository;
import com.intech.comptabilite.repositories.JournalComptableRepository;

@SpringBootTest
public class JournalComptableServiceIntegrationTest {

	@Autowired
	JournalComptableService journalComptableService;
	
	    
    @Test
    public void integrationTestGetListJournalComptable() {
    	List<JournalComptable> list = journalComptableService.getListJournalComptable();
    	
    	Assertions.assertEquals(list.size(), 4);
    }
    
}

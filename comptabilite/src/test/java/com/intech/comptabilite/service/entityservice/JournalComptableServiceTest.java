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
public class JournalComptableServiceTest {

	@Autowired
	JournalComptableService journalComptableService;
	
	@SpyBean
	JournalComptableRepository journalComptableRepository;
	
	
    @Test
    public void testGetByCodeShouldReturnJournalCompteComptableIfExists() {
       JournalComptable journalComptable = new JournalComptable("A","B");      
       JournalComptable journalComptable2 = new JournalComptable("C","D");    
       
       List<JournalComptable> listJournalComptables = new ArrayList<JournalComptable>();
       listJournalComptables.add(journalComptable);
       listJournalComptables.add(journalComptable2);
       
       JournalComptable result = journalComptableService.getByCode(listJournalComptables, "A");
       
       Assertions.assertNotNull(result);
       Assertions.assertEquals(result.getCode(), journalComptable.getCode());
       Assertions.assertEquals(result.getLibelle(), journalComptable.getLibelle());
    }
    
    @Test
    public void testGetByCodeShouldReturnNullIfNotExists() {
       JournalComptable journalComptable = new JournalComptable("A","B");
       JournalComptable journalComptable2 = new JournalComptable("C","D");    
       
       List<JournalComptable> listJournalComptables = new ArrayList<JournalComptable>();
       listJournalComptables.add(journalComptable);
       listJournalComptables.add(journalComptable2);
       
       JournalComptable result = journalComptableService.getByCode(listJournalComptables, "ZZ");
       
       Assertions.assertNull(result);
    }
    
    @Test
    public void unitTestGetListEcritureComptable() {
    	List<JournalComptable> list = new ArrayList<JournalComptable>();
    	JournalComptable journalComptable = new JournalComptable();
    	journalComptable.setCode("TEST");
    	list.add(journalComptable);
    	list.add(new JournalComptable());
    	
    	Mockito.when(journalComptableRepository.findAll()).thenReturn(list);
    	
    	List<JournalComptable> result = journalComptableService.getListJournalComptable();
    	
    	Assertions.assertEquals(result.size(), list.size());
    	Assertions.assertEquals(result.get(0).getCode(), journalComptable.getCode());

    }
    
    
}

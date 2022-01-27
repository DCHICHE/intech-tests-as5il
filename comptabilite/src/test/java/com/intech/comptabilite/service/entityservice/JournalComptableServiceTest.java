package com.intech.comptabilite.service.entityservice;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.intech.comptabilite.model.JournalComptable;

@SpringBootTest
public class JournalComptableServiceTest {

	@Autowired
	JournalComptableService journalComptableService;
	
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
}

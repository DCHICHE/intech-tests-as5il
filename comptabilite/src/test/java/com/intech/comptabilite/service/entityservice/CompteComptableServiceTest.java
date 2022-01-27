package com.intech.comptabilite.service.entityservice;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.intech.comptabilite.model.CompteComptable;

@SpringBootTest
public class CompteComptableServiceTest {

	@Autowired 
	CompteComptableService compteComptableService;
	
	    @Test
	    public void testGetByNumeroShouldReturnCompteComptableIfExists() {
	       CompteComptable compteComptable = new CompteComptable();
	       compteComptable.setNumero(1);
	       compteComptable.setLibelle("FIRST");
	       
	       CompteComptable compteComptable2 = new CompteComptable();
	       compteComptable2.setNumero(2);
	       compteComptable2.setLibelle("SECOND");;
	       
	       
	       List<CompteComptable> listCompteComptable = new ArrayList<CompteComptable>();
	       listCompteComptable.add(compteComptable);
	       listCompteComptable.add(compteComptable2);
	       
	       CompteComptable result = compteComptableService.getByNumero(listCompteComptable, 1);
	       
	       Assertions.assertNotNull(result);
	       Assertions.assertEquals(result.getNumero(), compteComptable.getNumero());
	       Assertions.assertEquals(result.getLibelle(), compteComptable.getLibelle());
	    }
	    
	    @Test
	    public void testGetByNumeroShouldReturnNullIfNotExists() {
	       CompteComptable compteComptable = new CompteComptable();
	       compteComptable.setNumero(1);
	       compteComptable.setLibelle("FIRST");
	       
	       CompteComptable compteComptable2 = new CompteComptable();
	       compteComptable2.setNumero(2);
	       compteComptable2.setLibelle("SECOND");;
	       
	       
	       List<CompteComptable> listCompteComptable = new ArrayList<CompteComptable>();
	       listCompteComptable.add(compteComptable);
	       listCompteComptable.add(compteComptable2);
	       
	       CompteComptable result = compteComptableService.getByNumero(listCompteComptable, 3);
	       
	       Assertions.assertNull(result);
	    }
	    
}

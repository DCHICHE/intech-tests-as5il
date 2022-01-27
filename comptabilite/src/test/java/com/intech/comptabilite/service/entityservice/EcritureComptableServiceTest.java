package com.intech.comptabilite.service.entityservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.intech.comptabilite.model.CompteComptable;
import com.intech.comptabilite.model.EcritureComptable;
import com.intech.comptabilite.model.JournalComptable;
import com.intech.comptabilite.model.LigneEcritureComptable;
import com.intech.comptabilite.repositories.EcritureComptableRepository;
import com.intech.comptabilite.service.exceptions.NotFoundException;

@SpringBootTest
public class EcritureComptableServiceTest {
	
	@MockBean
	EcritureComptableRepository ecritureComptableRepository;
	
	@Autowired
	private EcritureComptableService ecritureComptableService;

    private LigneEcritureComptable createLigne(Integer pCompteComptableNumero, String pDebit, String pCredit) {
        BigDecimal vDebit = pDebit == null ? null : new BigDecimal(pDebit);
        BigDecimal vCredit = pCredit == null ? null : new BigDecimal(pCredit);
        String vLibelle = ObjectUtils.defaultIfNull(vDebit, BigDecimal.ZERO)
                                     .subtract(ObjectUtils.defaultIfNull(vCredit, BigDecimal.ZERO)).toPlainString();
        LigneEcritureComptable vRetour = new LigneEcritureComptable(new CompteComptable(pCompteComptableNumero),
                                                                    vLibelle,
                                                                    vDebit, vCredit);
        return vRetour;
    }

    @Test
    public void isEquilibree() {
        EcritureComptable vEcriture;
        vEcriture = new EcritureComptable();

        vEcriture.setLibelle("Equilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "301"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "40", "7"));
        Assertions.assertTrue(ecritureComptableService.isEquilibree(vEcriture));

        vEcriture.getListLigneEcriture().clear();
        vEcriture.setLibelle("Non équilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "10", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "20", "1"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "30"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "1", "2"));
        Assertions.assertFalse(ecritureComptableService.isEquilibree(vEcriture));
    }
    
    @Test
    public void testGetTotalCreditShouldReturnTheTotalCredit() {
        EcritureComptable vEcriture;
        vEcriture = new EcritureComptable();
        
        String firstDecimalString = "125.4";
        String secondDecimalString = "10.6";
        
        BigDecimal vfirstCredit =  new BigDecimal(firstDecimalString); 
        BigDecimal vsecondCredit = new BigDecimal(secondDecimalString);
        
        
        BigDecimal totalCreditExcepted = vfirstCredit.add(vsecondCredit);
        
        vEcriture.getListLigneEcriture().add(this.createLigne(1, null, firstDecimalString));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, null, secondDecimalString));
        
        BigDecimal result = ecritureComptableService.getTotalCredit(vEcriture);
       
        Assertions.assertEquals(result, totalCreditExcepted);

    }
    
    @Test
    public void testGetTotalCreditShouldReturnZeroIfNoCredit() {
        EcritureComptable vEcriture;
        vEcriture = new EcritureComptable();               
        
        BigDecimal totalCreditExcepted = BigDecimal.ZERO;
        
        vEcriture.getListLigneEcriture().add(this.createLigne(1, null, null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, null, null));
        
        BigDecimal result = ecritureComptableService.getTotalCredit(vEcriture);
       
        Assertions.assertEquals(result, totalCreditExcepted);

    }
    
    @Test
    public void testGetTotalDebitShouldReturnTheTotalDebit() {
        EcritureComptable vEcriture;
        vEcriture = new EcritureComptable();
        
        String firstDecimalString = "125.4";
        String secondDecimalString = "10.6";
        
        BigDecimal vfirstDebit =  new BigDecimal(firstDecimalString); 
        BigDecimal vsecondDebit = new BigDecimal(secondDecimalString);
        
        
        BigDecimal totalDebitExcepted = vfirstDebit.add(vsecondDebit);
        
        vEcriture.getListLigneEcriture().add(this.createLigne(1, firstDecimalString, null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, secondDecimalString,null ));
        
        BigDecimal result = ecritureComptableService.getTotalDebit(vEcriture);
       
        Assertions.assertEquals(result, totalDebitExcepted);

    }
    
    @Test
    public void testGetTotalDebitShouldReturnZeroIfNoDebit() {
        EcritureComptable vEcriture;
        vEcriture = new EcritureComptable();               
        
        BigDecimal totalDebitExcepted = BigDecimal.ZERO;
        
        vEcriture.getListLigneEcriture().add(this.createLigne(1, null, null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, null, null));
        
        BigDecimal result = ecritureComptableService.getTotalDebit(vEcriture);
       
        Assertions.assertEquals(result, totalDebitExcepted);

    }
    
    @Test
    public void unitTestGetEcritureComptableByRefShouldReturnExistEcritureComptable() {
    	EcritureComptable ecritureComptable = new EcritureComptable();
    	String refString = "AC-2016/00001";
    	ecritureComptable.setReference(refString);
    	
       Mockito.when(ecritureComptableRepository.getByReference(refString))
       	      .thenReturn(java.util.Optional.of(ecritureComptable));
       
       Assertions.assertDoesNotThrow(() -> ecritureComptableService.getEcritureComptableByRef(refString));

    }
    
    @Test
    public void unitTestGetEcritureComptableByRefShouldThrowIfRefNotExist() {
    	EcritureComptable ecritureComptable = new EcritureComptable();
    	String refString = "AC-2016/00001";
    	ecritureComptable.setReference(refString);
    	
        Mockito.when(ecritureComptableRepository.getByReference(refString))
       	      .thenReturn(java.util.Optional.of(ecritureComptable));
       
       String badRefString = "AAAAAAA";
       
       Assertions.assertThrows(NotFoundException.class, () -> ecritureComptableService.getEcritureComptableByRef(badRefString));

    }
    
    @Test
    public void unitTestGetListEcritureComptable() {
    	List<EcritureComptable> ecritureComptables = new ArrayList<EcritureComptable>();
    	EcritureComptable exComptable = new EcritureComptable();
    	exComptable.setId(3712);
    	ecritureComptables.add(exComptable);
    	ecritureComptables.add(new EcritureComptable());
    	
    	Mockito.when(ecritureComptableRepository.findAll()).thenReturn(ecritureComptables);
    	
    	List<EcritureComptable> resuEcritureComptables = ecritureComptableService.getListEcritureComptable();
    	
    	Assertions.assertEquals(ecritureComptables.size(), resuEcritureComptables.size());
    	Assertions.assertEquals(ecritureComptables.get(0).getId(), exComptable.getId());

    }
    


}

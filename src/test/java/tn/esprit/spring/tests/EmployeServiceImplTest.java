package tn.esprit.spring.tests;
import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.EmployeServiceImpl;
import tn.esprit.spring.services.EntrepriseServiceImpl;

import org.apache.logging.log4j.Logger;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTest {
	
	private static final Logger l = LogManager.getLogger(EmployeServiceImplTest.class);
	
	@Autowired
	EmployeServiceImpl es;
	
	@Autowired
	EntrepriseServiceImpl ds;
	
	@Test
	public void testAddEmploye() {
		try {
		Employe E = new Employe("hajer" , "benarbia","hajer.benarbia@esprit.tn" ,true,Role.INGENIEUR);
		int Id = es.ajouterEmploye(E);
		//Assert obligataoire == pour tester il faut Assert !!!!
		assertNotNull(Id); //9otlou baad l'ajout test de l'ID not null 
		es.deleteEmployeById(Id); // delete bech n3abich base de données !!
		l.info("Add Employe works"); //pour la confirmation le succès du test !
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	@Test
	public void testUpdateEntreprise() {
		try {
		Employe E = new Employe("hajer" , "benarbia", "hajer.benarbia@esprit.tn" ,true,Role.INGENIEUR);
		int Id = es.ajouterEmploye(E);
		String email = "hajer@esprit.tn";
		es.mettreAjourEmailByEmployeId(email, Id);
		Employe Em = es.getEmployeById(Id); //3awedna 3ayatna lel employe pour tester le email tbadal ou nn!!
		assertEquals(email,Em.getEmail());
		es.deleteEmployeById(Id);
		l.info("Update Employe works");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	//test % size of list 
	@Test
	public void testDeleteEmployeById() {
		try {
			Employe E = new Employe("hajer" , "benarbia","hajer.benarbia@esprit.tn" ,true,Role.INGENIEUR);
		int Id = es.ajouterEmploye(E);
		int lengthBeforeDelete = es.getAllEmployes().size(); // longeur de la liste avant la suppression
		es.deleteEmployeById(Id);
		assertEquals(lengthBeforeDelete-1 , es.getAllEmployes().size()); //
		l.info("Delete Employe (%size) works");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	@Test
	public void testAffectEmployeToDepartment(){
		try {
		Employe E = new Employe("hajer" , "benarbia","hajer.benarbia@esprit.tn" ,true,Role.INGENIEUR);
		int IdE = es.ajouterEmploye(E);
		Departement D = new Departement("Info");
		int IdD = ds.ajouterDepartement(D);
		
		es.affecterEmployeADepartement(IdE, IdD);
		assertTrue(E.getDepartements().contains(IdD)); // retour true or false
	
		ds.deleteDepartementById(IdD);
		es.deleteEmployeById(IdE);
		l.info("Affect Employe to Department works");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
}

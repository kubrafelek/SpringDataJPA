package com.kubrafelek.jpahibernate;

import com.kubrafelek.jpahibernate.dao.AppDAO;
import com.kubrafelek.jpahibernate.dao.ReleaseDAO;
import com.kubrafelek.jpahibernate.dao.TicketDAO;
import com.kubrafelek.jpahibernate.entity.App;
import com.kubrafelek.jpahibernate.entity.Release;
import com.kubrafelek.jpahibernate.entity.Ticket;
import com.kubrafelek.jpahibernate.entity.dto.ApplicationDTO;
import com.kubrafelek.jpahibernate.entity.dto.TicketStatsByStatusDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    //Nesneyi kullanbilmek için springden bunları istiyoruz
    @Autowired
    private AppDAO appDAO;

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private ReleaseDAO releaseDAO;

    private static final Logger log = LoggerFactory.getLogger(JpaHibernateApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JpaHibernateApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        log.info("***************App Started***************");

        testPersist();

        testReadWithIdPrimaryKey();

        testReadWithJpql();

        testUpdate();

        //testRemove(); eksik

        printDBInfo();

        log.info("***************App Stopped***************");
    }

    private void printDBInfo() {
        EntityManagerFactory emf = entityManager.getEntityManagerFactory();
        Map<String, Object> emfPropertiesMap = emf.getProperties();
        log.info("*********************** EMF TEST **********************************************");
        for (Map.Entry<String, Object> entry : emfPropertiesMap.entrySet()) {
            log.info("------> " + entry.getKey() + " : " + entry.getValue());
        }
        log.info("********************************************************************************");
    }

    private void testRemove() {

        log.info("*********************** TEST REMOVE **********************************************");

        ApplicationDTO application = appDAO.getApplicationWithTicketsAndReleasesV3(1);

        Integer ticketId = application.getLastTicketId();
        ticketDAO.removeTicket(ticketDAO.getTicketById(ticketId));

        Ticket removedTicket = ticketDAO.getTicketById(ticketId);
        log.info("Is removed? {}", removedTicket == null);

        log.info("*********************** TEST REMOVE END *********************************************");
    }

    private void testUpdate() {

        log.info(">> TEST UPDATE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");

        Integer id = 1;
        String newName = "rentcar.com";
        String newOwner = "Ayşe Güzel";
        boolean updateSuccess = appDAO.updateNameAndOwnerById(id, newName, newOwner);

        App updated = appDAO.getApplicationById(1);

        log.info("Is updated ? {}, new name: {}, new owner: {}",
                updateSuccess, updated.getName(), updated.getOwner());

        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ");

    }

    private void testReadWithJpql() {

        log.info("---------- TESTING testReadWithJpql START -----------------");

        List<App> app = appDAO.getByNameOwner("buy.com", "Kübra Felek");
        log.info("***** App found : {}", app);

        log.info("---------- TESTING testReadWithJpql END -----------------");

    }

    private void testReadWithIdPrimaryKey() {

        log.info("---------- TESTING testReadWithIdPrimaryKey START -----------------");

        App app1 = appDAO.getApplicationById(1);
        log.info("********* Application Name: {}", app1.getName());

        List<Ticket> tickets = app1.getTickets();
        tickets.forEach(t -> log.info("******* Tickets: {}", t.getTitle()));

        Set<Release> releases = app1.getReleasesToDeploy();
        releases.forEach(r -> log.info("****** Release name: {}", r.getName()));

        log.info("---------- TESTING testReadWithIdPrimaryKey END -----------------");
    }

    private void testPersist() {

        App EcommerceWeb = new App("Ecommerce Service", "buy.com", "Kübra Felek");
        App EcommerceCoreApp = new App("Ecommerce Core Service", "buy.com", "Tuğçe Tutan");
        App EcommerceMobileApp = new App("Ecommerce Mobile Service", "buy.com", "Leyla Akçay");

        appDAO.addApp(EcommerceWeb);
        appDAO.addApp(EcommerceCoreApp);
        appDAO.addApp(EcommerceMobileApp);

        Ticket t1 = new Ticket("Login Failed when username is number", "OPEN", "Login Bug 1", LocalDate.now(), LocalDateTime.now());
        t1.setApplication(EcommerceWeb);
        ticketDAO.addTicket(t1);

        Ticket t2 = new Ticket("Password reminder not working", "CLOSED", "Password remind Bug 1", LocalDate.now(), LocalDateTime.now());
        t2.setApplication(EcommerceMobileApp);
        ticketDAO.addTicket(t2);

        Release release1 = new Release("v1", LocalDateTime.now().plusDays(5));
        Set<App> deployedApplications = new HashSet<>();
        deployedApplications.add(EcommerceCoreApp);
        deployedApplications.add(EcommerceWeb);
        deployedApplications.add(EcommerceMobileApp);
        release1.setDeployedApplications(deployedApplications);
        releaseDAO.addRelease(release1);
    }
}

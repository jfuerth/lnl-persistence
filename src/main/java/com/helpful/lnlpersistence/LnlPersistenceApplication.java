package com.helpful.lnlpersistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;

@SpringBootApplication
public class LnlPersistenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LnlPersistenceApplication.class, args);
    }


    @Autowired
    DataSource dataSource;

    //	@PostConstruct
    void classicJdbcQuery() throws SQLException {
        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from media limit 100")) {

            while (rs.next()) {
                System.out.println("*** Row " + rs.getRow());
                for (int col = 1; col <= rs.getMetaData().getColumnCount(); col++) {
                    System.out.println(rs.getMetaData().getColumnName(col) + ": " + rs.getString(col));
                }
                System.out.println();
            }
        }
    }


    @Autowired
    EntityManagerFactory entityManagerFactory;

    @PostConstruct
    void plainJpa() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Media m = entityManager.find(Media.class, "061588d8-14b6-49c2-ab08-e69881413d95");
            System.out.println("As fetched: " + m);

            m.setUrl("http://serious-videos.com/never-gonna-give-you-up.mp4");
            System.out.println("After modification: " + m);

            entityManager.refresh(m);
            System.out.println("After refresh: " + m);

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

//    @PostConstruct
    void plainJpaQuery() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            entityManager.createQuery("select m from Media m where m.createDate between ? and ?", Media.class)
                    .setParameter(0, Instant.parse("2017-04-04T00:00:00.00Z"))
                    .setParameter(1, Instant.parse("2017-04-05T00:00:00.00Z"))
                    .getResultList()
                    .forEach(System.out::println);

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }



    @Autowired
    MediaRepository mediaRepository;

//    @PostConstruct
    void springDataJpa() {
        mediaRepository.findByCreateDateBetween(
                Instant.parse("2017-04-04T00:00:00.00Z"),
                Instant.parse("2017-04-05T00:00:00.00Z"))
                .forEach(System.out::println);
    }
}

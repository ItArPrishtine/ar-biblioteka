package com.akropoliprishtine.repositories.book;

import com.akropoliprishtine.entities.Organization;
import com.akropoliprishtine.entities.book.Edition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Long> {
    List<Edition> findAllByOrganization(Organization organization);
}

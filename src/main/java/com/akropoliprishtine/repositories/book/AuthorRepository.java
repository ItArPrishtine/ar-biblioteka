package com.akropoliprishtine.repositories.book;

import com.akropoliprishtine.entities.Organization;
import com.akropoliprishtine.entities.book.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAllByOrganization(Organization organization);
}

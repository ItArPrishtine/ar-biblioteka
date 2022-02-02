package com.akropoliprishtine.entities.book;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.Auditable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "book_comment")
@SQLDelete(sql = "UPDATE book_comment SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class BookComment extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @JoinColumn(name = "userId", nullable = false)
    @ManyToOne
    private ApplicationUser applicationUser;

    @JoinColumn(name = "bookId", nullable = false)
    @ManyToOne
    private Book book;
    

}

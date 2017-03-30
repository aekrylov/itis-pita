package ru.kpfu.itis.pita.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/25/17 12:01 PM
 */
public class GroupRequest implements Serializable {

    @Id
    @ManyToOne
    private Group group;

    @Id
    @ManyToOne
    private User user;

    @Column(nullable = false, name = "timestamp")
    private Date timestamp;
}

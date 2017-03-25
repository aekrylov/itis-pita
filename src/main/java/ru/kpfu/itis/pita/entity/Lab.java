package ru.kpfu.itis.pita.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/25/17 11:22 AM
 */

@Entity
@Table(name = "labs")
public class Lab implements Serializable {

    //TODO

    @Id
    @OneToOne
    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lab)) return false;

        Lab lab = (Lab) o;

        return group.equals(lab.group);
    }

    @Override
    public int hashCode() {
        return group.hashCode();
    }
}

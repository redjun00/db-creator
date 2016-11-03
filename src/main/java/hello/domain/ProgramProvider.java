package hello.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class ProgramProvider {

    @Id
    @GeneratedValue
    private int id;

}

package hello.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cue {

    @Id
    @GeneratedValue
    private long id;

    private int durationInMs;

    @Enumerated(EnumType.STRING)
    private CueOwner owner;

    @Column(nullable = false)
    private long timeInMs;

    @Enumerated(EnumType.STRING)
    private CueType type;

    @ManyToOne(optional = false)
    private ProgramProvider programProvider;
}

package idg06.idg06_sec.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.util.Date;
import lombok.Data;

@Entity
@Data
@Table(name = "recuperarcontra")
public class Recuperar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_recuperar;

    @Column(length = 10)
    @NotEmpty
    private String token;

    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "estatus")
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Usuario usuario;

  
}
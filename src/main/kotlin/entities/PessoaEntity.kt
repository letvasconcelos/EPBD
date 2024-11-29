package entities

import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "Pessoa")
data class PessoaEntity(
    @Id
    @Column(name = "CPF", length = 11, nullable = false)
    val cpf: String,

    @Column(name = "Nome", length = 100, nullable = false)
    val nome: String,

    @Column(name = "Genero", length = 100, nullable = false)
    val genero: String,

    @Column(name = "Data_Nascimento", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val dataNascimento: LocalDateTime = LocalDateTime.now().withNano(0),

    @Column(name = "Email", length = 100, nullable = false)
    val email: String,

    @Column(name = "Telefone", length = 50, nullable = false)
    val telefone: String,

    @Column(name = "Email_reserva", length = 100, nullable = false)
    val emailReserva: String,

    @Column(name = "Telefone_Emergencia", length = 50, nullable = false)
    val telefoneEmergencia: String
)

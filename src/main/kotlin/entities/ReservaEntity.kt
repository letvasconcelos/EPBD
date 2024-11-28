package entities

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "Reserva", schema = "dbo")
data class ReservaEntity(

    @Id
    @Column(name = "IdReserva", nullable = false, length = 100)
    var idReserva: String = "",

    @Column(name = "DataInicio", nullable = false)
    var dataInicio: LocalDateTime = LocalDateTime.now(),

    @Column(name = "DataFim", nullable = false)
    var dataFim: LocalDateTime = LocalDateTime.now(),

    @Column(name = "Pago", nullable = false)
    var pago: Boolean = false,

    @Column(name = "Valor_Diarias", nullable = false, precision = 10, scale = 2)
    var valorDiarias: BigDecimal = BigDecimal.ZERO,

    @Column(name = "Numero_Acomodacao", nullable = false)
    var numeroAcomodacao: Int = 0,

    @Column(name = "CNPJ_Hotel", nullable = false, length = 11)
    var cnpjHotel: String = "",

    @Column(name = "CPF_Hospede", nullable = false, length = 11)
    var cpfHospede: String = "",

    @Column(name = "CPF_Responsavel", nullable = false, length = 11)
    var cpfResponsavel: String = ""
)

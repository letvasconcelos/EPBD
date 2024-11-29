package entities

import AcomodacaoId
import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.time.LocalDateTime

//*****
// DTO para lidas com a entidade Acomodacao
//*****

@Entity
@Table(name = "Acomodacao", schema = "dbo")
data class AcomodacaoEntity(
    @EmbeddedId
    var id: AcomodacaoId = AcomodacaoId(),

    @Column(name = "Politica_de_uso", nullable = false, length = 500)
    var politicaDeUso: String = "",

    @Column(name = "Capacidade", nullable = false)
    var capacidade: Int = 0,

    @Column(name = "Ultima_Limpeza", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    var ultimaLimpeza: LocalDateTime = LocalDateTime.now().withNano(0),

    @Column(name = "Preco_Base", nullable = false, precision = 10, scale = 2)
    var precoBase: BigDecimal = BigDecimal.ZERO,

    @Column(name = "Tipo", nullable = false, length = 50)
    var tipoAcomodacao: String = ""
)

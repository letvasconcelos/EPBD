package entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "Hospede", schema = "dbo")
data class HospedeEntity(
    @Id
    @Column(name = "CPF", nullable = false, length = 11)
    var cpf: String = "",

    @Column(name = "Pontos_Acumulados", nullable = false)
    var pontosAcumulados: Int = 0
)

package entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "Unidade")
data class UnidadeEntity(
    @Id
    @Column(name = "CNPJ", nullable = false, length = 14)
    val cnpjHotel: String,

    @Column(name = "End_Numero", nullable = false)
    val numero: Int,

    @Column(name = "End_Rua", nullable = false, length = 100)
    val rua: String,

    @Column(name = "End_Bairro", nullable = false, length = 50)
    val bairro: String,

    @Column(name = "End_CEP", nullable = false, length = 100)
    val cep: String,

    @Column(name = "End_Estado", nullable = false, length = 100)
    val estado: String,

    @Column(name = "Nome_Fantasia", nullable = false, length = 100)
    val nomeFantasia: String,

    @Column(name = "Registro_Imobiliario", nullable = false, length = 100)
    val registroImobiliario: String,

    @Column(name = "Nome_Empresa", nullable = false, length = 100)
    val nomeEmpresa: String
)

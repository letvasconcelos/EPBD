package entities

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "Hotel")
data class HotelEntity(
    @Id
    @Column(name = "CNPJ", nullable = false, length = 14)
    val cnpj: String,

    @Column(name = "Categoria", nullable = false, length = 50)
    val categoria: String,

    @Column(name = "Capacidade_Max_Hospedes", nullable = false)
    val capacidade: Int,

    @Column(name = "Id_Setor", nullable = false)
    val idSetor: Int
) : Serializable

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class AcomodacaoId(
    @Column(name = "Numero_Acomodacao")
    var numeroAcomodacao: Int = 0,
    @Column(name = "CNPJ_Hotel")
    var cnpjHotel: String = ""
) : Serializable

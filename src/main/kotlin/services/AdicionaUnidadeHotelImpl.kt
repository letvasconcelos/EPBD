package services

import entities.UnidadeEntity
import org.springframework.stereotype.Service
import repositories.UnidadeRepository

@Service
class AdicionaUnidadeHotelImpl(
    private val repositorio: UnidadeRepository
) {
    fun adicionarUnidade(params: Map<String, String>): UnidadeEntity {
        val unidade = UnidadeEntity(
            cnpjHotel = params["cnpj"] ?: "",
            numero = params["numero"]?.toIntOrNull() ?: 0,
            rua = params["rua"] ?: "",
            bairro = params["bairro"] ?: "",
            cep = params["cep"] ?: "",
            estado = params["estado"] ?: "",
            nomeFantasia = params["nomeFantasia"] ?: "",
            registroImobiliario = params["registroImobiliario"] ?: "",
            nomeEmpresa = params["nomeEmpresa"] ?: ""
        )

        return repositorio.save(unidade)
    }
}

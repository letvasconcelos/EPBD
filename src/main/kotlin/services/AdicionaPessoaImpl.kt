package services

import entities.HospedeEntity
import entities.PessoaEntity
import jakarta.persistence.Column
import org.springframework.stereotype.Service
import repositories.PessoaRepository
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class AdicionarPessoaImpl (
    private val repositorio: PessoaRepository
) {
    fun adicionarHospedePessoa(params: Map<String, String>): PessoaEntity {
        val pessoa = PessoaEntity(
            cpf =  params["cpf"] ?: "",
            nome= params["nome"] ?: "",
            genero= params["genero"] ?: "",
            dataNascimento= params["dataNascimento"]?.let { LocalDateTime.parse(it) } ?: LocalDateTime.now(),
            email= params["email"] ?: "",
            telefone=params["telefone"] ?: "",
            emailReserva= params["emailReserva"] ?: "",
            telefoneEmergencia=params["telefoneEmergencia"] ?: "",)

        return repositorio.save(pessoa)
    }
}
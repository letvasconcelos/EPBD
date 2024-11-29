package repositories

import entities.HospedeEntity
import entities.PessoaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PessoaRepository : JpaRepository<PessoaEntity, String> {
}
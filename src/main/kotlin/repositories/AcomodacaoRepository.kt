package repositories

import AcomodacaoId
import entities.AcomodacaoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AcomodacaoRepository : JpaRepository<AcomodacaoEntity, AcomodacaoId>
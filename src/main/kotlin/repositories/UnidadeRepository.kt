package repositories

import entities.ReservaEntity
import entities.UnidadeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UnidadeRepository : JpaRepository<UnidadeEntity, String> {
}
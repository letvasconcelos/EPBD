package repositories

import entities.HospedeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HospedeRepository : JpaRepository<HospedeEntity, String> {
}
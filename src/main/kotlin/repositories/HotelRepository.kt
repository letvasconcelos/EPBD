package repositories

import entities.HotelEntity
import entities.PessoaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HotelRepository : JpaRepository<HotelEntity, String> {
}
package task.simpleShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.simpleShop.model.Organisation;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {
}

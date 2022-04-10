package ru.geekbrains.march.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.march.market.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}

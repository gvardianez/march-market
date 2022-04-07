package ru.geekbrains.march.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.march.market.entities.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}

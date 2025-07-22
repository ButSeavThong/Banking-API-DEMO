package kh.coding.fullstackjpa.repository;

import kh.coding.fullstackjpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> { }

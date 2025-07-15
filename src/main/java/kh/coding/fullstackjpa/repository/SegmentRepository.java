package kh.coding.fullstackjpa.repository;

import kh.coding.fullstackjpa.domain.Segment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface SegmentRepository extends JpaRepository<Segment, Integer> {
    Optional<Segment> findBySegment(String segment);
}

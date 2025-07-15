package kh.coding.fullstackjpa.initialize;

import jakarta.annotation.PostConstruct;
import kh.coding.fullstackjpa.domain.Segment;
import kh.coding.fullstackjpa.repository.SegmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SegmentStock {
    private final SegmentRepository segmentRepository;

    // create object while program is running
    @PostConstruct
    void initialize() {
        try {
            if (segmentRepository.count() < 1) {
                List<String> segments = List.of("Gold", "Regular");
                List<Segment> segmentList = segments.stream().map(segt -> {
                    Segment segment = new Segment();
                    segment.setSegment(segt);
                    return segment;
                }).toList();
                segmentRepository.saveAll(segmentList);
                System.out.println("Segments initialized.");
            }
        } catch (Exception e) {
            System.err.println("Failed to initialize segments: " + e.getMessage());
            e.printStackTrace(); // Show full cause
        }
    }

}

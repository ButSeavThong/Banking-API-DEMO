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
    @PostConstruct
    void initialize(){
        if (segmentRepository.count() < 1) {
            List<String> segments = List.of("Gold", "Regular", "Regular");
            List<Segment> segmentList = segments.stream().map(segt -> {
                Segment segment = new Segment();
                segment.setSegment(segt);
                return segment;
            }).toList();
            segmentRepository.saveAll(segmentList);
        }
    }
}

package edu.ib.projekt_bazy_danych_v2;


import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Klasa
 */
public class TimeSlotsGenerator {
    public List generateTimeSlots(LocalTime startTime, LocalTime endTime){
        Duration piece = Duration.ofMinutes(15);
        int numberOfSlots = (int) Duration.between(startTime, endTime).dividedBy(piece);
        LocalTime[] partitionTimes = IntStream.rangeClosed(0, numberOfSlots)
                .mapToObj(i -> startTime.plus(piece.multipliedBy(i)))
                .toArray(LocalTime[]::new);
        List timeList = Arrays.asList(partitionTimes);
        return timeList;
    }


/*    public static void main(String[] args) {
        TimeSlotsGenerator timeSlotsGenerator = new TimeSlotsGenerator();
        List list = timeSlotsGenerator.generateTimeSlots(LocalTime.of(8,0), LocalTime.of(16,0));
        for (int i = 0; i <list.size();i++){
            System.out.println(list.get(i));
        }
    }*/
}

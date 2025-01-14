package com.maingame.Data;

import com.maingame.Model.Career.Student;
import net.datafaker.providers.base.AbstractProvider;
import net.datafaker.providers.base.BaseProviders;
import net.datafaker.service.WeightedRandomSelector;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class SchoolProvider extends AbstractProvider<BaseProviders> {

    private static final WeightedRandomSelector selector = new WeightedRandomSelector(new Random());

    private static final Map<Student.StudentType, List<Map<String, Object>>> schoolNames = Map.of(

            Student.StudentType.ELEMENTARYSCHOOL, List.of(
                    Map.of("value", "Maple Leaf Elementary", "weight", 0.15),
                    Map.of("value", "Sunshine Grove Primary School", "weight", 0.2),
                    Map.of("value", "Greenfield International School", "weight", 0.3),
                    Map.of("value", "Little Scholars Academy", "weight", 0.25),
                    Map.of("value", "Blue Sky Elementary", "weight", 0.1)
            ),
            Student.StudentType.MIDDLESCHOOL, List.of(
                    Map.of("value", "Sunset Ridge Middle School", "weight", 0.2),
                    Map.of("value", "Brookside Middle School", "weight", 0.3),
                    Map.of("value", "Riverdale Academy", "weight", 0.25),
                    Map.of("value", "Lakeside Junior High", "weight", 0.15),
                    Map.of("value", "Northwest Middle School", "weight", 0.2)
            ),
            Student.StudentType.HIGHSCHOOL, List.of(
                    Map.of("value", "Singapore city center high school", "weight", 0.1),
                    Map.of("value", "Downtown Science High", "weight", 0.2),
                    Map.of("value", "Westside High School", "weight", 0.3),
                    Map.of("value", "Eastgate Preparatory School", "weight", 0.25),
                    Map.of("value", "Riverside High", "weight", 0.15),
                    Map.of("value", "Mountainview High", "weight", 0.18),
                    Map.of("value", "Brooklyn International High School", "weight", 0.22)
            ),
            Student.StudentType.COLLEGE, List.of(
                    Map.of("value", "National University of Singapore", "weight", 0.4),
                    Map.of("value", "Nanyang Technological University", "weight", 0.25),
                    Map.of("value", "Singapore Management University", "weight", 0.15),
                    Map.of("value", "Yale-NUS College", "weight", 0.2),
                    Map.of("value", "Singapore Institute of Technology", "weight", 0.3),
                    Map.of("value", "University of California, Berkeley", "weight", 0.35),
                    Map.of("value", "University of Melbourne", "weight", 0.2)
            )
    );

    private static final List<Map<String, Object>> degreeChance = List.of(
            Map.of("value", "Computer Science", "weight", 0.2),
            Map.of("value", "Medicine", "weight", 0.15),
            Map.of("value", "Business", "weight", 0.15),
            Map.of("value", "Engineering", "weight", 0.12),
            Map.of("value", "Arts", "weight", 0.08),
            Map.of("value", "Psychology", "weight", 0.1),
            Map.of("value", "Law", "weight", 0.1)
    );

    public SchoolProvider(BaseProviders faker) {
        super(faker);
    }

    public String getSchoolName(Student.StudentType studentType) {
        return selector.select(schoolNames.get(studentType));
    }

    public String getRandomDegree() {
        return selector.select(degreeChance);
    }

    public List<String> getAllDegrees() {
        return degreeChance.stream()
                .map(degree -> (String) degree.get("value"))
                .collect(Collectors.toList());
    }
}
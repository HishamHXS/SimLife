package com.maingame.Data;

import net.datafaker.providers.base.AbstractProvider;
import net.datafaker.providers.base.BaseProviders;
import net.datafaker.service.WeightedRandomSelector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class CareerProvider extends AbstractProvider<BaseProviders> {

    private static final WeightedRandomSelector selector = new WeightedRandomSelector(new Random());

    private static final List<Map<String, Object>> careerChance = List.of(
            Map.of("value", "Software Engineer", "weight", 0.2, "degree", "Computer Science"),
            Map.of("value", "Doctor", "weight", 0.15, "degree", "Medicine"),
            Map.of("value", "Teacher", "weight", 0.1, "degree", "Psychology"),
            Map.of("value", "Artist", "weight", 0.08, "degree", "Arts"),
            Map.of("value", "Entrepreneur", "weight", 0.12, "degree", "Business"),
            Map.of("value", "Civil Engineer", "weight", 0.1, "degree", "Engineering"),
            Map.of("value", "Nurse", "weight", 0.08, "degree", "Medicine"),
            Map.of("value", "Marketing Manager", "weight", 0.07, "degree", "Business")
    );

    private static final Map<String, Integer> careerToSalary = new HashMap<>() {{
        put("Software Engineer", 40000);
        put("Doctor", 80000);
        put("Teacher", 30000);
        put("Artist", 25000);
        put("Entrepreneur", 0);
        put("Civil Engineer", 50000);
        put("Nurse", 35000);
        put("Marketing Manager", 55000);
    }};

    private static final Map<String, List<Map<String, Object>>> companyNames = Map.of(
            "Technology", List.of(
                    Map.of("value", "NovaTech Solutions", "weight", 0.2),
                    Map.of("value", "Quantum Innovations", "weight", 0.15),
                    Map.of("value", "CyberCore Systems", "weight", 0.18),
                    Map.of("value", "Vertex Digital", "weight", 0.22),
                    Map.of("value", "NexaSoft Technologies", "weight", 0.25)
            ),
            "Healthcare", List.of(
                    Map.of("value", "MedicaLife", "weight", 0.2),
                    Map.of("value", "VitalCare Solutions", "weight", 0.18),
                    Map.of("value", "Horizon Health", "weight", 0.15),
                    Map.of("value", "Zenith Medical", "weight", 0.22),
                    Map.of("value", "BioNova Pharmaceuticals", "weight", 0.25)
            ),
            "Finance", List.of(
                    Map.of("value", "Summit Capital", "weight", 0.2),
                    Map.of("value", "Fortis Financial", "weight", 0.18),
                    Map.of("value", "Vanguard Consulting", "weight", 0.15),
                    Map.of("value", "Everest Banking Group", "weight", 0.22),
                    Map.of("value", "Titan Investment Corp", "weight", 0.25)
            ),
            "Education", List.of(
                    Map.of("value", "BrightFuture Academy", "weight", 0.2),
                    Map.of("value", "Global Scholars Institute", "weight", 0.18),
                    Map.of("value", "Summit Learning Center", "weight", 0.15),
                    Map.of("value", "NextGen University", "weight", 0.22),
                    Map.of("value", "Pathway Knowledge Hub", "weight", 0.25)
            ),
            "Retail", List.of(
                    Map.of("value", "UrbanTrends", "weight", 0.2),
                    Map.of("value", "EverMart", "weight", 0.18),
                    Map.of("value", "Vista Retail", "weight", 0.15),
                    Map.of("value", "MetroStyle", "weight", 0.22),
                    Map.of("value", "NovaShops", "weight", 0.25)
            )
    );

    private static final Map<String, String> jobToIndustry = Map.of(
            "Software Engineer", "Technology",
            "Doctor", "Healthcare",
            "Teacher", "Education",
            "Artist", "Retail",
            "Entrepreneur", "Finance",
            "Civil Engineer", "Technology",
            "Nurse", "Healthcare",
            "Marketing Manager", "Finance"
    );

    public CareerProvider(BaseProviders faker) {
        super(faker);
    }

    public String getRandomCareer(String degree) {
        List<Map<String, Object>> filteredCareers = careerChance.stream()
                .filter(career -> career.get("degree").equals(degree) || career.get("degree").equals("No Degree"))
                .toList();

        return selector.select(filteredCareers);
    }

    public List<String> getAllCareers() {
        return careerChance.stream()
                .map(career -> {
                    String name = (String) career.get("value");
                    Integer salary = getSalary(name);
                    return String.format("%s - $%,d", name, salary);
                })
                .collect(Collectors.toList());
    }

    public String getCompanyName(String jobName) {
        return selector.select(companyNames.get(jobToIndustry.get(jobName)));
    }

    public int getSalary(String career) {
        return careerToSalary.getOrDefault(career, null);
    }
}

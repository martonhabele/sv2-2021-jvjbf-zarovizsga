package shipping;

import java.beans.PropertyEditorSupport;
import java.util.*;

public class ShippingService {
    private List<Transportable> packages = new ArrayList<>();

    public void addPackage(Transportable packageToShipping) {
        packages.add(packageToShipping);
    }

    public List<Transportable> collectItemsByBreakableAndWeight(boolean breakable, int minWeight) {
        List<Transportable> collected = new ArrayList<>();
        for (Transportable t : packages) {
            if (t.isBreakable() == breakable && t.getWeight() >= minWeight) {
                collected.add(t);
            }
        }
        return collected;
    }

    public Map<String, Integer> collectTransportableByCountry() {
        Map<String, Integer> collected = new LinkedHashMap<>();
        for (Transportable t : packages) {
            updateCollectedMap(collected, t);
        }
        return collected;
    }

    private void updateCollectedMap(Map<String, Integer> collected, Transportable t) {
        String country = t.getDestinationCountry();
        if (!collected.containsKey(country)) {
            collected.put(country, 1);
        } else {
            collected.put(country, collected.get(country) + 1);
        }
    }

    public List<Transportable> sortInternationalPackagesByDistance() {
        return packages.stream()
                .filter(transportable -> !transportable.getDestinationCountry().equals("Hungary"))
                .map(InternationalPackage.class::cast)
                .sorted(Comparator.comparingInt(InternationalPackage::getDistance))
                .map(Transportable.class::cast)
                .toList();
    }

    public List<Transportable> getPackages() {
        return Collections.unmodifiableList(packages);
    }
}
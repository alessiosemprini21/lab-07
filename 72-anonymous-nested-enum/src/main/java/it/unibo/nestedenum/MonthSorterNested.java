package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public enum Month {
        JANUARY(31), FEBRUARY(28), MARCH(31), APRIL(30),
        MAY(31), JUNE(30), JULY(31), AUGUST(31),
        SEPTEMBER(30), OCTOBER(31), NOVEMBER(30), DECEMBER(31);

        private final int days;

        Month(final int days) {
            this.days = days;
        }

        static Month fromString(String month) {
            Month candidate = null;
            month = month.toLowerCase(Locale.ROOT);
            for (var m : Month.values()) {
                if (m.name().toLowerCase(Locale.ROOT).startsWith(month)) {
                    if (candidate != null) {
                        throw new IllegalArgumentException("the name is ambiguous");
                    }
                    candidate = m;
                }
            }
            if (candidate == null) {
                throw new IllegalArgumentException("no month with such name");
            }
            return candidate;
        }

        public int getDays() {
            return this.days;
        }
    }

    public static class SortByMonthOrder implements Comparator<String> {
        @Override
        public int compare(final String a, final String b) {
            final var month1 = Month.fromString(a);
            final var month2 = Month.fromString(b);
            return month1.compareTo(month2);
        }
    }

    public static class SortByDate implements Comparator<String> {
        @Override
        public int compare(final String a, final String b) {
            final var days1 = Month.fromString(a).getDays();
            final var days2 = Month.fromString(b).getDays();
            return Integer.compare(days1, days2);
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }
}

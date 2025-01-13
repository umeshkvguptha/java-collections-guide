package guide.common.api;

import java.util.UUID;

/**
 * Represents an Employee with id and name.
 * Overrides equals() and hashCode() for proper behavior in collections.
 */
public class Employee implements Comparable{
    private final UUID id;
    private final String name;

    public Employee(final UUID id, final String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    // Overrides equals for meaningful comparison in collections.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return id == employee.id;
    }

    // Overrides hashCode to ensure consistency with equals.
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int compareTo(Object other) {
        return this.id.compareTo(((Employee)other).getId());
    }

    /**
     * Builder class for creating Employee objects.
     */
    public static EmployeeBuilder builder(final UUID id) {
        return new EmployeeBuilder(id);
    }

    /**
     * Builder class for creating Employee objects.
     */
    public static class EmployeeBuilder {
        private final UUID id; // Required field
        private String name; // Required field

        /**
         * Constructor for EmployeeBuilder with required fields.
         *
         * @param id The unique ID of the employee.
         */
        public EmployeeBuilder(UUID id) {
            this.id = id;
        }

        /**
         * Sets the name for the Employee.
         *
         * @param name The Employee name.
         * @return The current instance of EmployeeBuilder.
         */
        public EmployeeBuilder name(String name) {
            this.name = name;
            return this;
        }


        /**
         * Builds the Employee object.
         *
         * @return A new Employee object.
         */
        public Employee build() {
            return new Employee(id, name);
        }
    }
}

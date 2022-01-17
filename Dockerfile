FROM openjdk:11
ADD target/school-management-system-0.0.1-SNAPSHOT.jar school-management-system-g11.jar
ENTRYPOINT ["java", "-jar", "school-management-system-g11.jar"]
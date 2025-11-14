package co.edu.unbosque.ridesmartv2.sharedEvents;

public record UserCreatedEvent(String identification, String name, String mail, String token) {
}

package co.edu.unbosque.ridesmartv2.sharedEvents;

public record UserCreatedEvent(Long identification, String name, String mail, String token) {
}

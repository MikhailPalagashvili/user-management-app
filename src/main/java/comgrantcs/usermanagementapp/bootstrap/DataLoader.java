package comgrantcs.usermanagementapp.bootstrap;

import comgrantcs.usermanagementapp.entity.User;
import comgrantcs.usermanagementapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;

    public DataLoader(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = User
                .builder()
                .firstName("Stephen")
                .lastName("Curry")
                .email("stephencurry@gmail.com")
                .build();
        userRepository.save(user1);
        User user2 = User
                .builder()
                .firstName("Mark")
                .lastName("Zuckerberg")
                .email("markzuckerberg@gmail.com")
                .build();
        userRepository.save(user2);
    }
}

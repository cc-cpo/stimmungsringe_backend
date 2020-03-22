package de.wirvsvirus.hack.model;

import com.google.common.base.Preconditions;
import de.wirvsvirus.hack.mock.MockFactory;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.MoreCollectors;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserRepository {

    private List<User> mockDb = new ArrayList<>();

    @PostConstruct
    public void initMock() {
        mockDb = MockFactory.allUsers();
    }

    public User findByUserId(final UUID userId) {
        Preconditions.checkNotNull(userId);

        return
            StreamEx.of(mockDb)
                .collect(MoreCollectors.onlyOne(user -> user.getId().equals(userId)))
            .orElseThrow(() -> new IllegalStateException("User not found by id " + userId));
    }

    /**
     * return all users in same group except the requesting user
     */
    public List<User> findOtherUsersInGroup(UUID userId) {
        return
        mockDb.stream()
            .filter(user -> !user.getId().equals(userId))
            .collect(Collectors.toList());
    }

    public Sentiment findSentimentByUserId(UUID userId) {
        return MockFactory.sentimentByUser(userId);
    }

}

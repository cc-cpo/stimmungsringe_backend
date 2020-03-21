package de.wirvsvirus.hack.mock;

import com.google.common.collect.Lists;
import de.wirvsvirus.hack.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class MockFactory {

    @PostConstruct
    public void initMock() {
    }

    public List<User> allUsers() {

        User u1 = new User();
        u1.setName("Mutti");

        return Lists.newArrayList(u1);
    }


}
